package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface IXmlGenerator {
    String getXml(String keyWord) throws IOException;
}
