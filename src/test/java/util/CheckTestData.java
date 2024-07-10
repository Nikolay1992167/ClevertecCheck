package util;

import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.dto.response.CheckTotal;

public class CheckTestData {

    public static Check getCheck() {
        return Check.builder()
                .checkBody(CheckBodyTestData.getCheckBody())
                .checkTitle(CheckTitleTestData.getCheckTitle())
                .checkTotal(new CheckTotal(CheckBodyTestData.getCheckBody()))
                .build();
    }
}
