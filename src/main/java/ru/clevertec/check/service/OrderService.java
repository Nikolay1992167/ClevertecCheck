package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.dto.request.Bucket;
import main.java.ru.clevertec.check.dto.response.Check;

public interface OrderService {

    Check generateCheck(Bucket args);
}