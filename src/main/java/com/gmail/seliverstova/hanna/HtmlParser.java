package com.gmail.seliverstova.hanna;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {
    private String url;

    public HtmlParser(String url) {
        super();
        this.url = url;
    }

    public HtmlParser() {
        super();
    }

    public Map<String, String> getUrlMap() throws IOException {
        Map<String, String> urlMap = null;
        try {
            String text = getStringFromURL(this.url);
            urlMap = parse(text);
        } catch (IOException e) {
            throw e;
        }

        return urlMap;
    }

    private String getStringFromURL(String urlAddress) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            for (; (line = br.readLine()) != null;) {
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw e;
        }
        return sb.toString();
    }

    private Map<String, String> parse(String text) {
        Map<String, String> urlMap = new HashMap<>();

        String line;
        Pattern pattern = Pattern.compile("<a(.*?)</a>");
        Pattern pattern2 = Pattern.compile("href=\"(.*?)\"");
        Matcher matcher = pattern.matcher(text);
        for (; matcher.find();) {
            line = matcher.group(1);
            Matcher matcher2 = pattern2.matcher(line);
            if (matcher2.find()) {
                line = matcher2.group(1);
                urlMap.put(line, line.substring(line.lastIndexOf('>') + 1));
            }
        }

        return urlMap;
    }
}