import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {
    private static Location rusMskLoc=new Location("Moscow",
            Country.RUSSIA, "Lenina", 15);
    private static String MOSCOW_IP = "172.0.32.11";
    private static Location rusAny=new Location("Moscow",
            Country.RUSSIA, null, 0);

    private static Location usNyLoc=new Location("New York",
            Country.USA, " 10th Avenue", 32);
    private static String NEW_YORK_IP = "96.44.183.149";
    private static Location usAny=new Location("New York",
            Country.USA, null,  0);
    private static GeoService geoTest=new GeoServiceImpl();
    @ParameterizedTest
    @ValueSource(strings={"96.44.183.12","96.92.92.11","96.196.0.1"})

    public void locateUsAnyTest(String ip){
        Location result=geoTest.byIp(ip);
        Assertions.assertEquals(usAny.getCountry(),result.getCountry());
        Assertions.assertEquals(usAny.getCity(),result.getCity());
        Assertions.assertEquals(usAny.getStreet(),result.getStreet());
        Assertions.assertEquals(usAny.getBuiling(),result.getBuiling());
    }
   @Test
    public void locateUsNyTest(){
        Location result=geoTest.byIp(NEW_YORK_IP);
        Assertions.assertEquals(usNyLoc.getCountry(),result.getCountry());
        Assertions.assertEquals(usNyLoc.getCity(),result.getCity());
        Assertions.assertEquals(usNyLoc.getStreet(),result.getStreet());
        Assertions.assertEquals(usNyLoc.getBuiling(),result.getBuiling());
    }


    @ParameterizedTest
    @ValueSource(strings={"172.44.183.149","172.92.92.11","172.196.0.1"})
    public void locateRusAnyTest(String ip){
        Location result=geoTest.byIp(ip);
        Assertions.assertEquals(rusAny.getCountry(),result.getCountry());
        Assertions.assertEquals(rusAny.getCity(),result.getCity());
        Assertions.assertEquals(rusAny.getStreet(),result.getStreet());
        Assertions.assertEquals(rusAny.getBuiling(),result.getBuiling());
    }
    @Test
    public void locateRusMskTest(){
        Location result=geoTest.byIp(MOSCOW_IP);
        Assertions.assertEquals(rusMskLoc.getCountry(),result.getCountry());
        Assertions.assertEquals(rusMskLoc.getCity(),result.getCity());
        Assertions.assertEquals(rusMskLoc.getStreet(),result.getStreet());
        Assertions.assertEquals(rusMskLoc.getBuiling(),result.getBuiling());
    }
}