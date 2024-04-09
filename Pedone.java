package cleii.scacchi;

import java.util.ArrayList;

public class Pedone extends Pezzo {

	public Pedone(boolean colore, int pos) {
		super(colore, pos);
	}

	/*
	 * abstract boolean spostamentoPotenziale (Stato s, int target); che restituisce
	 * true se e solo se il pezzo può muovere nello stato s dalla propria casa alla
	 * casa target (che deve essere libera in s ). Si tenga presente che negli
	 * spostamenti del re bisogna considerare anche l’eventuale arrocco (che invece
	 * non va considerato tra gli spostamenti della torre). Tale metodo non tiene
	 * conto di eventuali situazioni di scacco (ai danni del proprio re causato dal
	 * movimento in questione), che rendono lo spostamento non realizzabile in una
	 * mossa valida.
	 */
	public boolean spostamentoPotenziale(Stato s, int target) {
		/*Scacchiera scacchiera = s.scacchiera;
		int posAttuale = this.pos;
		*/
		ArrayList<Integer> spostamenti = listaSpostamentoPotenziale(s);

		return spostamenti.contains(target);
	}

	/*
	 * ArrayList<Integer> listaSpostamentoPotenziale (Stato s) che restituisce un
	 * arraylist contenente tutte e sole le posizioni della scacchiera verso le
	 * quali il pezzo può muovere a partire dallo stato s . Si noti che le posizioni
	 * restituite devono corrispondere a una casa libera in s e che negli
	 * spostamenti del re bisogna considerare anche l’eventuale arrocco (che invece
	 * non va considerato tra gli spostamenti della torre). Tale metodo non tiene
	 * conto di eventuali situazioni di scacco (ai danni del proprio re causato dal
	 * movimento in questione), che rendono lo spostamento non realizzabile in una
	 * mossa valida. Si noti infine che, sebbene tale metodo possa essere
	 * implementato direttamente nellaclasse Pezzo, può avere senso sovrascriverlo
	 * nelle sottoclassi in modo da renderne più efficiente l’implementazione
	 */
	ArrayList<Integer> listaSpostamentoPotenziale(Stato s) {
		Scacchiera scacchiera = s.scacchiera;
		ArrayList<Integer> listSpostPot = new ArrayList<>();
		boolean colore = s.giocatore;

		// Se il pedone è in posizione inizale
		if ((colore == true && scacchiera.ped_bianco_pos_iniziale.contains(this.pos))
				|| (colore == false && scacchiera.ped_nero_pos_iniziale.contains(this.pos))) {
			if (colore == true) {
				if (scacchiera.get(this.pos + 2) == null)
					listSpostPot.add(this.pos + 2);
			} else {
				if (scacchiera.get(this.pos - 2) == null)
					listSpostPot.add(this.pos - 2);
			}
		}

		// Sposto avanti di uno
		if (colore == true) {
			if (scacchiera.get(this.pos + 1) == null)
				listSpostPot.add(this.pos + 1);
		} else {
			if (scacchiera.get(this.pos - 1) == null)
				listSpostPot.add(this.pos - 1);
		}

		// Cattura
		if (colore == true) {
			Pezzo avanti_destra = scacchiera.get(this.pos + 1 + 10);
			Pezzo avanti_sinistra = scacchiera.get(this.pos + 1 - 10);
			if (avanti_destra != null && avanti_destra.colore == false)
				listSpostPot.add(this.pos + 1 + 10);
			else if (avanti_sinistra != null && avanti_sinistra.colore == false)
				listSpostPot.add(this.pos + 1 - 10);
		} else {
			Pezzo sotto_destra = scacchiera.get(this.pos - 1 + 10);
			Pezzo sotto_sinistra = scacchiera.get(this.pos - 1 - 10);
			if (sotto_destra != null && sotto_destra.colore == true)
				listSpostPot.add(this.pos - 1 + 10);
			else if (sotto_sinistra != null && sotto_sinistra.colore == true)
				listSpostPot.add(this.pos - 1 - 10);
		}

		// En passant
		Pezzo destra = scacchiera.get(this.pos + 10);
		Pezzo sinistra = scacchiera.get(this.pos - 10);
		if (destra != null && destra.colore != this.colore && destra instanceof Pedone) {
			if ((this.colore == true && s.enPassBianco) || this.colore == false && s.enPassNero)
				listSpostPot.add(s.enPassant);
		} else if (sinistra != null && sinistra.colore != this.colore && sinistra instanceof Pedone) {
			if ((this.colore == true && s.enPassBianco) || this.colore == false && s.enPassNero)
				listSpostPot.add(s.enPassant);
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
	 * che restituisce un arraylist contenente tutte e sole le posizioni della scacchiera che
	 *	sono sotto attacco da parte del pezzo nello stato s. Si noti che le posizioni restituite
	 *	devono corrispondere a una casa libera oppure occupata da un pezzo avversario. 
	 *
	 */
	 
	 ArrayList<Integer> listaAttacco (Stato s){
		 ArrayList<Integer> listaAttacco = new ArrayList<>();
		 boolean colore = s.giocatore;
			for (int i = 0; i < s.scacchiera.riga; i++) {
				for (int k = 0; k < s.scacchiera.colonna; k++) {
					Pezzo attaccante = s.scacchiera.get(s.scacchiera.matrice[i][k]);
					if(attaccante instanceof Pedone && attaccante.colore == colore ) {
						if(colore == true) {
							Pezzo avanti_dx_attaccante = s.scacchiera.get(attaccante.pos + 1 + 10);
							Pezzo avanti_sx_attaccante = s.scacchiera.get(attaccante.pos + 1 - 10);
							if(s.sottoAttacco(avanti_dx_attaccante.pos, colore)) {
								listaAttacco.add(avanti_dx_attaccante.pos);
							} 
							if(s.sottoAttacco(avanti_sx_attaccante.pos, colore)){
								listaAttacco.add(avanti_sx_attaccante.pos);
							}
						} else {
							Pezzo sotto_dx_attaccante = s.scacchiera.get(attaccante.pos - 1 + 10);
							Pezzo sotto_sx_attaccante = s.scacchiera.get(attaccante.pos - 1 - 10);
							if(s.sottoAttacco(sotto_dx_attaccante.pos, colore)) {
								listaAttacco.add(sotto_dx_attaccante.pos);
							} 
							if(s.sottoAttacco(sotto_sx_attaccante.pos, colore)){
								listaAttacco.add(sotto_sx_attaccante.pos);
							}
						}
					}
				}
			}
		  return listaAttacco;
		 }
	 
	 
	 
	public String toString() {
		if (colore == true) {
			return "P";
		} else {
			return "p";
		}
	}

}