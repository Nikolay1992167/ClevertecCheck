package main.java.ru.clevertec.check.config;

import main.java.ru.clevertec.check.controller.MainOrderController;
import main.java.ru.clevertec.check.factory.CheckFactory;
import main.java.ru.clevertec.check.factory.impl.CheckFactoryImpl;
import main.java.ru.clevertec.check.mapper.ArgMapper;
import main.java.ru.clevertec.check.mapper.DiscountCardMapper;
import main.java.ru.clevertec.check.mapper.ProductMapper;
import main.java.ru.clevertec.check.mapper.impl.DiscountCardMapperImpl;
import main.java.ru.clevertec.check.mapper.impl.ProductMapperImpl;
import main.java.ru.clevertec.check.repository.DiscountCardRepository;
import main.java.ru.clevertec.check.repository.ProductRepository;
import main.java.ru.clevertec.check.service.DiscountCardService;
import main.java.ru.clevertec.check.service.OrderService;
import main.java.ru.clevertec.check.service.PrintService;
import main.java.ru.clevertec.check.service.ProductService;
import main.java.ru.clevertec.check.service.impl.DiscountCardServiceImpl;
import main.java.ru.clevertec.check.service.impl.OrderServiceImpl;
import main.java.ru.clevertec.check.service.impl.PrintServiceImpl;
import main.java.ru.clevertec.check.service.impl.ProductServiceImpl;
import main.java.ru.clevertec.check.starter.MainOrder;
import main.java.ru.clevertec.check.starter.impl.MainOrderImpl;
import main.java.ru.clevertec.check.validation.impl.ValidatorImpl;

public class ApplicationBuilder {

    private final MainOrder mainOrder;

    private ApplicationBuilder() {
        ArgMapper argMapper = new ArgMapper();
        PrintService printService = new PrintServiceImpl();

        ProductRepository productRepository = RepositoryConfig.getProductRepository(
                "./src/main/resources/products.csv");
        DiscountCardRepository discountCardRepository = RepositoryConfig.getDiscountCardRepository(
                "./src/main/resources/discountCards.csv");

        MainOrderController mainOrderController = getMainOrderController(discountCardRepository, productRepository);
        mainOrder = new MainOrderImpl(argMapper, printService, mainOrderController, new ValidatorImpl());
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

    public static void run(String[] args) {
        new ApplicationBuilder().mainOrder.processOrder(args);
    }
}