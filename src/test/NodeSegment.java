package test;

import java.io.Serializable;

/**
 * NodeSegment
 * Bildet die Unterelemente der eigentlichen Datenstruktur.
 * Enthält die Buchstaben, sowie zusätzlich deren Platz
 * 
 * @author Daniel Janz
 */

public class NodeSegment implements Serializable {
	
	private static final long serialVersionUID = -1768566971130141188L;
	
	char c;
	int place;
//	Letter letter = new Letter(c, 0);
	
	public NodeSegment(int place, char c) {
		super();
		this.c = c;
		this.place = place;
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
		System.out.println("Platzhalter = " + place);
	}

}
