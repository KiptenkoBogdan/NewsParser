package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class XmlGenerator implements IXmlGenerator{

    private static final Logger logger = Logger.getLogger(XmlGenerator.class);

    public String getXml(String baseURLsourses, String apiKey, String keyWord) throws IOException {
        String sURL = baseURLsourses + apiKey + keyWord;

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

        //creating JSONObject
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(content.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" + "<news>" + XML.toString(jsonObject) + "</news>";

        request.disconnect();
        logger.info("Disconnected from NewsAPI service");

        System.out.println(xml);
        return xml;
    }
}
