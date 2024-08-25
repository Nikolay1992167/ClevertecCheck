package ru.clevertec.check.config;

import ru.clevertec.check.controller.MainOrderController;
import ru.clevertec.check.dto.CommandLineArgumentContainer;
import ru.clevertec.check.factory.CheckFactory;
import ru.clevertec.check.factory.impl.CheckFactoryImpl;
import ru.clevertec.check.mapper.ArgMapper;
import ru.clevertec.check.mapper.DiscountCardMapper;
import ru.clevertec.check.mapper.ProductMapper;
import ru.clevertec.check.mapper.impl.DiscountCardMapperImpl;
import ru.clevertec.check.mapper.impl.ProductMapperImpl;
import ru.clevertec.check.repository.DiscountCardRepository;
import ru.clevertec.check.repository.ProductRepository;
import ru.clevertec.check.repository.impl.DiscountCardRepositoryImpl;
import ru.clevertec.check.repository.impl.ProductRepositoryImpl;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.OrderService;
import ru.clevertec.check.service.PrintService;
import ru.clevertec.check.service.ProductService;
import ru.clevertec.check.service.impl.DiscountCardServiceImpl;
import ru.clevertec.check.service.impl.OrderServiceImpl;
import ru.clevertec.check.service.impl.PrintServiceImpl;
import ru.clevertec.check.service.impl.ProductServiceImpl;
import ru.clevertec.check.starter.MainOrder;
import ru.clevertec.check.starter.impl.MainOrderImpl;
import ru.clevertec.check.util.dbconnection.DatabaseConnection;
import ru.clevertec.check.validation.impl.ValidatorImpl;

import java.sql.Connection;
import java.util.Map;

public class ApplicationBuilder {

    private final MainOrder mainOrder;

    public static void run(String[] args) {
        CommandLineArgumentContainer commandLineArgumentContainer = CommandLineArgumentResolver.splitArgs(args);
        ApplicationBuilder applicationBuilder = new ApplicationBuilder(commandLineArgumentContainer.getSaveToFilePath(), commandLineArgumentContainer.getProperties());

        applicationBuilder.mainOrder.processOrder(commandLineArgumentContainer.getAppArguments());
    }

    private ApplicationBuilder(String saveToFilePath, Map<String, String> properties) {
        ArgMapper argMapper = new ArgMapper();

        PrintService printService = new PrintServiceImpl();

        Connection connection = DatabaseConnection.initializeDatabase(properties);

        ProductRepository productRepository = new ProductRepositoryImpl(connection);
        DiscountCardRepository discountCardRepository = new DiscountCardRepositoryImpl(connection);

        MainOrderController mainOrderController = getMainOrderController(discountCardRepository, productRepository);

        mainOrder = new MainOrderImpl(argMapper, printService, mainOrderController, new ValidatorImpl(), saveToFilePath);
    }

    private MainOrderController getMainOrderController(DiscountCardRepository discountCardRepository, ProductRepository productRepository) {
        DiscountCardMapper discountCardMapper = new DiscountCardMapperImpl();
        DiscountCardService discountCardService = new DiscountCardServiceImpl(discountCardRepository, discountCardMapper);
        ProductMapper productMapper = new ProductMapperImpl();
        ProductService productService = new ProductServiceImpl(productRepository, productMapper);
        CheckFactory checkFactory = new CheckFactoryImpl();
        OrderService orderService = new OrderServiceImpl(productService, discountCardService, checkFactory);

        return new MainOrderController(orderService);
    }
}