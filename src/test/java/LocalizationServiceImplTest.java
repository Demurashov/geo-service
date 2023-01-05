import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    private static String expectedRus = "Добро пожаловать";
    private static String expectedUs = "Welcome";
    public static LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    public void localeRusTest() {
        Country country = Country.RUSSIA;
        String result = localizationService.locale(country);
        Assertions.assertEquals(expectedRus, result);
    }

    @Test
    public void localeAnyTest() {
        Country[] countries = {Country.USA, Country.BRAZIL, Country.GERMANY};
        for (Country country : countries) {
            String result = localizationService.locale(country);
            Assertions.assertEquals(expectedUs, result);
        }
    }
}
