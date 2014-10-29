package br.com.inmjava.geradormissa;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class CifraClub {
	
	public static void main(String[] args) throws IOException, InterruptedException {

		Document doc = Jsoup.connect("http://liturgia.cancaonova.com/liturgia/30a-semana-comum-quarta-feira-29102014/").get();

		for (Iterator<Element> iterator2 = doc.select(".tab-pane#liturgia-2 p").iterator(); iterator2.hasNext();) {
			Element type = (Element) iterator2.next();
			System.out.println(type.text());
		}
		
	}

}
