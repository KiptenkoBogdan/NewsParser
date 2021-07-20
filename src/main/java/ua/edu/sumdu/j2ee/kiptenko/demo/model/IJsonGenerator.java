package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public interface IJsonGenerator {
    Map<String, Object> getJson(String keyWord) throws IOException;
}
