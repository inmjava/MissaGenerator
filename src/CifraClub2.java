
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class CifraClub2 {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Document doc = Jsoup.connect("http://www.cifraclub.com.br/celina-borges/lava-me/imprimir.html").get();
		String[] splitData = doc.select("pre").text().split("\n");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < splitData.length; i++) {
			String eachSplit = splitData[i];
			if(eachSplit.matches("(.*)(\\w{4,}+)(.*)") || eachSplit.equals("")){
				sb.append(eachSplit+"\n");
			}
		}
		System.out.println(sb.toString());
	}

}
