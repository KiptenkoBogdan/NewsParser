package ua.edu.sumdu.j2ee.kiptenko.demo.converter;

import java.io.*;
import java.net.URISyntaxException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import ua.edu.sumdu.j2ee.kiptenko.demo.model.NewsPojo;

public class DocxGenerator {
    public static byte[] generateTemplate(NewsPojo nptest) throws IOException, URISyntaxException {
        XWPFDocument document = new XWPFDocument();
        File template;
        FileOutputStream out;
        try{
            template = new File(DocxGenerator.class.getClassLoader().getResource("template.docx").toURI());
            if(!template.exists()){
                template.createNewFile();
            }else{
                System.out.println("File already exists");
            }
            out = new FileOutputStream(template);

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setFontSize(14);
            run.setFontFamily("TimesNewRoman");
            for(int i = 0; i < nptest.getSources().length; i++) {
                run.setText("\nShort Description: " + nptest.getSources()[i].getDescription());
                run.addCarriageReturn();
                run.setText("\nOriginal Link: " + nptest.getSources()[i].getUrl());
                run.addCarriageReturn();
                run.setText("\nCategory: " + nptest.getSources()[i].getCategory());
                run.addCarriageReturn();
                run.setText("\nCountry: " + nptest.getSources()[i].getCountry());
                run.addCarriageReturn();
                run.setText("\nLanguage: " + nptest.getSources()[i].getLanguage());
                run.addCarriageReturn();
                run.addCarriageReturn();
            }
            document.write(out);
            out.close();
        } catch (NullPointerException | FileNotFoundException e){
            System.out.println(e);
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            document.write(stream);
        } catch (IOException e) {
            System.out.println("Error with writing: " + e);
            return null;
        }
        return stream.toByteArray();
    }
}