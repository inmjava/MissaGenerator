package br.com.inmjava.geradormissa;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class CifraToDocx {
	
	public static void main(String[] args) throws IOException, InterruptedException {

		XWPFDocument doc = new XWPFDocument();
		
	    XWPFParagraph p1 = doc.createParagraph();

        XWPFRun r1 = p1.createRun();
        
        Document doc2 = Jsoup.connect("http://www.cifraclub.com.br/celina-borges/lava-me/imprimir.html").get();

        r1.setText(doc2.select(".infos_cifra h1").text());
        r1.setText(doc2.select("pre").text());
        r1.setFontFamily("Arial");
        r1.setFontSize(10);

        FileOutputStream out = new FileOutputStream("simple.docx");
        doc.write(out);
        out.close();
	}

}
