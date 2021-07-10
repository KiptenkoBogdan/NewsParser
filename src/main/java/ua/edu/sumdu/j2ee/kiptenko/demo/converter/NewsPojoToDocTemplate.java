package ua.edu.sumdu.j2ee.kiptenko.demo.converter;

import java.io.*;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.edu.sumdu.j2ee.kiptenko.demo.model.NewsPojo;

@Component
public class NewsPojoToDocTemplate implements Converter<NewsPojo, byte[]> {

    private static final Logger logger = Logger.getLogger(NewsPojoToDocTemplate.class);

    @Override
    public byte[] convert(NewsPojo nptest){
        XWPFDocument document = new XWPFDocument();
        File template;
        FileOutputStream out;
        try{
            template = new File(NewsPojoToDocTemplate.class.getClassLoader().getResource("template.docx").toURI());
            if(!template.exists()){
                template.createNewFile();
            }else{
                logger.error("File already exists");
            }
            out = new FileOutputStream(template);

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setFontSize(14);
            run.setFontFamily("TimesNewRoman");
            for(int i = 0; i < nptest.getSources().size(); i++) {
                run.setText("\nTitle: " + nptest.getSources().get(i).getTitle());
                run.addCarriageReturn();
                run.setText("\nShort Description: " + nptest.getSources().get(i).getDescription());
                run.addCarriageReturn();
                run.setText("\nOriginal Link: " + nptest.getSources().get(i).getUrl());
                run.addCarriageReturn();
                run.setText("\nAuthor: " + nptest.getSources().get(i).getAuthor());
                run.addCarriageReturn();
                run.setText("\nRelated Image: " + nptest.getSources().get(i).getUrlToImage());
                run.addCarriageReturn();
                run.addCarriageReturn();
            }
            document.write(out);
            out.close();
        } catch (NullPointerException | IOException | URISyntaxException e){
            logger.error(e);
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            document.write(stream);
        } catch (IOException e) {
            logger.error("Error with writing: " + e);
            return null;
        }
        logger.info("Generated .doc template");

        return stream.toByteArray();
    }
}