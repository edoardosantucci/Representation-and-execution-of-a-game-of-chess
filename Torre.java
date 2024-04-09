package cleii.scacchi;

import java.util.ArrayList;

public class Torre extends Pezzo{
	
	public Torre(boolean colore,int pos){
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
	 public boolean spostamentoPotenziale (Stato s, int target) {
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
		//int decina = (torreB1 - unita) /10;
		// int unita = torreB1 -((torreB1/10)*10);
		 int torreB1 = scacchiera.getPos(scacchiera.t1);
		 int torreB2 = scacchiera.getPos(scacchiera.t2);
		 int torreN1 = scacchiera.getPos(scacchiera.t3);
		 int torreN2 = scacchiera.getPos(scacchiera.t4);
		 int colonnaB1 = -1;
		 int colonnaB2 = -1;
		 int colonnaN1 = -1;
		 int colonnaN2 = -1;
		 int rigaB1 = -1;
		 int rigaB2 = -1;
		 int rigaN1 = -1;
		 int rigaN2 = -1;
		 
		 
		 for (int i = 0; i < scacchiera.riga; i++) {
				for (int k = 0; k < scacchiera.colonna; k++) {
				if(scacchiera.matrice[i][k] == torreB1) {
					 colonnaB1 = k;
					 rigaB1 = i;
				}
				if(scacchiera.matrice[i][k] == torreB2) {
					 colonnaB2 = k;
					 rigaB2 = i;
				}
				if(scacchiera.matrice[i][k] == torreN1) {
					 colonnaN1 = k;
					 rigaN1 = i;
				}
				if(scacchiera.matrice[i][k] == torreN2) {
					 colonnaN2 = k;
					 rigaN2 = i;
				}
			}
		 }
		 //avanti ed indietro
		 if(colonnaB1 != -1 || colonnaB2 != -1 || colonnaN1 != -1 || colonnaN2 != -1) {
		 for (int i = 0; i < scacchiera.riga; i++) {
			 if(scacchiera.get(scacchiera.matrice[i][colonnaB1]) == null){
				 listSpostPot.add(scacchiera.matrice[i][colonnaB1]);
			 }
			 if(scacchiera.get(scacchiera.matrice[i][colonnaB2]) == null){
				 listSpostPot.add(scacchiera.matrice[i][colonnaB2]);
			 }
			 if(scacchiera.get(scacchiera.matrice[i][colonnaN1]) == null){
				 listSpostPot.add(scacchiera.matrice[i][colonnaN1]);
			 }
			 if(scacchiera.get(scacchiera.matrice[i][colonnaN2]) == null){
				 listSpostPot.add(scacchiera.matrice[i][colonnaN2]);
			 }
		 }
	 }
		 //destra e sinistra
		if(rigaB1 != -1 || rigaB2 != -1 || rigaN1 != -1 || rigaN2 != -1) {
			 for (int k = 0; k < scacchiera.colonna; k++) {
				 if(scacchiera.get(scacchiera.matrice[rigaB1][k]) == null){
					 listSpostPot.add(scacchiera.matrice[rigaB1][k]);
				 } 
				 if(scacchiera.get(scacchiera.matrice[rigaB2][k]) == null){
					 listSpostPot.add(scacchiera.matrice[rigaB2][k]);
				 }
				 if(scacchiera.get(scacchiera.matrice[rigaN1][k]) == null){
					 listSpostPot.add(scacchiera.matrice[rigaN1][k]);
				 }
				 if(scacchiera.get(scacchiera.matrice[rigaN2][k]) == null){
					 listSpostPot.add(scacchiera.matrice[rigaN2][k]);
				 }
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

			return "R";
		} else {
			return "r";
		}
	}
}
