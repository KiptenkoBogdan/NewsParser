package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface IPojoGenerator {
    NewsPojo createObject(String keyWord) throws IOException;
    String getStringJson(String parameters) throws IOException;
}
