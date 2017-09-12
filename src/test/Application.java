package test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import t12.treedata.subunits.Crawler;
import t12.treedata.subunits.Word;
import t12.treedata.tokenizer.Encoding;
import t12.treedata.tokenizer.SimpleTokenizer;

public class Application {

	public static void main(String[] args) throws IOException {
		List<Word> token = new ArrayList<Word>();
		Set<String> stopwords = new HashSet<String>();
		
		SimpleTokenizer tnizer = new SimpleTokenizer();
		Crawler crawl = new Crawler();
		
		File data = new File("C:/Users/Admin/workspace/T12_DanielJanz/data");			//Dateiverzeichnis, das eingelesen werden soll
		crawl.showFiles(data);
//		File vergleich = new File("C:/Users/Admin/workspace/DocumentAnalyzer/StopWords.txt");	
//		System.out.println(crawl.getFiles(data));										//Ausgabe des Dateiverzeichnisses als Liste

		
		List<String> words = tnizer.getTokens(crawl.getFiles(data), Encoding.UTF_8);	//Die Methode getTokens gibt nur Buchstabenfolgen, keine Zeichen aus
		words.add("e");
//		System.out.println("Liste: " +words);
//		List<String> badwords = tnizer.getTokens(vergleich, Encoding.UTF_8);	
//		words.removeAll(badwords);
		InSetEinfuegen(token,words);
		
		WoerterAusgeben(token);
//		System.out.println("Set:  " + token);
		
	}
	
	/**
	 * Überführt die Liste in eine HashMap<Letter, count>
	 * 
	 * 1.) Sortiert die Liste
	 * 2.) Zählt die Anzahl des jeweiligen Wortes.
	 * 
	 * @param token, words
	 */
	private static void InSetEinfuegen(List<Word> token,List<String> words) {
		Collections.sort(words);							//Sortiert die Liste words
		String currentWord = words.get(0);					//erzeugt einen String mit dem aktuellen Wort
		int wordCount = 1;									//Legt die Anzahl des Wortes fest
		for(int i=1;i<words.size();i++) {					//Läuft über die Wörter
//			System.out.println(words.get(i));
			if(i == words.size()-1){						//Wenn das vorlestzte Wort erreicht ist, soll das letzte als letztes zugefügt werden
				token.add(new Word(words.get(i),wordCount));		//funktioniert!!!!
			}
			if(!currentWord.equals(words.get(i))){				//Wenn das neue Wort dem vorherigen nicht entspricht
				token.add(new Word(currentWord,wordCount));	//dann füge der sortierten Liste ein neues Element hinzu
				wordCount = 1;									//Die Wortanzahl wird dann zurückgesetzt
			}else{
				wordCount++;									//ansonsten wird der Wortcount um 1 hochgezählt
			}
			currentWord = words.get(i);
			//System.out.println("Wort: " + currentWord + " Anzahl:" + wordCount);
		}			
	}
	
	
	private static void WoerterAusgeben(List<Word> token){
		Iterator it = token.iterator();
		Word temp;
		while (it.hasNext()){
		
			temp = (Word) it.next();
			
			System.out.println(temp.getWord() + " " + temp.getCount());
		}

	}

}
