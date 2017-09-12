package test;

import java.util.HashMap;

/**
 * Helferklasse zum Testen der Nodes
 * 
 * @author Daniel Janz
 */

public class NodeApplication extends Node<HashMap> {
	
	static char c = 0;
	static int place;
	
	
	/**
	 * Diese Methode verändert die Variable Place. Durch Zugriffe von außen wird
	 * mithilfe dieser Methode also die Anzahl des zugeordneten Buchstabens verändert.
	 * 
	 * @param place
	 * @return
	 */
	
	public static int changePlace(int place) {
		while (place <10) {
			place++;
		}
		return place;
	}

	
	
	/**
	 * Ausgabe für die entsprechenden Nodes - nützlich zum Debuggen.
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		NodeSegment x = new NodeSegment(place, c);
		int place = x.getPlace();
		x.setPlace(changePlace(place));
		
		System.out.println("Variable = " + x.place);

	}

}
