package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.sender.MessageSenderImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void byIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location("Moscow", Country.RUSSIA, null, 0);
        Location result = geoService.byIp("172. ");

        Assertions.assertEquals(expected,result);
    }

    @ParameterizedTest
    @MethodSource("factory")
    void byIpParam(String ip, Location expected) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location result = geoService.byIp(ip);
        Assertions.assertEquals(expected,result);
    }

    public static Stream<Arguments> factory() {

        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.20", new Location("Moscow", Country.RUSSIA, null, 0))


        );
    }
}
