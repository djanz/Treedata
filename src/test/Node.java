package test;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Die Node ist die Untereinheit des Trees, der die Datenstruktur des T9-Systems darstellt.
 * Der Datentyp der Node entspricht einer HashMap, weil der Stelle des entsprechenden Buchstabens eine Häufigkeit zugeteilt wird.
 * 
 * 
 * @author Daniel Janz
 *
 * @param <HashMap>
 */

public class Node<HashMap> implements Serializable {

	private static final long serialVersionUID = 5625136738124507560L;
	
	long prob;								//Legt die Häufigkeit fest
	char c;									//entspricht dem Buchstaben, der gesucht wird
	Node<HashMap> parent = null;			//vorheriger Knoten
	Node<HashMap> child = null;				//nachfolgender Knoten
	
	
	
	/**
	 * Diese Methode soll bei der geladenen Node den Zähler für die Wahrscheinlichkeit eines Buchstabends hochsetzen.
	 * Der Wert für prob wird dann erhöht, wenn der entsprechende Buchstabe gewählt wird.
	 * Rückgabewert ist:
	 * Node<HashMap>
	 * 
	 * @return
	 */
	public Node<HashMap> probabilityAdd(char c) {
		Node<HashMap> node = new Node<HashMap>();
		if (c == this.c)
			prob++;
		return node;
	}
	
	
	public char getC() {					//gibt den Buchstaben aus
		return c;
	}

	public void setC(char c) {				//setzt den entsprechenden Buchstaben fest (notwendig?)
		this.c = c;
	}

	public Node<HashMap> getParent() {		//Holt den vorherigen Knoten zurück
		return parent;
	}

	public void setParent(Node<HashMap> parent) {		//Setzt den vorherigen Knoten als parten-Knoten
		this.parent = parent;
	}

	public long getProb() {					//gibt die Häufigkeit des gedrückten Buttons an entsprechender Stelle des Wortes aus
		return prob;
	}
	
	public void setProb(long prob) {		//setzt die Häufigkeit
		this.prob = prob;
	}
	
}
