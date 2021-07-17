package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ua.edu.sumdu.j2ee.kiptenko.demo.converter.NewsPojoToDocTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;

//@PropertySource({"classpath:application.properties"})
@Component
public class DocumentGenerator implements IDocumentGenerator{

//    @Autowired
//    private Environment env;

    private static final Logger logger = Logger.getLogger(DocumentGenerator.class);

    @Autowired
    private IPojoGenerator pg;

    public ResponseEntity<InputStreamResource> getDocument(String baseURLsourses, String apiKey, String keyWord) throws IOException{
        String str = pg.getStringJson(baseURLsourses, apiKey, keyWord);
        NewsPojo nptest = pg.createObject(str);

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
