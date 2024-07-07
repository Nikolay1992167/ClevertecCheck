package main.java.ru.clevertec.check.config;

import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.repository.DiscountCardRepository;
import main.java.ru.clevertec.check.repository.ProductRepository;
import main.java.ru.clevertec.check.repository.impl.DiscountCardRepositoryImpl;
import main.java.ru.clevertec.check.repository.impl.ProductRepositoryImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositoryConfig {

    public static ProductRepository productRepository;
    public static DiscountCardRepository discountCardRepository;

    public static ProductRepository getProductRepository(String filePath) {
        if (productRepository == null) {
            productRepository = new ProductRepositoryImpl(productFileMap(filePath));
        }

        return productRepository;
    }

    public static DiscountCardRepository getDiscountCardRepository(String filePath) {
        if (discountCardRepository == null) {
            discountCardRepository = new DiscountCardRepositoryImpl(discountCardFileMap(filePath));
        }

        return discountCardRepository;
    }

    public static Map<Long, Product> productFileMap(String filePath) {
        Path path = Paths.get(filePath);

        try {
            return Files.readAllLines(path).stream()
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(args -> Product.builder()
                            .id(Long.parseLong(args[0]))
                            .description(args[1])
                            .price(new BigDecimal(args[2]))
                            .quantityInStock(Integer.parseInt(args[3]))
                            .wholesaleProduct(Boolean.valueOf(args[4]))
                            .build())
                    .collect(Collectors.toMap(Product::getId, product -> product));

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static Map<Long, DiscountCard> discountCardFileMap(String filePath) {
        Path path = Paths.get(filePath);

        try {
            return Files.readAllLines(path).stream()
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(args -> DiscountCard.builder()
                            .id(Long.parseLong(args[0]))
                            .number(Integer.parseInt(args[1]))
                            .discountAmount(Byte.parseByte(args[2]))
                            .build())
                    .collect(Collectors.toMap(DiscountCard::getId, card -> card));

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}