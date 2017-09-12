package test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import t12.treedata.LexReader;
import t12.treedata.subunits.Letter;
import t12.treedata.subunits.Word;

/**
 * ENGINE
 * Soll die eingelesenen Wörter in Buchstaben aufschneiden.
 * Gleichzeitig sollen den Buchstaben die entsprechenden Häufigkeiten
 * in Abhängigkeit von ihrer Position in einem Wort zugewiesen werden.
 * 
 * Datenstruktur:
 * Wortebene: 		Klasse mit Inhalt: String => Wort, Count (Wie oft kommmt das Wort vor)
 * 								s. Klasse Words
 * Zwischenebene: 	So viele wie Anzahl Buchstaben -1: Wortpart (z.B. "Ha" von "Hallo")
 * 						Hashmap => Enthält andere Zwischenebenen oder Wortebene,
 * 						Count => Funktion, die count von allen Unterebenen abruft und summiert
 * 								s. Klasse Letter
 * 
 * Damit ist der Ausgabedatentyp eine HashMap.
 * 
 * @author Daniel Janz
 */

public class ChopperBackup extends LexReader {
	
//	abstract boolean isLetter(String untersuchungsobjekt);	//Debug-Variable - Soll überprüfen können, ob ein eingegebenes Objekt ein Buchstabe ist
	
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {		//Debug-Main-Methode
		
		//Variables//
		LexReader lex = new LexReader();					//Ruft den LexReader auf
		List<Word> words = new ArrayList<Word>();			//Erzeugt eine neue Liste
		String pathToFile = "C:/Users/Admin/workspace/T12_DanielJanz/data";		//Debugging - gibt den Pfad zu dem Verzeichnis ein.
		Map<String,Letter> lexicon = new HashMap<String,Letter>();
		
		
		//Methods//
		words = lex.readData(pathToFile);					//Schreibt alle Dateien aus der Methode readData aus dem LexReader in words rein
//		lex.printWords(words);								//Debug-Methode - was ist in der Liste words drin?
		lexicon = createLexicon(words);						//Erstelle das Lexikon
	}
	
	
	/**
	 * Die Methode createLexicon() soll aus der eingelesenen Wortliste das fertige Lexikon erstellen.
	 * Das Lexikon ist es, das am Ende als Datei gespeichert werden soll.
	 * Hier sollen die Worte nach folgenden Parametern geordnet sein:
	 * 
	 * Zunächst die Buchstaben in Abhängigkeit ihrer Position in den jeweiligen Worten.
	 * Dann jedem Buchstaben die entsprechende Anzahl seiner Vorkommen.
	 * 
	 * 
	 * Bsp: Für die Worte Hallo, Hello, Hey,
	 * 
	 * 
	 * Ebene 1:					[ H : 3 ]
	 * 								|
	 * 						 _______|___________
	 * 						/					\
	 * Ebene 2:        [ a : 1 ]			[ e : 2 ]
	 * 					/						|
	 * 				   /					____|______
	 * 				  /					   /		   \
	 * Ebene 3:	  [ l : 1 ]			 [ l : 1 ]		[ y : 1 ]
	 * 				  |					 /
	 * Ebene 4:   [ l : 1 ]			[ l : 1 ]
	 * 				  |					|
	 * Ebene 5:	  [ o : 1 ]			[ o : 1 ]
	 * 
	 * 
	 * @param words
	 * @return Map<String, Letter>
	 */
	public static Map<String, Letter> createLexicon(List<Word> words) {		//Erzeugt das Lexikon als Map<String,Letter>, wobei gilt:
		//String = Buchstaben
		//Letter = Häufigkeit des Buchstabens an der Position seines Vorkommens in Abhängigkeit von Vorgängerbuchstaben
		
		//Variables//
		Map<String,Letter> lexicon = new HashMap<String,Letter>();			//Erzeugt die Map
		List<String> wordToString; 											//Stringliste entspricht dem Wort aus Einzelbuchstaben
		
		
		//Methods//
//		for(int i=0; i < words.size();i++) { 								//Jedes Wort in der Liste words
		for(int i=47; i < 50;i++) {											//Debugg-Methode - nur zum debuggen geeignet!
			wordToString = cutWords(words.get(i));							//Soll erst mal in Buchstaben geschnitten werden
			int count = words.get(i).getCount();
			System.out.println("aktuelles Wort [" + i + "] : " + words.get(i).getWord() + " "
			+ cutWords(words.get(i)) + " Count: [" + words.get(i).getCount() + "]");	//Debuggen - Aktuelles Wort
			
			if (wordToString != null)	{									//Wenn cutWords() ein Ergebnis zurückgibt (was nur bei richtigen Buchstaben passiert)
				lexicon = iterateLexicon(lexicon, wordToString, count);		//Über das Lexicon soll dann iteriert werden, um herauszufinden, ob ein Wort vorhanden ist oder nicht
			}
		}
		return lexicon;														//Soll am Ende das Lexikon ausgeben. 
	}
	

	/**
	 * Die Methode cutWords() soll die eigegebenen Wörter in Buchstaben aufschneiden.
	 * Dabei soll auch überprüft werden, ob es sich dabei um echte Buchstaben
	 * oder um Sonderzeichen handelt. Die Sonderzeichen und Zahlen werden automatisch
	 * gefiltert.
	 * Die Ausgabe ist eine Liste aus reinen Buchstaben als List<String>.
	 * 
	 * @return characters
	 */
	public static List<String> cutWords(Word reference) {
		
		//Variables//
		List<String> characters = new ArrayList<String>();					//Erstellt eine Liste aus den Buchstaben eines jeweiligen Wortes
		String word = reference.getWord();									//Holt sich den entsprechenden String
		
		
		//Methods//
		for (int i = 0;i < word.length();i++) {								//Für jedes Element des Wortes (also jeden Buchstaben)
			
			char c = ((CharSequence) word.substring(i,i + 1)).charAt(0);	//Setze die Buchstaben in Charakter
//			System.out.println("Buchstabe " + c + " ist Letter? " + Character.isLetter(c));		//Kontrollmethode - soll aussagen, ob der Character wirklich ein Buchstabe ist oder nicht
			
			if(Character.isLetter(c)){										//Wenn der Character ein Buchstabe und kein Sonderzeichen ist
				characters.add(word.substring(i,i + 1));					//Soll er der Liste hinzugefügt werden
			} else if (i+1 >= word.length()){
				return null;
			}
		}
		return characters;													//Ausgegeben wird am Ende die Liste der Buchstaben eines Wortes
	}
	
	
	/**
	 * Die Methode iterateLexicon() iteriert über die Worte im Lexicon, indem es neue Worte überprüft
	 * ob sie im Lexicon vorhanden sind und, wenn sie noch nicht vorhanden sind, hinzufügt.
	 * Sollten sie bereits vorhanden sein, wird stattdessen der Count des jeweiligen Wortes erhöht.
	 * 
	 * @param wordToString, words, i
	 * @return Map<String, Letter>
	 */
	private static Map<String, Letter> iterateLexicon(Map<String, Letter> lexicon, List<String> newWord, int count) {
		
		//Variables//
		Letter tempLetter;												//templetter entspricht einem Zeiger, der durch die Buchstaben eines Wortes word iteriert
		String stringLetter = newWord.get(0);							//String - entspricht dem ersten Buchstaben eines Wortes
		
		
		//Methods//
		if(!lexicon.containsKey(stringLetter)) {						//Dann überprüfe, ob das Lexikon den ersten Letter beinhaltet
			lexicon.put(stringLetter,new Letter(stringLetter,count,1));	//und wenn nicht, dann füge den Letter dem Lexikon hinzu
			tempLetter = lexicon.get(stringLetter);						//und setze diesen Letter dann als templetter, von dem aus der nächste Buchstabe überprüft werden woll
			System.out.println("Erster Buchstabe: " + tempLetter.getWord() + " " + tempLetter.getCount());
		} else {														//Ansonsten
			tempLetter = lexicon.get(stringLetter);						//hole den Letter aus dem Lexikon und setze ihn als templetter
			tempLetter.increaseCount(count);							//und erhöhe den Count um den mitgeliefernten Count
			System.out.println("Erster Buchstabe: " + tempLetter.getWord() + " " + tempLetter.getCount());
		}
			
		for(int i2 = 1;i2 < newWord.size();i2++) {						//Gehe mit einer Schleife über die templetter
			sortIntoLexicon(tempLetter, new Letter(newWord.get(i2),count,i2));	//sortiere sie ins Lexikon über sortIntoLexicon() (?)
		}
	return lexicon;														//Gib das Wort zurück
	}
	
	
	/**
	 * Die Methode sortIntoLexicon() sortiert einen neuen Buchstaben ausgehend vom vorherigen Buchstaben
	 * in das Lexikon. Der Soruce-Letter entspricht dabei dem Letter, der den Ursprung eines Wort-
	 * stamms entspricht. Ausgehend von ihm soll überprüft werden, ob der nächste eingelesene Letter
	 * in dem Wortstamm an der nächsten Stelle vorhanden ist, oder nicht. Ist er vorhanden, so wird dessen Count erhöht.
	 * Ist er nicht vorhanden, wird er neu angelegt.
	 * Es entsteht eine Baumstruktur:
	 * 
	 * 
	 * @param sourceLetter, newLetter
	 * @return Letter
	 */
	public static Letter sortIntoLexicon(Letter sourceLetter, Letter newLetter) {
		
		//Variables//
		String singleLetter = newLetter.getWord();
		Map<String, Letter> treeLetter = sourceLetter.letterTree;
		
		
		//Methods//
		if(treeLetter.containsKey(singleLetter)){
			int count = treeLetter.get(singleLetter).getCount();
			treeLetter.get(singleLetter).increaseCount(count);
		} else {
			treeLetter.put(singleLetter, newLetter);
		}
		System.out.println("Folgebuchstabe: " + treeLetter.get(singleLetter).getWord() + " " + treeLetter.get(singleLetter).getCount());
		return treeLetter.get(newLetter.getWord());
	}
}