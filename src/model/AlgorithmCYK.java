package model;

import java.util.Enumeration;
import java.util.Hashtable;

public class AlgorithmCYK {

	Hashtable<String, Hashtable<String, String>> states;
	int numberStates;
	String string;
	int sizeString;
	String[][] cykMatrix;

	public AlgorithmCYK() {
		states = new Hashtable<>();
		numberStates = 0;
		sizeString = 0;
	}

	public Hashtable<String, String> splitProductions(String text){
		String [] splitProductions = text.split("/");

		Hashtable<String, String> productionsOfState = new Hashtable<>();

		for (int i = 0; i < splitProductions.length; i++) {
			productionsOfState.put(splitProductions[i],splitProductions[i]);
		}

		return productionsOfState;
	}

	public void addStates(String state, Hashtable<String, String> productions) {
		states.put(state, productions);
	}

	public void addString(String string) {
		this.string = string;
		sizeString = this.string.length();
		cykMatrix = new String[sizeString][sizeString];
	}

	public boolean calculateCYK() {
		stepOne();
		stepTwo();
		return false;
	}
	
	public String searchSymbol(String symbol) {
		
		Enumeration<Hashtable<String, String>> productions = states.elements();
		Enumeration<String> keys = states.keys();
		String state = "{}"; 
		String verify;
		while (productions.hasMoreElements()) {
			
			verify = productions.nextElement().get(symbol);
			
			if(verify != null && verify.equals(symbol)) {
				state += (String) keys.nextElement();
			}
		}
		
		return state;
	}

	public void stepOne() {
		
		String[] symbol = string.split("");
		
		for (int i = 0; i < cykMatrix.length; i++) {
			cykMatrix[i][0] = searchSymbol(symbol[i]);
			System.out.println("Matriz posición: "+i+" guardó: "+symbol[i]);
		}
	}

	public void stepTwo() {

	}


}
