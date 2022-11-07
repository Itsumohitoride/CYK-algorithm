package model;

import java.util.ArrayList;
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
		String state = ""; 
		String verify;
		while (productions.hasMoreElements()) {
			
			verify = productions.nextElement().get(symbol);
			String current = (String) keys.nextElement();
			
			if(verify != null && verify.equals(symbol)) {
				state += current;
			}
		}
		
		return state;
	}

	public void stepOne() {
		
		String[] symbol = string.split("");
		
		for (int i = 0; i < sizeString; i++) {
			cykMatrix[i][0] = searchSymbol(symbol[i]);
		}
	}

	public void stepTwo() {
		
		String states1 = "";
		String states2 = "";

		for (int j = 1; j < sizeString; j++) {
			
			for (int i = 0; i <= sizeString - (j+2) + 1; i++) {
				
				String combination = "";
				
				boolean verify = true;
				
				for (int k = 1; k < (j+2) - 1; k++) {
					verify = false;
					states1 = cykMatrix[i][k-1];
					states2 = cykMatrix[i+k][j-k];
					combination += combinations(states1, states2);
				}
				
				if(!verify) {
					cykMatrix[i][j] = combination;
				}
			}
		}
		
		for (int i = 0; i < cykMatrix.length; i++) {
			for (int j = 0; j < cykMatrix.length; j++) {
				System.out.println("{"+cykMatrix[j][i]+"}");
			}
		}
	}
	
	public String combinations(String states1, String states2) {
		String[] first = states1.split("");
		String[] second = states2.split("");
		
		ArrayList<String> combinations = new ArrayList<>();
		
		for (int i = 0; i < first.length; i++) {
			
			for (int j = 0; j < second.length; j++) {
				combinations.add(first[i]+second[j]);
			}
		}
		String symbol = "";
		
		for (int i = 0; i < combinations.size(); i++) {
			symbol += searchSymbol(combinations.get(i));
		}
		
		return symbol;
	}
	//Verificación de que si la S está conetenida en el último lugar, lo genera ;)
}
