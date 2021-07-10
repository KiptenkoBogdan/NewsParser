package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import ua.edu.sumdu.j2ee.kiptenko.demo.converter.JSONObjectToPojo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PojoGenerator{

    private static final Logger logger = Logger.getLogger(PojoGenerator.class);

    public NewsPojo createObject(String json){

        NewsPojo np = new NewsPojo();
        JSONObject newsJsonObject = new JSONObject(json);

        GenericConversionService conversionService = new GenericConversionService();
        Converter<JSONObject, Pojo> customConverter = new JSONObjectToPojo();
        conversionService.addConverter(customConverter);

        JSONArray weatherArray = (JSONArray) newsJsonObject.get("articles");
        np.setSources(new ArrayList(weatherArray.length()));

        for (int i = 0; i < weatherArray.length(); i++){
            try{
                np.getSources().add(conversionService.convert(weatherArray.get(i), Pojo.class));
                logger.info(np.getSources().get(i));
            }catch (NullPointerException e){
                logger.error(e);
            }
        }
        logger.info("Created NewsPojo object");
        return np;
    }

    public static Pojo createPojo(JSONObject obj){
        Pojo pojo = new Pojo();
        try{
            pojo.setTitle(obj.optString("title"));
            pojo.setDescription(obj.optString("description"));
            pojo.setUrl(obj.optString("url"));
            pojo.setAuthor(obj.optString("author"));
            pojo.setUrlToImage(obj.optString("urlToImage"));
        } catch (JSONException e){
            logger.error(e);
        }
        return pojo;
    }

    public String getStringJson(String baseURLsources, String apiKey, String parameters) throws IOException {
        String sURL = baseURLsources + apiKey + parameters;

        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("GET");
        request.connect();
        logger.info("Connected to NewsAPI service");
        request.getInputStream();
        String contents = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        logger.info("Retrieved data (json string) from NewsAPI service");
        request.disconnect();
        logger.info("Disconnected from NewsAPI service");

        return contents;
    }
}
