package sm.scraper.util;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static String getStringValue(String text, String regex) {
        if(Objects.equals(regex, "")) {
            return null;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            System.out.println("No match found for regex: " + regex);
        }
        return null;
    }

    public static Integer getIntValue(String text, String regex) {
        if(Objects.equals(regex, "")) {
            return null;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            System.out.println("No match found for regex: " + regex);
        }
        return null;
    }

}
