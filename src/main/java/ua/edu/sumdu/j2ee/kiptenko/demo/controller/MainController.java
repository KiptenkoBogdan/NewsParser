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

@RestController
@PropertySource({"classpath:application.properties"})
public class MainController {

    @Autowired
    private Environment env;

    private static final Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    private IDocumentGenerator docGen;
    @Autowired
    private IPojoGenerator pg;

    //private static String baseURLsources = "https://newsapi.org/v2/sources";
    //private static String apiKey = "?apiKey=629909da42254cd28c952adb9d926de4";

    //News in .docx format
    @RequestMapping(value = "/getDocument/bycategory")
    public ResponseEntity<InputStreamResource> getNewsByCategoryDoc(
            @RequestParam(value = "category", defaultValue = "sports") String category) {
        ResponseEntity<InputStreamResource> result = null;
        NewsPojo np = null;
        try {
            np = pg.createObject("&category=" + category);
            result = docGen.getDocument(np);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    @RequestMapping(value = "/getDocument/bylanguage")
    public ResponseEntity<InputStreamResource> getNewsByLanguageDoc(
            @RequestParam(value = "language", defaultValue = "ua") String language) {
        ResponseEntity<InputStreamResource> result = null;
        NewsPojo np = null;
        try {
            np = pg.createObject("&language=" + language);
            result = docGen.getDocument(np);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    @RequestMapping(value = "/getDocument/bycountry")
    public ResponseEntity<InputStreamResource> getNewsByCountryDoc(
            @RequestParam(value = "country", defaultValue = "ua") String country) {
        ResponseEntity<InputStreamResource> result = null;
        NewsPojo np = null;
        try {
            np = pg.createObject("&country=" + country);
            result = docGen.getDocument(np);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    // localhost:8080/getJSON/bycategory?category=sports

    //News in JSON format

    @RequestMapping(value = "/getJSON/bycategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public NewsPojo getNewsByCategoryJson(@RequestParam(value = "category", defaultValue = "sports") String category) {
        NewsPojo result = null;
        try {
            result = pg.createObject("&category=" + category);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    @RequestMapping(value = "/getJSON/bylanguage", produces = MediaType.APPLICATION_JSON_VALUE)
    public NewsPojo  getNewsByLanguageJson(@RequestParam(value = "language", defaultValue = "ua") String language) {
        NewsPojo result = null;
        try {
            result = pg.createObject("&language=" + language);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    @RequestMapping(value = "/getJSON/bycountry", produces = MediaType.APPLICATION_JSON_VALUE)
    public NewsPojo  getNewsByCountryJson(@RequestParam(value = "country", defaultValue = "ua") String country) {
        NewsPojo result = null;
        try {
            result = pg.createObject("&country=" + country);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }


    // localhost:8080/getXML/bycategory?category=sports

    //News in XML format

    @RequestMapping(value = "/getXML/bycategory", produces = MediaType.APPLICATION_XML_VALUE)
    public NewsPojo getNewsByCategoryXML(@RequestParam(value = "category", defaultValue = "sports") String category) {
        NewsPojo result = null;
        try {
            result = pg.createObject("&category=" + category);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    @RequestMapping(value = "/getXML/bylanguage", produces = MediaType.APPLICATION_XML_VALUE)
    public NewsPojo getNewsByLanguageXML(@RequestParam(value = "language", defaultValue = "ua") String language) {
        NewsPojo result = null;
        try {
            result = pg.createObject("&language=" + language);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    @RequestMapping(value = "/getXML/bycountry", produces = MediaType.APPLICATION_XML_VALUE)
    public NewsPojo getNewsByCountryXML(@RequestParam(value = "country", defaultValue = "ua") String country) {
        NewsPojo result = null;
        try {
            result = pg.createObject("&country=" + country);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }
}