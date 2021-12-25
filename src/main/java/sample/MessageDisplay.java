package sample;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageDisplay {

    public static String display(String message) {
        return String.format("Victor %s\n%s",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), message);
    }

}
