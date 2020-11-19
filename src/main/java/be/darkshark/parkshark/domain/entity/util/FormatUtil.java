package be.darkshark.parkshark.domain.entity.util;

import java.time.format.DateTimeFormatter;

public class FormatUtil {

    public static DateTimeFormatter getDateFormat(){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }
    public static DateTimeFormatter getDateTimeFormat() { return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:MM:SS");}
}
