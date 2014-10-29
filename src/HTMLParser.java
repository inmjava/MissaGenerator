
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class HTMLParser {
	
	public static void main(String[] args) throws IOException, InterruptedException {

		for (int i = 24; i < 32; i++) {
			
			String ida = (i < 10 ? "0" + i : i+"");
			//String volta = (i+1 < 10 ? "0" + (i+1) : (i+1)+"");
			
			boolean encontrou = false;
			
			while (!encontrou) {

				try{
					Document doc = Jsoup.connect("http://www.mundi.com.br/flightmetasearch?airport1=CWB&airport2=VIX&triptype=2&date1=" + ida + "/09/2014&numadults=1&numbabies=0&numchildren=0&page=1&sortType=2&currency=BRL&cabintype=null").get();
					Elements lowestPrice = doc.select(".lowest-price");
					Elements companhia = doc.select(".prime-logo");
					System.out.println(ida + " = " + lowestPrice.get(0).text() + " cia " + companhia.get(0).attr("alt"));
					encontrou = true;
				} catch (Exception e){
					// Não encontrou... vai tentar novamente
				}
				
			}
		}
		
	}

}
