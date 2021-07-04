package ua.edu.sumdu.j2ee.kiptenko.demo.converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.edu.sumdu.j2ee.kiptenko.demo.model.NewsPojo;
import ua.edu.sumdu.j2ee.kiptenko.demo.model.Pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JsonConverter implements Converter<JSONObject, Pojo>{

//    @Autowired
//    ConversionService conversionService;

    public NewsPojo createObject(String json){

        NewsPojo np = new NewsPojo();
        JSONObject newsJsonObject = new JSONObject(json);

        JSONArray weatherArray = (JSONArray) newsJsonObject.get("sources");
        np.setSources(new ArrayList(weatherArray.length()));

        for (int i = 0; i < weatherArray.length(); i++){
            try{
                //np.getSources().set(i, conversionService.convert(weatherArray.get(i), Pojo.class));
                np.getSources().add(createPojo((JSONObject) weatherArray.get(i)));
                System.out.println(np.getSources().get(i));
            }catch (NullPointerException e){
                System.out.println(e);
            }
        }
        return np;
    }

    @Override
    public Pojo convert(JSONObject obj) {
        Pojo pojo = new Pojo();
        pojo.setDescription(obj.getString("description"));
        pojo.setUrl(obj.getString("url"));
        pojo.setCategory(obj.getString("category"));
        pojo.setCountry(obj.getString("country"));
        pojo.setLanguage(obj.getString("language"));
        return pojo;
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

    public String getStringJson(String baseURLsources, String apiKey, String parameters) throws IOException {
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
