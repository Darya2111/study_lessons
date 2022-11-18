package tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class parametersTest {

    @Test
    @DisplayName("Мой любимый тест")
    public void testAnnotated() {
        Allure.parameter("Регион", "Московская область");
        Allure.parameter("Город", "Москва");
    }
}
