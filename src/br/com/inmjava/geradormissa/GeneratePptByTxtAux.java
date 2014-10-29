package br.com.inmjava.geradormissa;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.SlideShow;


public class GeneratePptByTxtAux {

	public static void main(String[] args) throws IOException {
		FileInputStream stream = new FileInputStream("txtaux.txt");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		String linha = br.readLine();
		
		//create a new empty slide show
	    SlideShow ppt = new SlideShow();
		
	    StringBuffer sb = new StringBuffer();
	    boolean isTitleMomento = false;
	    
		while(linha != null) {
			if(linha.equals("---")){
				if(isTitleMomento){
					createTitleMomento(ppt, sb.toString());
				}else{
					createTitle(ppt, sb);
				}
			    isTitleMomento = false;
			    sb = new StringBuffer();
			}else {
			
				if(linha.startsWith("*")){
					sb.append(linha.substring(1));
					isTitleMomento = true;
				}else{
					sb.append(linha);
				}
			}
	        linha = br.readLine();
			System.out.println(linha);
		}

        saveppt(ppt);
	}

	private static void saveppt(SlideShow ppt) throws FileNotFoundException,
			IOException {
		FileOutputStream out;
		out = new FileOutputStream("cantos-ppt.ppt");
        ppt.write(out);
        out.close();
	}

	private static void createTitleMomento(SlideShow ppt, String momento) {
		Slide s1 = ppt.createSlide();
		TextBox title = s1.addTitle();
		String strSlide = momento;
		title.setText("\n\n\n\n\n\n" + strSlide);
		title.setHorizontalAlignment(TextBox.AlignCenter);
		title.setVerticalAlignment(TextBox.AnchorMiddleCentered);
	}

	private static void createTitle(SlideShow ppt, StringBuffer sb) {
		Slide s1 = ppt.createSlide();
		TextBox title = s1.addTitle();
		String strSlide = sb.toString();
		title.setText(strSlide);
		title.setHorizontalAlignment(TextBox.AlignLeft);
	}

}
