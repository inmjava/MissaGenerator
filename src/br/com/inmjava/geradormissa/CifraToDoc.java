package br.com.inmjava.geradormissa;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class CifraToDoc {
	
	public static void main(String[] args) throws IOException, InterruptedException {

		XWPFDocument doc = new XWPFDocument();

        XWPFParagraph p = doc.createParagraph();

        XWPFRun run = p.createRun();
        
        Document doc2 = Jsoup.connect("http://www.cifraclub.com.br/celina-borges/lava-me/imprimir.html").get();

        run.setText(doc2.select("pre").text());
        run.setFontFamily("Arial");
        run.setFontSize(10);

        FileOutputStream out = new FileOutputStream("simple.docx");
        doc.write(out);
        out.close();
	}

}
