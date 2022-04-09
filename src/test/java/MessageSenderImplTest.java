import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageSenderImplTest {
    final GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
    final LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
    final MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
    final String IP_RUSSIA = "172.";
    final String IP_USA = "96.";
    final String MESSAGE_RU = "Добро пожаловать";
    final String MESSAGE_US = "Welcome";

    @ParameterizedTest
    @CsvSource({
            MessageSenderImpl.IP_ADDRESS_HEADER + "," + IP_RUSSIA + "," + MESSAGE_RU,
            MessageSenderImpl.IP_ADDRESS_HEADER + "," + IP_USA + "," + MESSAGE_US
        })
    void test_check_language_ru(String header, String ip, String message) {

        Mockito.when(geoService.byIp(IP_RUSSIA)).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp(IP_USA)).thenReturn(new Location("New York", Country.USA, null,  0));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn(MESSAGE_RU);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn(MESSAGE_US);

        assertEquals(messageSender.send(Map.of(header, ip)), message);
    }
}
