package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface IDocumentGenerator {
    ResponseEntity<InputStreamResource> getDocument(NewsPojo nptest);
}
