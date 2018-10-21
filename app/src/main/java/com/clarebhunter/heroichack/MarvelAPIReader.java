package com.clarebhunter.heroichack;
import static com.clarebhunter.heroichack.HashGenerator.generate;

import org.json.JSONArray;
import org.json.JSONObject;
import android.media.Image;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MarvelAPIReader {
    private static String apiKey = "0d20b732e9366c58db70602fc87cf3df";
    private static long timeStamp = System.currentTimeMillis();
    private static String privateKey = "4fe8d50f61812a2fe69a4ec406da67d594b4e193";

    public static Integer getCharacterID(String name) {
        Integer idNum = -1;
        if (name.contains(" ")) {
            int spaceChar = name.indexOf(" ");
            name = name.substring(0, spaceChar) + "%20" + name.substring(spaceChar, name.length());
        }
        try {
            timeStamp = System.currentTimeMillis();
            String hashString = generate(timeStamp, privateKey, apiKey);

            idNum = singleCharacter(getHTML(String.format("https://gateway.marvel.com/v1/public/characters?name=%s&apikey=%s&ts=%s&hash=%s", name, apiKey, timeStamp, hashString)));

        } catch (Exception e) {
            System.out.println(e);
        }

        return idNum;
    }

    public static Integer singleCharacter(String jsonLine) {
        JSONObject data;
        JSONArray result;
        int idNum = 0;

        try {
            JSONObject obj = new JSONObject(jsonLine);

            data = obj.getJSONObject("data");
            result = data.getJSONArray("results");

            for (int i = 0; i < result.length(); i++) {
                idNum = result.getJSONObject(i).getInt("id");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return idNum;
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

        return result.toString();
    }

    public static List<MarvelCharacter> parseCharacters(String jsonLine) {
        JSONObject data;
        JSONArray result;
        List<MarvelCharacter> characterList = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(jsonLine);

            data = obj.getJSONObject("data");
            result = data.getJSONArray("results");

            for (int i = 0; i < result.length(); i++) {
                String name = result.getJSONObject(i).getString("name");
                int idNum = result.getJSONObject(i).getInt("id");
                MarvelCharacter character = new MarvelCharacter(name, idNum);
                characterList.add(character);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return characterList;
    }

    public static List<Comic> getComics(List<Integer> ids) {
        List<Comic> comics = new ArrayList<>();
        try {
            timeStamp = System.currentTimeMillis();
            String hashString = generate(timeStamp, privateKey, apiKey);
            String characters = "";

            if (ids.size() == 1){
                characters = ids.get(0) + "";
            } else {
                for (int i = 0; i < ids.size() - 1; i++) {
                    characters += ids.get(i) + "%2C";
                }
                characters += ids.get(ids.size() - 1);
            }
            String urlToPassIN = String.format("https://gateway.marvel.com/v1/public/comics?sharedAppearances=%s&apikey=%s&ts=%s&hash=%s", characters, apiKey, timeStamp, hashString);
            comics = parseComics(getHTML(urlToPassIN));
        } catch (Exception e) {
            System.out.println(e);
        }

        return comics;
    }

    private static List<Comic> parseComics(String jsonLine) {
        JSONObject data;
        JSONArray result;
        List<Comic> comicList = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(jsonLine);

            data = obj.getJSONObject("data");
            result = data.getJSONArray("results");

            for (int i = 0; i < result.length(); i++) {
                String title = result.getJSONObject(i).getString("title");
                String description;
                try {
                    description = result.getJSONObject(i).getString("description");
                } catch (Exception e) {
                    description = "There is no description for this comic.";
                }
                //Image thumbnail = result.getJSONObject(i).getString("thumbnail");
                //Comic comic = new Comic(title, description, thumbnail);
                Comic comic = new Comic(title, description);
                comicList.add(comic);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return comicList;
    }
}
