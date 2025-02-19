package edu.wgu.d387_sample_code.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PresentationTimes {

    public static String[] timeZoneArray = new String[3];;
    @RequestMapping(path = "/timezones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public static String[] showWelcomeMessage() {
        ZoneId zEastern = ZoneId.of("America/New_York");
        ZoneId zMountain = ZoneId.of("America/Denver");
        ZoneId zoneId = ZoneId.of("UTC");

        LocalDateTime localDateTime = LocalDateTime.now();
        String localTime = localDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        ZonedDateTime zonedDateTimeEastern = zonedDateTime.withZoneSameInstant(zEastern);
        LocalDateTime localDateTimeEastern = zonedDateTimeEastern.toLocalDateTime();
        String localTimeEastern = localDateTimeEastern.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        ZonedDateTime zonedDateTimeMountain = zonedDateTime.withZoneSameInstant(zMountain);
        LocalDateTime localDateTimeMountain = zonedDateTimeMountain.toLocalDateTime();
        String localTimeMountain = localDateTimeMountain.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));

        timeZoneArray[0] = localTime;
        timeZoneArray[1] = localTimeEastern;
        timeZoneArray[2] = localTimeMountain;

        return timeZoneArray;
    }
}
