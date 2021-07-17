package ua.edu.sumdu.j2ee.kiptenko.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.sumdu.j2ee.kiptenko.demo.model.*;


import java.io.*;
import java.util.Map;

@RestController
@PropertySource({"classpath:application.properties"})
public class MainController {

    // localhost:8080/getDocument/bycategory?category=sports
    @Autowired
    private Environment env;

    private static final Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    private IDocumentGenerator docGen;
    @Autowired
    private IJsonGenerator jsonGen;
    @Autowired
    private IXmlGenerator xmlGen;

    //private static String baseURLsources = "https://newsapi.org/v2/sources";
    //private static String apiKey = "?apiKey=629909da42254cd28c952adb9d926de4";

    //News in .docx format
    @RequestMapping(value = "/getDocument/bycategory")
    public ResponseEntity<InputStreamResource> getNewsByCategoryDoc(
            @RequestParam(value = "category", defaultValue = "sports") String category) {
        ResponseEntity<InputStreamResource> result = null;
        try {
            result = docGen.getDocument(env.getProperty("baseURLsources"), env.getProperty("apiKey"),"&category=" + category);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    @RequestMapping(value = "/getDocument/bylanguage")
    public ResponseEntity<InputStreamResource> getNewsByLanguageDoc(
            @RequestParam(value = "language", defaultValue = "ua") String language) {
        ResponseEntity<InputStreamResource> result = null;
        try {
            result = docGen.getDocument(env.getProperty("baseURLsources"), env.getProperty("apiKey"),"&language=" + language);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    @RequestMapping(value = "/getDocument/bycountry")
    public ResponseEntity<InputStreamResource> getNewsByCountryDoc(
            @RequestParam(value = "country", defaultValue = "ua") String country) {
        ResponseEntity<InputStreamResource> result = null;
        try {
            result = docGen.getDocument(env.getProperty("baseURLsources"), env.getProperty("apiKey"),"&country=" + country);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    // localhost:8080/getJSON/bycategory?category=sports

    //News in JSON format
    @RequestMapping(value = "/getJSON/bycategory")
    public Map<String, Object> getNewsByCategoryJson(@RequestParam(value = "category", defaultValue = "sports") String category) {
        Map<String, Object> result = null;
        try {
            result = jsonGen.getJson(env.getProperty("baseURLsources"), env.getProperty("apiKey"),"&category=" + category);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    @RequestMapping(value = "/getJSON/bylanguage")
    public Map<String, Object> getNewsByLanguageJson(@RequestParam(value = "language", defaultValue = "ua") String language) {
        Map<String, Object> result = null;
        try {
            result = jsonGen.getJson(env.getProperty("baseURLsources"), env.getProperty("apiKey"),"&language=" + language);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    @RequestMapping(value = "/getJSON/bycountry")
    public Map<String, Object> getNewsByCountryJson(@RequestParam(value = "country", defaultValue = "ua") String country) {
        Map<String, Object> result = null;
        try {
            result = jsonGen.getJson(env.getProperty("baseURLsources"), env.getProperty("apiKey"),"&country=" + country);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }


    // localhost:8080/getXML/bycategory?category=sports

    //News in XML format

    @RequestMapping(value = "/getXML/bycategory", produces = MediaType.APPLICATION_XML_VALUE)
    public String getNewsByCategoryXML(@RequestParam(value = "category", defaultValue = "sports") String category) {
        String result = null;
        try {
            result = xmlGen.getXml(env.getProperty("baseURLsources"), env.getProperty("apiKey"),"&category=" + category);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    @RequestMapping(value = "/getXML/bylanguage", produces = MediaType.APPLICATION_XML_VALUE)
    public String getNewsByLanguageXML(@RequestParam(value = "language", defaultValue = "ua") String language) {
        String result = null;
        try {
            result = xmlGen.getXml(env.getProperty("baseURLsources"), env.getProperty("apiKey"),"&language=" + language);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    @RequestMapping(value = "/getXML/bycountry", produces = MediaType.APPLICATION_XML_VALUE)
    public String getNewsByCountryXML(@RequestParam(value = "country", defaultValue = "ua") String country) {
        String result = null;
        try {
            result = xmlGen.getXml(env.getProperty("baseURLsources"), env.getProperty("apiKey"),"&country=" + country);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

//    @RequestMapping(value = "/getXML/bycategory")
//    public Map<String, String> getNewsByCategoryXML(@RequestParam(value = "category", defaultValue = "sports") String category) {
//        Map<String, String> result = null;
//        try {
//            result = xmlGen.getXml(env.getProperty("baseURLsources"), env.getProperty("apiKey"),"&category=" + category);
//        } catch (IOException e) {
//            logger.error(e);
//        }
//        return result;
//    }
//
//    @RequestMapping(value = "/getXML/bylanguage")
//    public Map<String, String> getNewsByLanguageXML(@RequestParam(value = "language", defaultValue = "ua") String language) {
//        Map<String, String> result = null;
//        try {
//            result = xmlGen.getXml(env.getProperty("baseURLsources"), env.getProperty("apiKey"),"&language=" + language);
//        } catch (IOException e) {
//            logger.error(e);
//        }
//        return result;
//    }
//
//    @RequestMapping(value = "/getXML/bycountry")
//    public Map<String, String> getNewsByCountryXML(@RequestParam(value = "country", defaultValue = "ua") String country) {
//        Map<String, String> result = null;
//        try {
//            result = xmlGen.getXml(env.getProperty("baseURLsources"), env.getProperty("apiKey"),"&country=" + country);
//        } catch (IOException e) {
//            logger.error(e);
//        }
//        return result;
//    }

}