package com.clarebhunter.heroichack;
import static com.clarebhunter.heroichack.HashGenerator.generate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class MarvelAPIReader {
    private static String apiKey = "0d20b732e9366c58db70602fc87cf3df";
    private static long timeStamp = System.currentTimeMillis();
    private static String privateKey = "4fe8d50f61812a2fe69a4ec406da67d594b4e193";

    public static void main(String[] args) {
        try {
            timeStamp = System.currentTimeMillis();
            String hashString = generate(timeStamp, privateKey, apiKey);
            System.out.println(getHTML(String.format("https://gateway.marvel.com/v1/public/characters?apikey=%s&ts=%s&hash=%s", apiKey, timeStamp, hashString)));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL myURL = new URL(urlToRead);
        URLConnection myURLConnection = myURL.openConnection();
        myURLConnection.connect();

        BufferedReader rd = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();

        parse(result.toString());
        return result.toString();
    }

    public static void parse(String jsonLine) {
        JSONObject data;
        JSONArray result;
        Map<String, Integer> characterMap = new HashMap<>();

        try {
            JSONObject obj = new JSONObject(jsonLine);

            data = obj.getJSONObject("data");
            result = data.getJSONArray("results");

            for (int i = 0; i < result.length(); i++) {
                String name = result.getJSONObject(i).getString("name");
                int idNum = result.getJSONObject(i).getInt("id");
                characterMap.put(name, idNum);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(characterMap);
    }
}
