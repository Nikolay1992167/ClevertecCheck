package ru.clevertec.check.dto;

import java.util.Map;

public interface CommandLineArgumentContainer {

    String[] getAppArguments();

    Map<String, String> getProperties();

    String getSaveToFilePath();
}