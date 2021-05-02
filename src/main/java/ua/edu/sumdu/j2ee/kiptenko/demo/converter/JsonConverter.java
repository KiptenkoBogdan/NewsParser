package ua.edu.sumdu.j2ee.kiptenko.demo.converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ua.edu.sumdu.j2ee.kiptenko.demo.model.NewsPojo;
import ua.edu.sumdu.j2ee.kiptenko.demo.model.Pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JsonConverter {
    public static NewsPojo createObject(String json){
        NewsPojo np = new NewsPojo();
        JSONObject newsJsonObject = new JSONObject(json);

        JSONArray weatherArray = (JSONArray) newsJsonObject.get("sources");
        np.setSources(new Pojo[weatherArray.length()]);

        for (int i=0; i<weatherArray.length(); i++){
            np.getSources()[i] = createPojo((JSONObject) weatherArray.get(i));
            System.out.println(np.getSources()[i]);
        }
        return np;
    }

    public static Pojo createPojo(JSONObject obj){
        Pojo pojo = new Pojo();
        pojo.setDescription(obj.getString("description"));
        pojo.setUrl(obj.getString("url"));
        pojo.setCategory(obj.getString("category"));
        pojo.setCountry(obj.getString("country"));
        pojo.setLanguage(obj.getString("language"));
        return pojo;
    }

    public static String getStringJson(String baseURLsources, String apiKey, String parameters) throws IOException {
        String sURL = baseURLsources + apiKey + parameters;

        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("GET");
        request.connect();
        request.getInputStream();
        String contents = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        request.disconnect();

        return contents;
    }

    public static JSONObject parseJSON(InputStream stream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
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
        return jsonObject;
    }
}
