package ua.edu.sumdu.j2ee.kiptenko.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.sumdu.j2ee.kiptenko.demo.model.DocumentGenerator;

import java.io.*;
import java.net.URISyntaxException;

@RestController
@PropertySource({"classpath:application.properties"})
public class MainController {

    // localhost:8080/getDocument/bycategory?category=sports
    @Autowired
    private Environment env;

    private DocumentGenerator docGen = new DocumentGenerator();

    //private static String baseURLsources = "https://newsapi.org/v2/sources";
    //private static String apiKey = "?apiKey=629909da42254cd28c952adb9d926de4";

    @RequestMapping(value = "/getDocument/bycategory")
    public ResponseEntity<InputStreamResource> getNewsByCategory(
            @RequestParam(value = "category", defaultValue = "sports") String category) {
        ResponseEntity<InputStreamResource> result = null;
        try {
            result = docGen.getDocument("&category=" + category);
        } catch (IOException | URISyntaxException e) {
            System.out.println(e);
        }
        return result;
    }

    @RequestMapping(value = "/getDocument/bylanguage")
    public ResponseEntity<InputStreamResource> getNewsByLanguage(
            @RequestParam(value = "language", defaultValue = "ua") String language) {
        ResponseEntity<InputStreamResource> result = null;
        try {
            result = docGen.getDocument("&language=" + language);
        } catch (IOException | URISyntaxException e) {
            System.out.println(e);
        }
        return result;
    }

    @RequestMapping(value = "/getDocument/bycountry")
    public ResponseEntity<InputStreamResource> getNewsByCountry(
            @RequestParam(value = "country", defaultValue = "ua") String country) {
        ResponseEntity<InputStreamResource> result = null;
        try {
            result = docGen.getDocument("&country=" + country);
        } catch (IOException | URISyntaxException e) {
            System.out.println(e);
        }
        return result;
    }
    //    private static JSONObject getRequest(String parameters) throws IOException {
//        String sURL = baseURLsources + apiKey + parameters;
//
//        URL url = new URL(sURL);
//        HttpURLConnection request = (HttpURLConnection) url.openConnection();
//        request.setRequestMethod("GET");
//        request.connect();
//
//        JSONObject result = JsonConverter.parseJSON(request.getInputStream());
//
//        request.disconnect();
//
//        return result;
//    }

//    @GetMapping("/api/news/bycountry/xml")
//    String getNewsByCountryXml(@RequestParam(value = "country") String country) throws IOException {
//        return getXMLRequest("&country=" + country);
//    }
//    @GetMapping("/api/news/bycategory/xml")
//    String getNewsByCategotyXml(@RequestParam(value = "category") String category) throws IOException {
//        return getXMLRequest("&category=" + category);
//    }
//    @GetMapping("/api/news/bylanguage/xml")
//    String getNewsByLanguageXml(@RequestParam(value = "language") String language) throws IOException {
//        return getXMLRequest("&language=" + language);
//    }

//    private static String getXMLRequest(String parameters) throws IOException {
//        String sURL = baseURLsources + parameters;
//
//        URL url = new URL(sURL);
//        HttpURLConnection request = (HttpURLConnection)url.openConnection();
//        request.setRequestMethod("GET");
//        request.connect();
//
//        String result = parseXML(request.getInputStream());
//
//        request.disconnect();
//
//        return result;
//    }

//    private static String parseXML(InputStream stream) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
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
//        String xml = null;
//        try {
//            xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n<root>" + XML.toString(jsonObject) + "</root>";
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return xml;
//    }
}