package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface IPojoGenerator {
    NewsPojo createObject(String json);
    Pojo createPojo(JSONObject obj);
    String getStringJson(String baseURLsources, String apiKey, String parameters) throws IOException;
}
