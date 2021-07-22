package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;
import ua.edu.sumdu.j2ee.kiptenko.demo.converter.JSONObjectToPojo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Component
public class PojoGenerator implements IPojoGenerator{

    private static final Logger logger = Logger.getLogger(PojoGenerator.class);

    @Value("${baseURLsources}")
    private String baseURLsources;

    @Value("${apiKey}")
    private String apiKey;

    public NewsPojo createObject(String keyWord) throws IOException {

        NewsPojo np = new NewsPojo();
        String str = getStringJson(keyWord);
        JSONObject newsJsonObject = new JSONObject(str);

        GenericConversionService conversionService = new GenericConversionService();
        Converter<JSONObject, Pojo> customConverter = new JSONObjectToPojo();
        conversionService.addConverter(customConverter);

        JSONArray weatherArray = (JSONArray) newsJsonObject.get("articles");
        np.setSources(new ArrayList(weatherArray.length()));

        for (int i = 0; i < weatherArray.length(); i++){
            try{
                np.getSources().add(conversionService.convert(weatherArray.get(i), Pojo.class));
                //logger.info(np.getSources().get(i));
            }catch (NullPointerException e){
                logger.error(e);
            }
        }
        logger.info("Created NewsPojo object");
        return np;
    }

    public String getStringJson(String parameters) throws IOException {
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
