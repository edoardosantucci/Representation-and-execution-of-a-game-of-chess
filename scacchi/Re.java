package cleii.scacchi;

import java.util.ArrayList;

public class Re extends Pezzo{
	
	public Re(boolean colore,int pos){
		super(colore, pos);
	}
	
	boolean spostamentoPotenziale (Stato s, int target) {
		/*Scacchiera scacchiera = s.scacchiera;
		int posAttuale = this.pos;
		*/
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
	 		
	 		
	 		//Arrocco	
	 		   if(s.arrBianco) {
	 			  listSpostPot.add(scacchiera.matrice[7][6]);
	 			   listSpostPot.add(scacchiera.matrice[7][3]);
	 		}
	 		   if(s.arrNero) {
	 			  listSpostPot.add(scacchiera.matrice[0][1]);
	 			 listSpostPot.add(scacchiera.matrice[0][6]);
	 		   }
	 		   //Spostamenti RE BIANCO
             
            	 //laterale dx sx
    	 		   int posRB = scacchiera.getPos(scacchiera.r1);
    	 		   if(scacchiera.get(posRB + 10) == null) {
    	 			   int mossa = posRB + 10;
    	 			  listSpostPot.add(mossa);
    	 		   }
    	 		   if(scacchiera.get(posRB -10) == null) {
    	 			  int mossa = posRB - 10;
    	 			  listSpostPot.add(mossa);
    	 		   }
    	 		  //avanti indietro
    	 		  if(scacchiera.get(posRB +1) == null) {
    	 			  int mossa = posRB + 1;
    	 			  listSpostPot.add(mossa);
    	 		   }
    	 		 if(scacchiera.get(posRB -1) == null) {
   	 			  int mossa = posRB - 1;
   	 			  listSpostPot.add(mossa);
   	 		   }
    	 		// obliqui
    	 		 if(scacchiera.get(posRB + 11) == null) {
    	 			  int mossa = posRB + 11;
    	 			  listSpostPot.add(mossa);
    	 		   }
    	 		if(scacchiera.get(posRB -11) == null) {
    	 			  int mossa = posRB - 11;
    	 			  listSpostPot.add(mossa);
    	 		   }
    	 		if(scacchiera.get(posRB + 9) == null) {
    	 			  int mossa = posRB + 9;
    	 			  listSpostPot.add(mossa);
    	 		   }
    	 		if(scacchiera.get(posRB -9) == null) {
    	 			  int mossa = posRB - 9;
    	 			  listSpostPot.add(mossa);
    	 		}
	 			  
	 		//Spostamenti RE NERO
            	//laterale dx sx
  	 		   int posRN = scacchiera.getPos(scacchiera.r2);
  	 		   if(scacchiera.get(posRN + 10) == null) {
  	 			   int mossa = posRN + 10;
  	 			  listSpostPot.add(mossa);
  	 		   }
  	 		   if(scacchiera.get(posRN -10) == null) {
  	 			  int mossa = posRN - 10;
  	 			  listSpostPot.add(mossa);
  	 		   }
  	 		   //avanti indietro
  	 		  if(scacchiera.get(posRN - 1) == null) {
  	 			  int mossa = posRN - 1;
  	 			  listSpostPot.add(mossa);
  	 		   }
  	 		  if(scacchiera.get(posRN + 1) == null) {
  	 			  int mossa = posRN + 1;
  	 			  listSpostPot.add(mossa);
  	 		   }
  	 		  // obliqui
  	 		 if(scacchiera.get(posRN + 11) == null) {
  	 			  int mossa = posRN + 11;
  	 			  listSpostPot.add(mossa);
  	 		   }
  	 		if(scacchiera.get(posRN -11) == null) {
  	 			  int mossa = posRN - 11;
  	 			  listSpostPot.add(mossa);
  	 		   }
  	 		if(scacchiera.get(posRN + 9) == null) {
  	 			  int mossa = posRN + 9;
  	 			  listSpostPot.add(mossa);
  	 		   }
  	 		if(scacchiera.get(posRN -9) == null) {
  	 			  int mossa = posRN - 9;
  	 			  listSpostPot.add(mossa);
  	 		   }
             
             return listSpostPot;
	}
	 		   
	/*
	 * che restituisce true se e solo se nello stato s il pezzo pone sotto attacco
	 * la casa target . Si noti che target deve essere libero oppure occupato da un
	 * pezzo avversario. A proposito di questo ultimo caso, si tenga presente che
	 * tale metodo non tiene conto di eventuali situazioni di scacco (ai danni del
	 * proprio re causato dalla cattura in questione), che rendono la cattura non
	 * realizzabile in una mossa valida
	 */
	 		
	
	
	boolean attacco (Stato s, int target) {
		 ArrayList<Integer> attacco = new ArrayList<>();
		 attacco = listaAttacco(s);
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
	 * ArrayList<Integer> listaAttacco (Stato s) che restituisce un arraylist
	 * contenente tutte e sole le posizioni della scacchiera che sono sotto attacco
	 * da parte del pezzo nello stato s . Si noti che le posizioni restituite devono
	 * corrispondere a una casa libera oppure occupata da un pezzo avversario. Si
	 * noti infine che, sebbene tale metodo possa essere implementato direttamente
	 * nella classe Pezzo, può avere senso sovrascriverlo nelle sottoclassi in modo
	 * da renderne più efficiente l’implementazione
	 */
	
	ArrayList<Integer> listaAttacco (Stato s){
		ArrayList<Integer> listaAttacco = new ArrayList<>();
		 boolean colore = s.giocatore;
			for (int i = 0; i < s.scacchiera.riga; i++) {
				for (int k = 0; k < s.scacchiera.colonna; k++) {
					Pezzo attaccante = s.scacchiera.get(s.scacchiera.matrice[i][k]);
					if(attaccante instanceof Re && attaccante.colore == colore ) {
						if(colore == true) {
							Pezzo avanti_dx_attaccante = s.scacchiera.get(attaccante.pos + 1 + 10);
							Pezzo avanti_sx_attaccante = s.scacchiera.get(attaccante.pos + 1 - 10);
							Pezzo sotto_dx_attaccante = s.scacchiera.get(attaccante.pos - 1 + 10);
							Pezzo sotto_sx_attaccante = s.scacchiera.get(attaccante.pos - 1 - 10);
							Pezzo avanti_attaccante = s.scacchiera.get(attaccante.pos +1);
							Pezzo sotto_attaccante = s.scacchiera.get(attaccante.pos -1);
							Pezzo dx_attaccante = s.scacchiera.get(attaccante.pos +10);
							Pezzo sx_attaccante = s.scacchiera.get(attaccante.pos -10);
							
							if(s.sottoAttacco(avanti_dx_attaccante.pos, colore)) {
								listaAttacco.add(avanti_dx_attaccante.pos);
							} 
							if(s.sottoAttacco(avanti_sx_attaccante.pos, colore)){
								listaAttacco.add(avanti_sx_attaccante.pos);
							}
							if(s.sottoAttacco(sotto_dx_attaccante.pos, colore)){
								listaAttacco.add(sotto_dx_attaccante.pos);
							}
							if(s.sottoAttacco(sotto_sx_attaccante.pos, colore)){
								listaAttacco.add(sotto_sx_attaccante.pos);
							}
							if(s.sottoAttacco(avanti_attaccante.pos, colore)){
								listaAttacco.add(avanti_attaccante.pos);
							}
							if(s.sottoAttacco(sotto_attaccante.pos, colore)){
								listaAttacco.add(sotto_attaccante.pos);
							}
							if(s.sottoAttacco(dx_attaccante.pos, colore)){
								listaAttacco.add(dx_attaccante.pos);
							}
							if(s.sottoAttacco(sx_attaccante.pos, colore)){
								listaAttacco.add(sx_attaccante.pos);
							}
							
							
						} else {
							Pezzo avanti_dx_attaccante = s.scacchiera.get(attaccante.pos - 1 + 10);
							Pezzo avanti_sx_attaccante = s.scacchiera.get(attaccante.pos - 1 - 10);
							Pezzo sotto_dx_attaccante = s.scacchiera.get(attaccante.pos + 1 + 10);
							Pezzo sotto_sx_attaccante = s.scacchiera.get(attaccante.pos + 1 - 10);
							Pezzo avanti_attaccante = s.scacchiera.get(attaccante.pos - 1);
							Pezzo sotto_attaccante = s.scacchiera.get(attaccante.pos + 1);
							Pezzo dx_attaccante = s.scacchiera.get(attaccante.pos +10);
							Pezzo sx_attaccante = s.scacchiera.get(attaccante.pos -10);
							
							if(s.sottoAttacco(avanti_dx_attaccante.pos, colore)) {
								listaAttacco.add(avanti_dx_attaccante.pos);
							} 
							if(s.sottoAttacco(avanti_sx_attaccante.pos, colore)){
								listaAttacco.add(avanti_sx_attaccante.pos);
							}
							if(s.sottoAttacco(sotto_dx_attaccante.pos, colore)){
								listaAttacco.add(sotto_dx_attaccante.pos);
							}
							if(s.sottoAttacco(sotto_sx_attaccante.pos, colore)){
								listaAttacco.add(sotto_sx_attaccante.pos);
							}
							if(s.sottoAttacco(avanti_attaccante.pos, colore)){
								listaAttacco.add(avanti_attaccante.pos);
							}
							if(s.sottoAttacco(sotto_attaccante.pos, colore)){
								listaAttacco.add(sotto_attaccante.pos);
							}
							if(s.sottoAttacco(dx_attaccante.pos, colore)){
								listaAttacco.add(dx_attaccante.pos);
							}
							if(s.sottoAttacco(sx_attaccante.pos, colore)){
								listaAttacco.add(sx_attaccante.pos);
							}
						}
					}
				}
			}
		
		return listaAttacco;
	}
	
	
	public String toString() {
		if (colore == true) {

			return "K";
		} else {
			return "k";
		}
	}
}
