package br.com.inmjava.geradormissa;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class CifraClub3 {
	
	public static void main(String[] args) throws IOException, InterruptedException {

		Document doc = Jsoup.connect("http://www.cifraclub.com.br/maria-do-rosario/louvando-a-maria/imprimir.html").get();
		System.out.println(doc);
		
	}

}
