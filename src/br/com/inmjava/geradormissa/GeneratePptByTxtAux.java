package br.com.inmjava.geradormissa;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

public class GeneratePptByTxtAux {

	public static void main(String[] args) throws IOException {
		FileInputStream stream = new FileInputStream("txtaux.txt");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		String linha = br.readLine();

		// create a new empty slide show
		SlideShow ppt = new SlideShow();

		StringBuffer sb = new StringBuffer();
		boolean isTitleMomento = false;

		while (linha != null) {
			if (linha.equals("---")) {
				if (isTitleMomento) {
					createTitleMomento(ppt, sb.toString());
				} else {
					createTitle(ppt, sb);
				}
				isTitleMomento = false;
				sb = new StringBuffer();
			} else {

				if (linha.startsWith("*")) {
					sb.append(linha.substring(1));
					isTitleMomento = true;
				} else {
					sb.append(linha);
				}
			}
			linha = br.readLine();
			System.out.println(linha);
		}

		saveppt(ppt);
	}

	private static void saveppt(SlideShow ppt) throws FileNotFoundException, IOException {
		FileOutputStream out;
		out = new FileOutputStream("cantos-ppt.ppt");
		ppt.write(out);
		out.close();
	}

	private static void createTitleMomento(SlideShow ppt, String momento) {
		Slide s1 = ppt.createSlide();
		TextBox title = s1.addTitle();
		String strSlide = momento;
		title.setText("\n\n\n" + strSlide);
		title.setHorizontalAlignment(TextBox.AlignCenter);
		title.setVerticalAlignment(TextBox.AnchorMiddleCentered);
		RichTextRun rt = title.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(80);
		rt.setFontColor(new Color(63, 138, 112));
		rt.setBold(true);
	}

	private static void createTitle(SlideShow ppt, StringBuffer sb) {
		Slide s1 = ppt.createSlide();
		TextBox title = s1.addTitle();
		String strSlide = sb.toString();
		boolean refrao = false;
		if(strSlide.startsWith("$")){
			strSlide = strSlide.substring(1);
			refrao = true;
		}
		title.setText(strSlide);
		title.setHorizontalAlignment(TextBox.AlignLeft);
		RichTextRun rt = title.getTextRun().getRichTextRuns()[0];
		if(strSlide.length() < 71){
			rt.setFontSize(72);
		} else if(strSlide.length() < 80){
			rt.setFontSize(66);
		} else if(strSlide.length() < 103){
			rt.setFontSize(60);
		} else if(strSlide.length() < 115){
			rt.setFontSize(54);
		} else if(strSlide.length() < 155){
			rt.setFontSize(48);
		} else if(strSlide.length() < 193){
			rt.setFontSize(44);
		} else {
			rt.setFontSize(40);
		}
		rt.setFontName("Arial");
		if(refrao){
//			rt.setFontColor(Color.red);
			rt.setFontColor(new Color(219, 75, 94));
		}
//		rt.setBold(true);
//		rt.setItalic(true);
//		rt.setUnderlined(true);
//		rt.setAlignment(TextBox.AlignRight);
	}

}
