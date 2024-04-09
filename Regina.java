package cleii.scacchi;

import java.util.ArrayList;

public class Regina extends Pezzo{
	
	public Regina(boolean colore,int pos){
		super(colore, pos);
	}
	
	
	boolean spostamentoPotenziale(Stato s, int target) {
		ArrayList<Integer> spostamenti = listaSpostamentoPotenziale(s);
		return spostamenti.contains(target);
	}

	
	ArrayList<Integer> listaSpostamentoPotenziale (Stato s){
		 Scacchiera scacchiera = s.scacchiera;
		 ArrayList<Integer> listSpostPot = new ArrayList<>();
		 int reginaB = scacchiera.getPos(scacchiera.rg1);
		 int reginaN = scacchiera.getPos(scacchiera.rg2);
		 int colonnaRGB = -1;
		 int rigaRGB = -1;
		 int colonnaRGN = -1;
		 int rigaRGN = -1;
		 
		 for (int i = 0; i < scacchiera.riga; i++) {
				for (int k = 0; k < scacchiera.colonna; k++) {
				if(scacchiera.matrice[i][k] == reginaB) {
					 colonnaRGB = k;
					 rigaRGB = i;
				}
				if(scacchiera.matrice[i][k] == reginaN) {
					 colonnaRGN = k;
					 rigaRGN = i;
				}
			}
		 }
		 //avanti ed indietro
		 if(colonnaRGB != -1 || colonnaRGN != -1) {
			 for (int i = 0; i < scacchiera.riga; i++) {
				 if(scacchiera.get(scacchiera.matrice[i][colonnaRGB]) == null){
					 listSpostPot.add(scacchiera.matrice[i][colonnaRGB]);
				 }
				 if(scacchiera.get(scacchiera.matrice[i][colonnaRGN]) == null){
					 listSpostPot.add(scacchiera.matrice[i][colonnaRGN]);
				 }
			 }
		 }
		 //destra e sinistra
			if(rigaRGB != -1 || rigaRGN != -1) {
				 for (int k = 0; k < scacchiera.colonna; k++) {
					 if(scacchiera.get(scacchiera.matrice[rigaRGB][k]) == null){
						 listSpostPot.add(scacchiera.matrice[rigaRGB][k]);
					 } 
					 if(scacchiera.get(scacchiera.matrice[rigaRGN][k]) == null){
						 listSpostPot.add(scacchiera.matrice[rigaRGN][k]);
					 }
				 }
			}
			//diagonale 11 regina bianca
			 for(int i = reginaB; i <= 88; i = i + 11) {
				 if(scacchiera.get(i) == null) {
					 listSpostPot.add(i);
				 }
			 }
			 for(int i = reginaB; i >= 11; i = i - 11) {
				 if(scacchiera.get(i) == null) {
					 listSpostPot.add(i);
				 }
			 }
			 //diagonale 9 regina bianca
			 for(int i = reginaB; i <= 81; i = i + 9) {
				 if(scacchiera.get(i) == null) {
					 listSpostPot.add(i);
				 }
			 }
			 for(int i = reginaB; i >= 18; i = i - 9) {
				 if(scacchiera.get(i) == null) {
					 listSpostPot.add(i);
				 }
			 }
			//diagonale 11 regina nera
			 for(int i = reginaN; i <= 88; i = i + 11) {
				 if(scacchiera.get(i) == null) {
					 listSpostPot.add(i);
				 }
			 }
			 for(int i = reginaN; i >= 11; i = i - 11) {
				 if(scacchiera.get(i) == null) {
					 listSpostPot.add(i);
				 }
			 }
			 //diagonale 9 regina nera
			 for(int i = reginaN; i <= 81; i = i + 9) {
				 if(scacchiera.get(i) == null) {
					 listSpostPot.add(i);
				 }
			 }
			 for(int i = reginaN; i >= 18; i = i - 9) {
				 if(scacchiera.get(i) == null) {
					 listSpostPot.add(i);
				 }
			 }
			  return listSpostPot; 
	}
	
	boolean attacco(Stato s, int target) {
		ArrayList<Integer> attacco = listaAttacco(s);
   	 boolean ris = false;
		 for(int i = 0; i < attacco.size(); i++) {         
			 int verifica = (int)attacco.get(i);
			 if(verifica == target) {
				 ris = true;
				 break;
			 }
		 }
		 return ris;
	}
	
	 ArrayList<Integer> listaAttacco (Stato s){
	    	ArrayList<Integer> listaAttacco = new ArrayList<>();
			 ArrayList<Integer> listSpostPot = listaSpostamentoPotenziale(s);
			 
			 for(int i = 0; i < listSpostPot.size(); i++) {
				 if(s.sottoAttacco(listSpostPot.get(i), true)) {
					 listaAttacco.add(listSpostPot.get(i));
				 }
				 if(s.sottoAttacco(listSpostPot.get(i), false)) {
					 listaAttacco.add(listSpostPot.get(i));
				 }
			 }
			 
			 return listaAttacco;
	    	
	    }
	
	public String toString() {
		if (colore == true) {

			return "Q";
		} else {
			return "q";
		}
	}





}
