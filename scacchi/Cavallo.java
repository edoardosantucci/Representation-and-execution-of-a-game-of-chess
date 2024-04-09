package cleii.scacchi;

import java.util.ArrayList;

public class Cavallo extends Pezzo{
	
	public Cavallo(boolean colore,int pos){
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
	 boolean spostamentoPotenziale(Stato s, int target) {
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
		
		int posCB1 = scacchiera.getPos(scacchiera.c1);
		//Spostamenti avanti
		//Sx avanti lungo
		if(scacchiera.get(posCB1 - 8) == null) {
			int mossa = posCB1 - 8;
 			  listSpostPot.add(mossa);
		}
		//Dx avanti lungo
		if(scacchiera.get(posCB1 + 12) == null) {
			int mossa = posCB1 + 12;
 			  listSpostPot.add(mossa);
		}
		//Sx avanti corto
		if(scacchiera.get(posCB1 - 19) == null) {
			int mossa = posCB1 - 19;
 			  listSpostPot.add(mossa);
		}
		//Dx avanti corto
		if(scacchiera.get(posCB1 + 21) == null) {
			int mossa = posCB1 + 21;
 			  listSpostPot.add(mossa);
		}
		//Spostamenti indietro
		//Sx indietro lungo
		if(scacchiera.get(posCB1 - 12) == null) {
			int mossa = posCB1 - 12;
 			  listSpostPot.add(mossa);
		}
		//Dx indietro lungo
		if(scacchiera.get(posCB1 + 8) == null) {
			int mossa = posCB1 + 8;
 			  listSpostPot.add(mossa);
		}
		//Sx indietro corto
		if(scacchiera.get(posCB1 - 21) == null) {
			int mossa = posCB1 - 21;
 			  listSpostPot.add(mossa);
		}
		//Dx indietro corto
		if(scacchiera.get(posCB1 + 19) == null) {
			int mossa = posCB1 + 19;
 			  listSpostPot.add(mossa);
		}
		
		int posCB2 = scacchiera.getPos(scacchiera.c2);
		//Spostamenti avanti
				//Sx avanti lungo
				if(scacchiera.get(posCB2 - 8) == null) {
					int mossa = posCB2 - 8;
		 			  listSpostPot.add(mossa);
				}
				//Dx avanti lungo
				if(scacchiera.get(posCB2 + 12) == null) {
					int mossa = posCB2 + 12;
		 			  listSpostPot.add(mossa);
				}
				//Sx avanti corto
				if(scacchiera.get(posCB2 - 19) == null) {
					int mossa = posCB2 - 19;
		 			  listSpostPot.add(mossa);
				}
				//Dx avanti corto
				if(scacchiera.get(posCB2 + 21) == null) {
					int mossa = posCB2 + 21;
		 			  listSpostPot.add(mossa);
				}
				//Spostamenti indietro
				//Sx indietro lungo
				if(scacchiera.get(posCB2 - 12) == null) {
					int mossa = posCB2 - 12;
		 			  listSpostPot.add(mossa);
				}
				//Dx indietro lungo
				if(scacchiera.get(posCB2 + 8) == null) {
					int mossa = posCB2 + 8;
		 			  listSpostPot.add(mossa);
				}
				//Sx indietro corto
				if(scacchiera.get(posCB2 - 21) == null) {
					int mossa = posCB2 - 21;
		 			  listSpostPot.add(mossa);
				}
				//Dx indietro corto
				if(scacchiera.get(posCB2 + 19) == null) {
					int mossa = posCB2 + 19;
		 			  listSpostPot.add(mossa);
				}
		
		int posCN1 = scacchiera.getPos(scacchiera.c3);
		//Spostamenti indietro
		//Sx indiero lungo
		if(scacchiera.get(posCN1 - 8) == null) {
			int mossa = posCN1 - 8;
 			  listSpostPot.add(mossa);
		}
		//Dx indietro lungo
		if(scacchiera.get(posCN1 + 12) == null) {
			int mossa = posCN1 + 12;
 			  listSpostPot.add(mossa);
		}
		//Sx indietro corto
		if(scacchiera.get(posCN1 - 19) == null) {
			int mossa = posCN1 - 19;
 			  listSpostPot.add(mossa);
		}
		//Dx indietro corto
		if(scacchiera.get(posCN1 + 21) == null) {
			int mossa = posCN1 + 21;
 			  listSpostPot.add(mossa);
		}
		//Spostamenti avanti
		//Sx avanti lungo
		if(scacchiera.get(posCN1 - 12) == null) {
			int mossa = posCN1 - 12;
 			  listSpostPot.add(mossa);
		}
		//Dx avanti lungo
		if(scacchiera.get(posCN1 + 8) == null) {
			int mossa = posCN1 + 8;
 			  listSpostPot.add(mossa);
		}
		//Sx avanti corto
		if(scacchiera.get(posCN1 - 21) == null) {
			int mossa = posCN1 - 21;
 			  listSpostPot.add(mossa);
		}
		//Dx avanti corto
		if(scacchiera.get(posCN1 + 19) == null) {
			int mossa = posCN1 + 19;
 			  listSpostPot.add(mossa);
		}
		
		
		int posCN2 = scacchiera.getPos(scacchiera.c4);
		//Spostamenti indietro
				//Sx indiero lungo
				if(scacchiera.get(posCN2 - 8) == null) {
					int mossa = posCN2 - 8;
		 			  listSpostPot.add(mossa);
				}
				//Dx indietro lungo
				if(scacchiera.get(posCN2 + 12) == null) {
					int mossa = posCN2 + 12;
		 			  listSpostPot.add(mossa);
				}
				//Sx indietro corto
				if(scacchiera.get(posCN2 - 19) == null) {
					int mossa = posCN2 - 19;
		 			  listSpostPot.add(mossa);
				}
				//Dx indietro corto
				if(scacchiera.get(posCN2 + 21) == null) {
					int mossa = posCN2 + 21;
		 			  listSpostPot.add(mossa);
				}
				//Spostamenti avanti
				//Sx avanti lungo
				if(scacchiera.get(posCN2 - 12) == null) {
					int mossa = posCN2 - 12;
		 			  listSpostPot.add(mossa);
				}
				//Dx avanti lungo
				if(scacchiera.get(posCN2 + 8) == null) {
					int mossa = posCN2 + 8;
		 			  listSpostPot.add(mossa);
				}
				//Sx avanti corto
				if(scacchiera.get(posCN2 - 21) == null) {
					int mossa = posCN2 - 21;
		 			  listSpostPot.add(mossa);
				}
				//Dx avanti corto
				if(scacchiera.get(posCN2 + 19) == null) {
					int mossa = posCN2 + 19;
		 			  listSpostPot.add(mossa);
				}
				
				return listSpostPot;
		
	}

	/*
	 * abstract boolean attacco (Stato s, int target); che restituisce true se e
	 * solo se nello stato s il pezzo pone sotto attacco la casa target . Si noti
	 * che target deve essere libero oppure occupato da un pezzo avversario. A
	 * proposito di questo ultimo caso, si tenga presente che tale metodo non tiene
	 * conto di eventuali situazioni di scacco (ai danni del proprio re causato
	 * dalla cattura in questione), che rendono la cattura non realizzabile in una
	 * mossa valida.
	 */
	 boolean attacco(Stato s, int target) {
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
	ArrayList<Integer> listaAttacco(Stato s) {
		ArrayList<Integer> listaAttacco = new ArrayList<>();
		boolean colore = s.giocatore;
		for (int i = 0; i < s.scacchiera.riga; i++) {
			for (int k = 0; k < s.scacchiera.colonna; k++) {
			Pezzo attaccante = s.scacchiera.get(s.scacchiera.matrice[i][k]);
			if(attaccante instanceof Cavallo && attaccante.colore == colore) {
				if(colore == true) {
					Pezzo SxAvantiL = s.scacchiera.get(attaccante.pos - 8);
					Pezzo DxAvantiL = s.scacchiera.get(attaccante.pos + 12);
					Pezzo SxAvantiC = s.scacchiera.get(attaccante.pos - 19);
					Pezzo DxAvantiC = s.scacchiera.get(attaccante.pos + 21);
					Pezzo SxindietroL = s.scacchiera.get(attaccante.pos - 12);
					Pezzo DxindietroL = s.scacchiera.get(attaccante.pos + 8);
					Pezzo SxindietroC = s.scacchiera.get(attaccante.pos - 21);
					Pezzo DxindietroC = s.scacchiera.get(attaccante.pos + 19);
					
					if(s.sottoAttacco(SxAvantiL.pos, colore)) {
						listaAttacco.add(SxAvantiL.pos);
					} 
					if(s.sottoAttacco(DxAvantiL.pos, colore)) {
						listaAttacco.add(DxAvantiL.pos);
					} 
					if(s.sottoAttacco(SxAvantiC.pos, colore)) {
						listaAttacco.add(SxAvantiC.pos);
					} 
					if(s.sottoAttacco(DxAvantiC.pos, colore)) {
						listaAttacco.add(DxAvantiC.pos);
					}
					if(s.sottoAttacco(SxindietroL.pos, colore)) {
						listaAttacco.add(SxindietroL.pos);
					} 
					if(s.sottoAttacco(DxindietroL.pos, colore)) {
						listaAttacco.add(DxindietroL.pos);
					} 
					if(s.sottoAttacco(SxindietroC.pos, colore)) {
						listaAttacco.add(SxindietroC.pos);
					} 
					if(s.sottoAttacco(DxindietroC.pos, colore)) {
						listaAttacco.add(DxindietroC.pos);
					} 
				} else {
					
					Pezzo SxindietroL = s.scacchiera.get(attaccante.pos - 8);
					Pezzo DxindietroL = s.scacchiera.get(attaccante.pos + 12);
					Pezzo SxindietroC = s.scacchiera.get(attaccante.pos - 19);
					Pezzo DxindietroC = s.scacchiera.get(attaccante.pos + 21);
					Pezzo SxAvantiL = s.scacchiera.get(attaccante.pos - 12);
					Pezzo DxAvantiL = s.scacchiera.get(attaccante.pos + 8);
					Pezzo SxAvantiC = s.scacchiera.get(attaccante.pos - 21);
					Pezzo DxAvantiC = s.scacchiera.get(attaccante.pos + 19);
					
					if(s.sottoAttacco(SxAvantiL.pos, colore)) {
						listaAttacco.add(SxAvantiL.pos);
					} 
					if(s.sottoAttacco(DxAvantiL.pos, colore)) {
						listaAttacco.add(DxAvantiL.pos);
					} 
					if(s.sottoAttacco(SxAvantiC.pos, colore)) {
						listaAttacco.add(SxAvantiC.pos);
					} 
					if(s.sottoAttacco(DxAvantiC.pos, colore)) {
						listaAttacco.add(DxAvantiC.pos);
					}
					if(s.sottoAttacco(SxindietroL.pos, colore)) {
						listaAttacco.add(SxindietroL.pos);
					} 
					if(s.sottoAttacco(DxindietroL.pos, colore)) {
						listaAttacco.add(DxindietroL.pos);
					} 
					if(s.sottoAttacco(SxindietroC.pos, colore)) {
						listaAttacco.add(SxindietroC.pos);
					} 
					if(s.sottoAttacco(DxindietroC.pos, colore)) {
						listaAttacco.add(DxindietroC.pos);
					} 
					
					
				}
				
			}
			
		}
	}
	return listaAttacco;	
		
}	
	
	public String toString() {
		if (colore == true) {

			return "N";
		} else {
			return "n";
		}
	}
}
