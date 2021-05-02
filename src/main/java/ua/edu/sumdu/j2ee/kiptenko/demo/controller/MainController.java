package ua.edu.sumdu.j2ee.kiptenko.demo.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URISyntaxException;

@RestController
public class MainController {
    private static final String baseURLsources = "https://newsapi.org/v2/sources";
    private static final String apiKey = "?apiKey=629909da42254cd28c952adb9d926de4";

    @RequestMapping(value = "/getDocument/bycategory")
    public ResponseEntity<InputStreamResource> getNewsByCategory(
            @RequestParam(value = "category", defaultValue = "sports") String category) {
        ResponseEntity<InputStreamResource> result = null;
        try {
            result = DocumentController.getDocument(baseURLsources, apiKey,"&category=" + category);
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
            result = DocumentController.getDocument(baseURLsources, apiKey, "&language=" + language);
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
            result = DocumentController.getDocument(baseURLsources, apiKey, "&country=" + country);
        } catch (IOException | URISyntaxException e) {
            System.out.println(e);
        }
        return result;
    }
}