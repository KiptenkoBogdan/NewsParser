package ua.edu.sumdu.j2ee.kiptenko.demo.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@RestController
public class MainController {
    private static final String baseURL = "https://newsapi.org/v2/everything?apiKey=629909da42254cd28c952adb9d926de4";

    @GetMapping("/api/news/name")
    Map<String, Object> getNews(@RequestParam(value = "question") String question, @RequestParam(value = "country", defaultValue = "ua") String country) throws IOException {

        return getRequest("&q=" + question + "&c=" + country);
    }


    @GetMapping("/api/news/name/xml")
    String getNewsXml(@RequestParam(value = "question") String question, @RequestParam(value = "country", defaultValue = "ua") String country) throws IOException {
        return getXMLRequest("&q=" + question + "&c=" + country);
    }

    private static Map<String, Object> getRequest(String parameters) throws IOException {
        String sURL = baseURL + parameters;

        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("GET");
        request.connect();

        Map<String, Object> result = parseJSON(request.getInputStream());

        request.disconnect();

        return result;
    }

    private static Map<String, Object> parseJSON(InputStream stream) throws IOException {
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

        return jsonObject.toMap();
    }

    private static String getXMLRequest(String parameters) throws IOException {
        String sURL = baseURL + parameters;

        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection)url.openConnection();
        request.setRequestMethod("GET");
        request.connect();

        String result = parseXML(request.getInputStream());

        request.disconnect();

        return result;
    }

    private static String parseXML(InputStream stream) throws IOException {
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
        String xml = null;
        try {
            xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n<root>" + XML.toString(jsonObject) + "</root>";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return xml;
    }
}