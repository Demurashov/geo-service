import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    //private String[] ip={"96.44.183.149","172.92.92.11"};
     //@Test


      private static GeoService geoServiceImpl = Mockito.mock(GeoService.class);
    private static LocalizationService localizationServiceImpl=Mockito.mock(LocalizationService.class);


    @ParameterizedTest
     @ValueSource(strings={"96.44.183.149","96.92.92.11","96.196.0.1"})
     public void sendUsaTest(String ip) {
       // String ip="96.44.183.149";
        Mockito.when(geoServiceImpl.byIp(ip)).thenReturn(new Location("New York",
                Country.USA, null,  0));
        Mockito.when(localizationServiceImpl.locale(Country.USA)).thenReturn("Hi guys!!!");
        MessageSender messageSender = new MessageSenderImpl(geoServiceImpl, localizationServiceImpl);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String expected=messageSender.send(headers);
        Assertions.assertEquals(expected,"Hi guys!!!");
    }
    @ParameterizedTest
    @ValueSource(strings={"172.44.183.149","172.92.92.11","172.196.0.1"})
    public void sendRussiaTest(String ip){
        Mockito.when(geoServiceImpl.byIp(ip)).thenReturn(new Location("Moscow",
                Country.RUSSIA, null, 0));
        Mockito.when(localizationServiceImpl.locale(Country.RUSSIA)).thenReturn("Привет чувак!!!");
        MessageSender messageSender = new MessageSenderImpl(geoServiceImpl, localizationServiceImpl);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        messageSender.send(headers);
        String expected=messageSender.send(headers);
        Assertions.assertEquals(expected,"Привет чувак!!!");
    }
}
