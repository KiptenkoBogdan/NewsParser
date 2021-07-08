package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ua.edu.sumdu.j2ee.kiptenko.demo.converter.TemplateGenerator;
import ua.edu.sumdu.j2ee.kiptenko.demo.converter.JsonConverter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

@PropertySource({"classpath:application.properties"})
public class DocumentGenerator {

    @Autowired
    private Environment env;

    private static final Logger logger = Logger.getLogger(DocumentGenerator.class);
    private final JsonConverter jc = new JsonConverter();

    public ResponseEntity<InputStreamResource> getDocument(String baseURLsourses, String apiKey, String keyWord) throws IOException, URISyntaxException {
        //String str = jc.getStringJson(env.getProperty("baseURLsources"), env.getProperty("apiKey"), keyWord);
        String str = jc.getStringJson(baseURLsourses, apiKey, keyWord);
        NewsPojo nptest = jc.createObject(str);

        byte[] doc = TemplateGenerator.generateTemplate(nptest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "News.docx");
        headers.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.wordprocessingml.document"));
        headers.setContentLength(doc.length);
        InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(doc));
        logger.info("Filled in .doc template");
        return ResponseEntity.ok().headers(headers).body(inputStreamResource);
    }
}
