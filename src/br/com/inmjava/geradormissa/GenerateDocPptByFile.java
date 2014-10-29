package br.com.inmjava.geradormissa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class GenerateDocPptByFile {

	public static void main(String[] args) throws IOException {
		FileInputStream stream = new FileInputStream("cantos.txt");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		String linha = br.readLine();
		
		StringBuffer txtAux = new StringBuffer();
		
		XWPFDocument doc = new XWPFDocument();
		
		//create a new empty slide show
	    SlideShow ppt = new SlideShow();
		
		while(linha != null) {
			
			String momento = linha.split(";")[0];
			String linkMusica = linha.split(";")[1];
	        
	        Document jsoupDoc = Jsoup.connect(linkMusica).get();
	        String musicaPpt = "";
	        String musicaDocx = "";
	        if(linkMusica.contains("cancaonova")){
	        	for (Iterator<Element> iterator2 = jsoupDoc.select(".tab-pane#liturgia-2 p").iterator(); iterator2.hasNext();) {
	    			Element type = (Element) iterator2.next();
	    			musicaDocx += type.text()+"\n";
	    		}
	        	musicaPpt = jsoupDoc.select(".tab-pane#liturgia-2 p").get(1).text();
	        }else{
	        	musicaPpt = jsoupDoc.select("pre").text();
	        	musicaDocx = jsoupDoc.select("pre").text();
	        }
	        
	        gerarMusicaDocx(doc, momento, musicaDocx);
	        gerarMusicaPpt(ppt, momento, musicaPpt, txtAux);
	        
	        linha = br.readLine();
		}

		savedocx(doc);
        saveppt(ppt);
        savetxt(txtAux);
	}

	private static void savetxt(StringBuffer txtAux) throws IOException {
		String content = txtAux.toString();
		File file = new File("txtaux.txt");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
		
	}

	private static void saveppt(SlideShow ppt) throws FileNotFoundException,
			IOException {
		FileOutputStream out;
		out = new FileOutputStream("cantos-ppt.ppt");
        ppt.write(out);
        out.close();
	}

	private static void savedocx(XWPFDocument doc)
			throws FileNotFoundException, IOException {
		FileOutputStream out = new FileOutputStream("cantos-docx.docx");
        doc.write(out);
        out.close();
	}

	private static void gerarMusicaDocx(XWPFDocument doc, String momento, String musica) {
		//String arquivoCantosDocx = "\n" + jsoupDoc.select("pre").text() + "\n";
		String arquivoCantosDocx = "\n" + musica + "\n";

		XWPFParagraph p1 = doc.createParagraph();
		XWPFRun r1 = p1.createRun();
		
		r1.setText(momento);
		r1.setFontFamily("Arial");
		r1.setBold(true);
		r1.setFontSize(15);
		
		XWPFParagraph p2 = doc.createParagraph();
		XWPFRun r2 = p2.createRun();
		
		r2.setText(arquivoCantosDocx);
		
		r2.setFontFamily("Arial");
		r2.setFontSize(10);
	}

	private static void gerarMusicaPpt(SlideShow ppt, String momento, String musica, StringBuffer txtAux) {
		//String[] splitData = jsoupDoc.select("pre").text().split("\n");
		String[] splitData = musica.split("\n");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < splitData.length; i++) {
			String eachSplit = splitData[i];
			if((eachSplit.matches("(.*)(\\w{3,}+)(.*)(\\w{3,}+)(.*)") || eachSplit.equals("")) && !eachSplit.matches("(.*)(\\#)(.*)") && !eachSplit.matches("(.*)(7)(.*)") && !eachSplit.matches("(.*)(9)(.*)")){
				sb.append(eachSplit+"\n");
			}
		}
		String arquivoCantosPpt = "\n" + sb.toString() + "\n";
		arquivoCantosPpt = arquivoCantosPpt.replaceAll("(\\s)(\\s)+", "$1");
		arquivoCantosPpt = arquivoCantosPpt.replaceAll("(\\.)(\\.)+", "");
		arquivoCantosPpt = arquivoCantosPpt.replaceAll("^(\\s)", "");
		arquivoCantosPpt = arquivoCantosPpt.replaceAll("aa+", "a");
		arquivoCantosPpt = arquivoCantosPpt.replaceAll("ee+", "e");
		arquivoCantosPpt = arquivoCantosPpt.replaceAll("ii+", "i");
		arquivoCantosPpt = arquivoCantosPpt.replaceAll("oo+", "o");
		arquivoCantosPpt = arquivoCantosPpt.replaceAll("uu+", "u");
		arquivoCantosPpt = arquivoCantosPpt.replaceAll("jesus", "Jesus");
		arquivoCantosPpt = arquivoCantosPpt.replaceAll("senhor", "Senhor");
		arquivoCantosPpt = arquivoCantosPpt.replaceAll("deus", "Deus");
		
		createTitleMomento(ppt, momento, txtAux);
		
		splitData = arquivoCantosPpt.split("\n");
		sb = new StringBuffer();
		int i;
		for (i = 0; i < splitData.length; i++) {
			if(i != 0 && i % 4 == 0){
				String slide = sb.toString();
				createTitle(ppt, slide, txtAux);
			    sb = new StringBuffer();
			}
			if(splitData[i].endsWith(",") || splitData[i].endsWith(".")){
				sb.append(splitData[i]+" ");
			}else{
				sb.append(splitData[i]+" / ");
			}
		}
		i--;
		if(!(i != 0 && i % 4 == 0)){
			String slide = sb.toString();
			createTitle(ppt, slide, txtAux);
		}
		
//		for (i = 0; i < splitData.length; i++) {
//			if(i % 4 == 0){
//			    sb = new StringBuffer();
//			    sb.append(splitData[i-4]);
//			    sb.append(" " + splitData[i-3]);
//			    sb.append(" " + splitData[i-2]);
//			    sb.append(" " + splitData[i-1]);
//			    createTitle(ppt, sb, txtAux);
//			}
//		}
//	    sb = new StringBuffer();
//		// Esse switch não usa break, o comportamento é esperado =D
//		i--;
//		switch (i % 4) {
//			case 3:sb.append(" " + splitData[i-3]);
//			case 2:sb.append(" " + splitData[i-2]);
//			case 1:sb.append(" " + splitData[i-1]);
//			createTitle(ppt, sb, txtAux); // não é default
//		}

	    createTitle(ppt, "", txtAux);
		
//		if(!sb.toString().equals("")){
//		    createTitle(ppt, sb);
//		}
	}

	private static void createTitleMomento(SlideShow ppt, String momento, StringBuffer txtAux) {
		Slide s1 = ppt.createSlide();
		TextBox title = s1.addTitle();
		String strSlide = momento;
		title.setText("\n\n\n\n\n\n" + strSlide);
		txtAux.append("*"+strSlide);
		txtAux.append("\n---\n");
		title.setHorizontalAlignment(TextBox.AlignCenter);
		title.setVerticalAlignment(TextBox.AnchorMiddleCentered);
	}

	private static void createTitle(SlideShow ppt, String slide, StringBuffer txtAux) {
		if(slide.endsWith("/ ")){
			slide = slide.substring(0, slide.length() - 2);
		}
		Slide s1 = ppt.createSlide();
		TextBox title = s1.addTitle();
		String strSlide = slide;
		title.setText(strSlide);
		txtAux.append(strSlide);
		txtAux.append("\n---\n");
		title.setHorizontalAlignment(TextBox.AlignLeft);
	}

}
