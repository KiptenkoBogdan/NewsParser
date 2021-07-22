package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ua.edu.sumdu.j2ee.kiptenko.demo.converter.NewsPojoToDocTemplate;

import java.io.ByteArrayInputStream;

@Component
public class DocumentGenerator implements IDocumentGenerator{

    private static final Logger logger = Logger.getLogger(DocumentGenerator.class);

    @Value("${baseURLsources}")
    private String baseURLsources;

    @Value("${apiKey}")
    private String apiKey;

    @Autowired
    private IPojoGenerator pg;

    public ResponseEntity<InputStreamResource> getDocument(NewsPojo nptest) {

        GenericConversionService conversionService = new GenericConversionService();
        Converter<NewsPojo, byte[]> customConverter = new NewsPojoToDocTemplate();
        conversionService.addConverter(customConverter);

        byte[] doc = conversionService.convert(nptest, byte[].class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "News.docx");
        headers.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.wordprocessingml.document"));
        headers.setContentLength(doc.length);
        InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(doc));
        logger.info("Filled in .doc template");
        return ResponseEntity.ok().headers(headers).body(inputStreamResource);
    }
}
