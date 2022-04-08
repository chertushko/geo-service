import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImpTest {
    LocalizationService suite = new LocalizationServiceImpl();

    @Test
    public void testLocale() {
        final String exected = "Welcome";
        String result = suite.locale(Country.USA);
        assertEquals(exected, result);
    }
}
