package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class JsonGenerator {

    public Map<String, Object> getJson(String baseURLsourses, String apiKey, String keyWord) throws IOException {
        String sURL = baseURLsourses + apiKey + keyWord;

        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("GET");
        request.connect();


        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(content.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Map<String, Object> result = jsonObject.toMap();

        request.disconnect();
        return result;
    }
}