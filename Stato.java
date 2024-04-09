package cleii.scacchi;

import java.util.*;

public class Stato {
	Scacchiera scacchiera;
	boolean giocatore;
	boolean arrBianco;
	boolean arrNero;
	boolean enPassBianco;
	boolean enPassNero;
	int enPassant;

	public Stato(Scacchiera s, boolean giocatore, boolean arrBianco, boolean arrNero, boolean enPassBianco,
			boolean enPassNero, int enPassant) {
		this.scacchiera = s;
		this.giocatore = giocatore;
		this.arrBianco = arrBianco;
		this.arrNero = arrNero;
		this.enPassBianco = enPassBianco;
		this.enPassNero = enPassNero;
		this.enPassant = enPassant;
	}

	
	public Stato() {

	}

	/*
	 * che restituisce true se e solo se la casa pos della scacchiera è sotto
	 * attacco da parte di un qualsiasi pezzo bianco (qualora il parametro white
	 * valga true) o nero (qualora il parametro white valgo false). Si noti che pos
	 * deve essere una casa vuota o contenente un pezzo di colore diverso dal colore
	 * che pone sotto attacco.
	 */

	public boolean sottoAttacco(int pos, boolean colore) { // scacchiera.get(pos) colore = true
		for (int i = 0; i < scacchiera.riga; i++) {
			for (int k = 0; k < scacchiera.colonna; k++) {
				if (scacchiera.get(pos) == null || scacchiera.get(pos).colore != colore)
					return true;
			}
		}
		return false;
	}

	/*
	 * che restituisce true se e solo se il re del giocatore di turno (che deve fare
	 * la prossima mossa) si trova sotto scacco, cioè in una casa sotto attacco.
	 */

	public boolean scacco() {
		int posReB = scacchiera.getPos(scacchiera.r1);
		int posReN = scacchiera.getPos(scacchiera.r2);
		Pezzo RB = scacchiera.get(scacchiera.getPos(scacchiera.r1));
		Pezzo RN = scacchiera.get(scacchiera.getPos(scacchiera.r2));
		for (int i = 0; i < RB.listaSpostamentoPotenziale(this).size(); i++) {
			if (this.sottoAttacco(posReB, giocatore) && RB.listaSpostamentoPotenziale(this) != null) {
				return true;
			}
		}
		for (int i = 0; i < RN.listaSpostamentoPotenziale(this).size(); i++) {
			if (this.sottoAttacco(posReN, giocatore) && RN.listaSpostamentoPotenziale(this) != null) {
				return true;
			}
		}
		return false;

	}

	/*
	 * boolean scaccoMatto () che restituisce true se e solo se il re del giocatore
	 * di turno (che deve fare la prossima mossa) si trova sotto scacco e il
	 * giocatore di turno non ha nessuna mossa valida a disposizione. In altre
	 * parole, tutti gli spostamenti potenziali di un qualsiasi suo pezzo o le
	 * catture da esso realizzate non possono realizzare una mossa valida in quanto
	 * lascerebbero il proprio re sotto scacco.
	 */
	boolean scaccoMatto() {
		return !stallo();
	}

	/*
	 * che restituisce true se e solo se il giocatore di turno (che deve fare la
	 * prossima mossa) non ha nessuna mossa valida a disposizione e non ha il
	 * proprio re attualmente sotto scacco. In altre parole, tutti gli spostamenti
	 * potenziali di un qualsiasi suo pezzo o le catture da esso realizzate non
	 * possono realizzare una mossa valida in quanto metterebbero il proprio re
	 * sotto scacco.
	 */

	boolean stallo() {
		List<Pezzo> pezzi = null;
		if (giocatore == true) {
			pezzi = scacchiera.bianchi;
		} else {
			pezzi = scacchiera.neri;
		}

		for (int i = 0; i < pezzi.size(); i++) {
			Pezzo pezzo = pezzi.get(i);
			for (int mossa = 0; mossa < pezzo.listaSpostamentoPotenziale(this).size(); mossa++) {
				boolean valida = mossaValida(pezzo.pos, mossa);
				if (valida)
					return false;
			}
		}
		if (scacco() == false)
			return true;

		return false;
	}

	/*
	 * che restituisce un nuovo stato risultante dalla mossa del giocatore di turno
	 * dalla casa from alla casa to , se ▪ nella casa from è presente un pezzo del
	 * giocatore individuato dal parametro colore ▪ tale mossa rientra tra gli
	 * spostamenti potenziali del pezzo in questione, oppure è diretta verso una
	 * casa, sotto attacco dal pezzo in questione, che contiene un pezzo del
	 * giocatore avversario. Altrimenti restituisce null . Il parametro promozione è
	 * usato unicamente nel caso in cui si tratti di uno spostamento del pedone che
	 * raggiunge la traversa più lontana dalla sua posizione inziale e indica il
	 * pezzo di promozione del pedone: (0=regina, 1=cavallo, 2=alfiere, 3=torre).
	 */
	Stato simulaSpostamentoOCattura(int from, int to, int promozione) {
		Stato nuovo_stato = new Stato(this.scacchiera, this.giocatore, this.arrBianco, this.arrNero, this.enPassBianco,
				this.enPassNero, this.enPassant);

		if (giocatore == scacchiera.get(from).colore
				&& (scacchiera.get(to) == null || scacchiera.get(from).colore != scacchiera.get(to).colore)) {
			Pezzo mossa = scacchiera.get(from); // es. pedone
			Pezzo scambio = scacchiera.get(to); // es,alfiere
			if (mossa.listaSpostamentoPotenziale(this) == null)
				return null;
			for (int i = 0; i < mossa.listaSpostamentoPotenziale(this).size(); i++) {
				int giusto = (int) mossa.listaSpostamentoPotenziale(this).get(i);
				if (giusto == to) {
					scambio = mossa;
					mossa = null;
					scambio.pos = to;
					nuovo_stato.scacchiera.setPos(from, to); // aggiorno la posizione del nuovo scambio (all'inizio era
																// from)
					//Arrocco
					if(scambio instanceof Re) {
						if(giocatore == true) {
							if(scacchiera.posIniziale[7][0].equals(scacchiera.t1) || scacchiera.posIniziale[7][7].equals(scacchiera.t2)) {
								nuovo_stato.arrBianco = true;
							}
						} 
						if(giocatore == false) {
							if(scacchiera.posIniziale[0][0].equals(scacchiera.t3) || scacchiera.posIniziale[0][7].equals(scacchiera.t4)) {
								nuovo_stato.arrNero = true;
							}
						}
						//Se faccio l'arrocco non posso più farlo
						if (giocatore == true && nuovo_stato.arrBianco == true) {
							nuovo_stato.arrBianco = false;
						}
						else if (giocatore == false && nuovo_stato.arrNero == true) {
							nuovo_stato.arrNero = false;
						}
						
					}
					
					// En passant
					if (scambio instanceof Pedone) {
						// Se è una posizione iniziale e mi sposto di due
						if ((giocatore == true && scacchiera.ped_bianco_pos_iniziale.contains(from) && (from + 2 == to))
								|| (giocatore == false && scacchiera.ped_nero_pos_iniziale.contains(from)
										&& (from - 2 == to))) {
							Pezzo destra = scacchiera.get(scambio.pos + 10);
							Pezzo sinistra = scacchiera.get(scambio.pos - 10);
							// Se nella casella a destra o sinistra c'è un pedone di un altro colore
							if ((destra != null && destra.colore == !giocatore && destra instanceof Pedone)
									|| (sinistra != null && sinistra.colore == !giocatore
											&& sinistra instanceof Pedone)) {
								if (giocatore == true) {
									nuovo_stato.enPassNero = true;
									nuovo_stato.enPassant = to - 1;
								} else {
									nuovo_stato.enPassBianco = true;
									nuovo_stato.enPassant = to + 1;
								}
							}
						}
					}
					// Se posso fare en passant, nel nuovo stato non potrò più farlo
					if (giocatore == true && nuovo_stato.enPassBianco == true) {
						nuovo_stato.enPassBianco = false;
					}
					else if (giocatore == false && nuovo_stato.enPassNero == true) {
						nuovo_stato.enPassNero = false;
					}

					if (promozione > 3 || promozione < 0)
						return nuovo_stato;
					if (scambio instanceof Pedone) {
						if (giocatore == true) {
							// Promozione bianco
							for (int j = 18; j <= 88; j = j + 10) {
								if (to == j) {
									if (promozione == 0) {
										nuovo_stato.scacchiera.setPezzo(to, new Regina(true, to));
									} else if (promozione == 1) {
										nuovo_stato.scacchiera.setPezzo(to, new Cavallo(true, to));
									} else if (promozione == 2) {
										nuovo_stato.scacchiera.setPezzo(to, new Alfiere(true, to));
									} else {
										nuovo_stato.scacchiera.setPezzo(to, new Torre(true, to));
									}
								}
							}
						} else {
							// Promozione nero
							for (int k = 11; k <= 81; k = k + 10) {
								if (to == k) {
									if (promozione == 0) {
										nuovo_stato.scacchiera.setPezzo(to, new Regina(false, to));
									} else if (promozione == 1) {
										nuovo_stato.scacchiera.setPezzo(to, new Cavallo(false, to));
									} else if (promozione == 2) {
										nuovo_stato.scacchiera.setPezzo(to, new Alfiere(false, to));
									} else {
										nuovo_stato.scacchiera.setPezzo(to, new Torre(false, to));
									}
								}
							}
						}
					}

				}

			}

		}
		

		return nuovo_stato;
	}

	Stato simulaSpostamentoOCattura(int from, int to)  {
		return simulaSpostamentoOCattura(from, to, 0);
	}

	/*
	 * che simula la mossa del giocatore di turno dalla casa from alla casa to e
	 * restituisce true se e solo se tale mossa è valida. Il parametro promozione è
	 * usato unicamente nel caso in cui la mossa corrisponde a uno spostamento del
	 * pedone che raggiunge la traversa più lontana dalla sua posizione inziale e
	 * indica il pezzo di promozione del pedone: (0=regina, 1=cavallo, 2=alfiere,
	 * 3=torre). Per essere valida, devono valere entrambe le seguenti condizioni: ▪
	 * simulaSpostamentoOCattura (from, to, promozione) non deve restituire null ; ▪
	 * lo Stato restituito da simulaSpostamentoOCattura (from, to, promozione) non
	 * deve essere tale che il proprio re si trovi in una situazione di scacco
	 * (ovvero si trovi in una casa sotto attacco da parte di un pezzo del giocatore
	 * avversario).
	 */

	boolean mossaValida(int from, int to, int promozione)  {
		Stato simulato = simulaSpostamentoOCattura(from, to, promozione);
		if (simulato == null) {return false;}

		int posRB = simulato.scacchiera.getPos(scacchiera.r1);
		int posRN = simulato.scacchiera.getPos(scacchiera.r2);
		if (simulato.sottoAttacco(posRB, giocatore)) {
			return false;
		} else if (simulato.sottoAttacco(posRN, giocatore)) {
			return false;
		} else {
			return true;
		}
	}

	boolean mossaValida(int from, int to) {
		return mossaValida(from, to, 0);
	}

	/*
	 * che modifica lo stato in modo da eseguire la mossa del giocatore di turno
	 * dalla casa from alla casa to , e restituisce true , se tale mossa è valida;
	 * altrimenti lascia invariato lo stato e restituisce false . Il parametro
	 * promozione è usato unicamente nel caso in cui la mossa corrisponde a uno
	 * spostamento del pedone che raggiunge la traversa più lontana dalla sua
	 * posizione inziale e indica il pezzo di promozione del pedone: (0 = regina, 1=
	 * cavallo, 2=alfiere, 3=torre).
	 */
	boolean eseguiMossa(int from, int to, int promozione) {
		if (this.mossaValida(from, to, promozione)) {
			return true;
		} else {
			return false;
		}

	}

	boolean eseguiMossa(int from, int to)  {
		return eseguiMossa(from, to, 0);
	}
	
	public boolean getGiocatore() {
		return giocatore;
	}
	
	public void setGiocatore(boolean giocatore) {
		this.giocatore = giocatore;
	}

}