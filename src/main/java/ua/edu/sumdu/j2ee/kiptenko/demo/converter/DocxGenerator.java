package ua.edu.sumdu.j2ee.kiptenko.demo.converter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import ua.edu.sumdu.j2ee.kiptenko.demo.model.NewsPojo;

public class DocxGenerator {
    public static byte[] generateTemplate(NewsPojo nptest) throws IOException, URISyntaxException {
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File(DocxGenerator.class.getClassLoader().getResource("template.docx").toURI()));

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