package br.com.inmjava.geradormissa;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.SlideShow;


public class CifraToPpt {
	
	public static void main(String[] args) throws IOException, InterruptedException {

		//create a new empty slide show
	    SlideShow ppt = new SlideShow();

	    //add first slide
	    Slide s1 = ppt.createSlide();
	    
	    TextBox title = s1.addTitle();
	    title.setText("Hello, World!\n" +
	    		"Hello, World!\n" +
	    		"Hello, World!\n" +
	    		"Hello, World!\n" +
	    		"Hello, World!\n" +
	    		"Hello, World!\n" +
	    		"Hello, World!\n" +
	    		"Hello, World!\n" +
	    		"Hello, World!\n" +
	    		"Hello, World!\n" +
	    		"Hello, World!\n" +
	    		"");

	    //add second slide
	    Slide s2 = ppt.createSlide();
	    
	    title = s2.addTitle();
	    title.setText("Hello, World!");
	    
	    //save changes in a file
	    FileOutputStream out = new FileOutputStream("slideshow.ppt");
	    ppt.write(out);
	    out.close();
	                 
	}

}
