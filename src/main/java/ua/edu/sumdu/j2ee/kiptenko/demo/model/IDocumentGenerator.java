package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface IDocumentGenerator {
    ResponseEntity<InputStreamResource> getDocument(String keyWord) throws IOException;
}
