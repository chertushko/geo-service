import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceImplTest {
    GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
    private final GeoService suite = new GeoServiceImpl();
    Date data = new Date();

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new test " + data.toString());
    }

    @AfterEach
    public void finishTest() {
        System.out.println("Test complete");
    }

    @Test
    void byIpTesting() {
        final Location exected = new Location("New York", Country.USA, null,  0);
        Location actual = suite.byIp("96.");
        assertEquals(exected.getCountry(), actual.getCountry());
    }

    @Test
    void exceptionTesting() {
        assertThrows(RuntimeException.class, () -> suite.byCoordinates(1, 2.0));

    }
}
