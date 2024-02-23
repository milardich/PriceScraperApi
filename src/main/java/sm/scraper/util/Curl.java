package sm.scraper.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Curl {

    public static String getHtml(String url) {

        StringBuilder command = new StringBuilder();
        command.append("curl -X GET ");
        command.append(url);
        ProcessBuilder processBuilder = new ProcessBuilder(command.toString().split(" "));

        String result = "";

        try {
            Process process = processBuilder.start();
            result = new String(process.getInputStream().readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(result.isEmpty()) {
            throw new RuntimeException("Failed to fetch html from url " + url);
        }

        return result;
    }

    public static String extractBaseUrl(String url) {
        String regex = "^(https:?://[^/]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            System.out.println("Error: URL not valid");
            return null;
        }
    }
}
