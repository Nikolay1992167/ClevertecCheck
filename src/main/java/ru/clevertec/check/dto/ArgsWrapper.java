package main.java.ru.clevertec.check.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgsWrapper implements CommandLineArgumentContainer {

    public final static String READ_FROM_FILE_PATH = "pathToFile";
    public final static String SAVE_TO_FILE_PATH = "saveToFile";

    private final List<String> appArgs = new ArrayList<>();
    private final Map<String, String> properties = new HashMap<>();

    public void addAppArg(String... args) {
        appArgs.addAll(Arrays.asList(args));
    }

    @Override
    public String[] getAppArguments() {
        return appArgs.toArray(String[]::new);
    }

    public void putProperty(String key, String value) {
        properties.put(key, value);
    }

    @Override
    public String getReadFromFilePath() {
        return properties.get(READ_FROM_FILE_PATH);
    }

    @Override
    public String getSaveToFilePath() {
        return properties.get(SAVE_TO_FILE_PATH);
    }
}