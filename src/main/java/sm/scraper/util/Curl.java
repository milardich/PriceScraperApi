package sm.scraper.util;

import java.io.IOException;

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
}
