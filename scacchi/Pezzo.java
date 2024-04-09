package cleii.scacchi;

import java.util.ArrayList;

public abstract class Pezzo {

	protected boolean colore;
	protected int pos;

	public Pezzo(boolean colore, int pos) {
		this.colore = colore;
		this.pos = pos;
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
	abstract boolean spostamentoPotenziale(Stato s, int target);

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

		for (int i = 0; i < scacchiera.riga; i++) {
			for (int k = 0; k < scacchiera.colonna; k++) {
				if (s.sottoAttacco(scacchiera.matrice[i][k], colore) == false) {
					listSpostPot.add(scacchiera.matrice[i][k]);
				}
			}
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
	abstract boolean attacco(Stato s, int target);

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
		Scacchiera scacchiera = s.scacchiera;
		ArrayList<Integer> listaAttacco = new ArrayList<>();
		boolean colore = s.giocatore;
		for (int i = 0; i < scacchiera.riga; i++) {
			for (int k = 0; k < scacchiera.colonna; k++) {
				if (s.sottoAttacco(scacchiera.matrice[i][k], colore)) {
					listaAttacco.add(scacchiera.matrice[i][k]);
				}
			}
		}
		return listaAttacco;
	}

}