package com.gmail.seliverstova.hanna;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        HtmlParser parser = new HtmlParser("https://habr.com/ru/");

        Map<String, String> urlMap;
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        try {
            urlMap = parser.getUrlMap();
            set = urlMap.keySet();
            for (String key : set) {
                sb.append(key + "\t" + urlMap.get(key)).append(System.lineSeparator());
            }
            FileManipulator.wrireToFile(new File("urls.txt"), sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
