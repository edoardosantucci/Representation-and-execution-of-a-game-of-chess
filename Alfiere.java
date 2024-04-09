package cleii.scacchi;

import java.util.ArrayList;

public class Alfiere extends Pezzo{
	
	public Alfiere(boolean colore,int pos){
		super(colore, pos);
	}
	
	
	/*
	 * abstract boolean spostamentoPotenziale (Stato s, int target);
		che restituisce true se e solo se il pezzo può muovere nello stato s dalla propria casa
		alla casa target (che deve essere libera in s ). Si tenga presente che negli spostamenti
		del re bisogna considerare anche l’eventuale arrocco (che invece non va considerato tra
		gli spostamenti della torre). Tale metodo non tiene conto di eventuali situazioni di
		scacco (ai danni del proprio re causato dal movimento in questione), che rendono lo
		spostamento non realizzabile in una mossa valida.
	 */
	
	 boolean spostamentoPotenziale (Stato s, int target) {
		 ArrayList<Integer> spostamenti = listaSpostamentoPotenziale(s);
			return spostamenti.contains(target);
	 }
	 
	 /*
	   * ArrayList<Integer> listaSpostamentoPotenziale (Stato s)
			che restituisce un arraylist contenente tutte e sole le posizioni della scacchiera verso le
			quali il pezzo può muovere a partire dallo stato s . Si noti che le posizioni restituite
			devono corrispondere a una casa libera in s e che negli spostamenti del re bisogna
			considerare anche l’eventuale arrocco (che invece non va considerato tra gli
			spostamenti della torre). Tale metodo non tiene conto di eventuali situazioni di scacco
			(ai danni del proprio re causato dal movimento in questione), che rendono lo
			spostamento non realizzabile in una mossa valida.
			Si noti infine che, sebbene tale metodo possa essere implementato direttamente nellaclasse 
			Pezzo, può avere senso sovrascriverlo nelle sottoclassi in modo da renderne più
			efficiente l’implementazione
			*/
	 
	 ArrayList<Integer> listaSpostamentoPotenziale (Stato s){
		 Scacchiera scacchiera = s.scacchiera;
		 ArrayList<Integer> listSpostPot = new ArrayList<>();
		 //Alfieri bianchi
		 int alfiereB1 = scacchiera.getPos(scacchiera.a1);
		 //diagonale 11
		 for(int i = alfiereB1; i <= 88; i = i + 11) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 for(int i = alfiereB1; i >= 11; i = i - 11) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 //diagonale 9
		 for(int i = alfiereB1; i <= 81; i = i + 9) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 for(int i = alfiereB1; i >= 18; i = i - 9) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		  
		 int alfiereB2 = scacchiera.getPos(scacchiera.a2);
		 //diagonale 11
		 for(int i = alfiereB2; i <= 88; i = i + 11) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 for(int i = alfiereB2; i >= 11; i = i - 11) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 //diagonale 9
		 for(int i = alfiereB2; i <= 81; i = i + 9) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 for(int i = alfiereB2; i >= 18; i = i - 9) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 //Alfieri neri
		 int alfiereN1 = scacchiera.getPos(scacchiera.a3);
		 //diagonale 11
		 for(int i = alfiereN1; i <= 88; i = i + 11) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 for(int i = alfiereN1; i >= 11; i = i - 11) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 //diagonale 9
		 for(int i = alfiereN1; i <= 81; i = i + 9) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 for(int i = alfiereN1; i >= 18; i = i - 9) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 
		 int alfiereN2 = scacchiera.getPos(scacchiera.a4);
		 //diagonale 11
		 for(int i = alfiereN2; i <= 88; i = i + 11) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 for(int i = alfiereN2; i >= 11; i = i - 11) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 //diagonale 9
		 for(int i = alfiereN2; i <= 81; i = i + 9) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 for(int i = alfiereN2; i >= 18; i = i - 9) {
			 if(scacchiera.get(i) == null) {
				 listSpostPot.add(i);
			 }
		 }
		 
		 return listSpostPot;
	 }
	 
	 /*
		abstract boolean attacco (Stato s, int target);
		che restituisce true se e solo se nello stato s il pezzo pone sotto attacco la casa
		target . Si noti che target deve essere libero oppure occupato da un pezzo
		avversario. A proposito di questo ultimo caso, si tenga presente che tale metodo non
		tiene conto di eventuali situazioni di scacco (ai danni del proprio re causato dalla
		cattura in questione), che rendono la cattura non realizzabile in una mossa valida.
		*/
	 
	 boolean attacco (Stato s, int target) {
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
	
	    /*
		ArrayList<Integer> listaAttacco (Stato s)
		che restituisce un arraylist contenente tutte e sole le posizioni della scacchiera che
		sono sotto attacco da parte del pezzo nello stato s . Si noti che le posizioni restituite
		devono corrispondere a una casa libera oppure occupata da un pezzo avversario. Si noti
		infine che, sebbene tale metodo possa essere implementato direttamente nella classe
		Pezzo, può avere senso sovrascriverlo nelle sottoclassi in modo da renderne più
		efficiente l’implementazione
	*/
	 
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

				return "B";
			} else {
				return "b";
			}
			
		}
	

	
}
