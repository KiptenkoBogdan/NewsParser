package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.thoughtworks.xstream.XStream;
import ua.edu.sumdu.j2ee.kiptenko.demo.converter.MapEntryConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class XmlGenerator {

//    public Map<String, String> getXml(String baseURLsourses, String apiKey, String keyWord) throws IOException {
//        String sURL = baseURLsourses + apiKey + keyWord;
//
//        URL url = new URL(sURL);
//        HttpURLConnection request = (HttpURLConnection) url.openConnection();
//        request.setRequestMethod("GET");
//        request.connect();
//
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String inputLine;
//        StringBuilder content = new StringBuilder();
//        while ((inputLine = in.readLine()) != null) {
//            content.append(inputLine);
//        }
//        in.close();
//
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = new JSONObject(content.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        String xml = XML.toString(jsonObject);
//
//        XStream magicApi = new XStream();
//        magicApi.registerConverter(new MapEntryConverter());
//        magicApi.alias("sources", Map.class);
//
//        Map<String, String> map = (Map<String, String>) magicApi.fromXML(xml);
//
//        request.disconnect();
//        System.out.println(xml);
//        System.out.println("\n");
//        System.out.println(map);
//        return map;
//    }

    public String getXml(String baseURLsourses, String apiKey, String keyWord) throws IOException {
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

        String xml = "<?xml version='1.0' encoding='UTF-8'?>" + "<news>" + XML.toString(jsonObject) + "<news>";

        request.disconnect();
        System.out.println(xml);
        return xml;
    }
}
