package main.java.ru.clevertec.check.dto.response;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static main.java.ru.clevertec.check.util.Constants.DELIMITER;

public class CheckTitle extends AbstractTitlePrintable {

    private final LocalDate date;
    private final LocalTime time;

    public CheckTitle() {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    @Override
    protected String getTitle() {
        return "Date" + DELIMITER + "Time\n";
    }

    @Override
    protected void printBody(Writer writer) throws IOException {
        writer.append(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .append(DELIMITER)
                .append(time.truncatedTo(ChronoUnit.SECONDS).toString())
                .append('\n');
    }
}
