package ua.edu.sumdu.j2ee.kiptenko.demo.converter;

import java.io.*;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import ua.edu.sumdu.j2ee.kiptenko.demo.model.NewsPojo;

public class TemplateGenerator {

    private static final Logger logger = Logger.getLogger(TemplateGenerator.class);

    public static byte[] generateTemplate(NewsPojo nptest) throws IOException, URISyntaxException {
        XWPFDocument document = new XWPFDocument();
        File template;
        FileOutputStream out;
        try{
            template = new File(TemplateGenerator.class.getClassLoader().getResource("template.docx").toURI());
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
                run.setText("\nShort Description: " + nptest.getSources().get(i).getDescription());
                run.addCarriageReturn();
                run.setText("\nOriginal Link: " + nptest.getSources().get(i).getUrl());
                run.addCarriageReturn();
                run.setText("\nCategory: " + nptest.getSources().get(i).getCategory());
                run.addCarriageReturn();
                run.setText("\nCountry: " + nptest.getSources().get(i).getCountry());
                run.addCarriageReturn();
                run.setText("\nLanguage: " + nptest.getSources().get(i).getLanguage());
                run.addCarriageReturn();
                run.addCarriageReturn();
            }
            document.write(out);
            out.close();
        } catch (NullPointerException | FileNotFoundException e){
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