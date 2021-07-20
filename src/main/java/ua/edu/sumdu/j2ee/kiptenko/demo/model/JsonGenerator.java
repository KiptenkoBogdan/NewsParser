package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Component
public class JsonGenerator implements IJsonGenerator{

    private static final Logger logger = Logger.getLogger(JsonGenerator.class);

    @Value("${baseURLsources}")
    private String baseURLsources;

    @Value("${apiKey}")
    private String apiKey;


    public Map<String, Object> getJson(String keyWord) throws IOException {
        String sURL = baseURLsources + apiKey + keyWord;

        //connecting to NewsAPI service
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("GET");
        request.connect();
        logger.info("Connected to NewsAPI service");

        //retrieving data
        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        logger.info("Data retrieved from service");

        //creating JSONObject
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(content.toString());
        } catch (JSONException e) {
            logger.error(e);
        }

        //mapping end result
        Map<String, Object> result = jsonObject.toMap();

        request.disconnect();
        logger.info("Disconnected from NewsAPI service");

        return result;
    }
}