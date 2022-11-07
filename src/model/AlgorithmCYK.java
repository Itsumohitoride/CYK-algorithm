package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class AlgorithmCYK {
	
	Hashtable<String, ArrayList<String>> states;
	int numberStates;
	String string;
	int sizeString;
	
	public AlgorithmCYK() {
		states = new Hashtable<>();
		numberStates = 0;
		sizeString = 0;
	}
	
	public ArrayList<String> splitProductions(String text){
		String [] splitProductions = text.split("/");
		
		ArrayList<String> productionsOfState = new ArrayList<>();
		
		for (int i = 0; i < splitProductions.length; i++) {
			productionsOfState.add(splitProductions[i]);
		}
		
		return productionsOfState;
	}
	
	public void addStates(String state, ArrayList<String> productions) {
		states.put(state, productions);
	}
	
	public void addString(String string) {
		this.string = string;
		sizeString = this.string.length();
	}
	
	public boolean calculateCYK() {
		
		return false;
	}
	
	
}
