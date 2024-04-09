package cleii.scacchi;

import java.util.*;
import java.io.*;

public class Scacchiera {
	final int riga = 8;
	final int colonna = 8;

	public final int matrice[][];
	public Pezzo posIniziale[][];

	public Pedone p1 = new Pedone(true, 12);
	public Pedone p2 = new Pedone(true, 22);
	public Pedone p3 = new Pedone(true, 32);
	public Pedone p4 = new Pedone(true, 42);
	public Pedone p5 = new Pedone(true, 52);
	public Pedone p6 = new Pedone(true, 62);
	public Pedone p7 = new Pedone(true, 72);
	public Pedone p8 = new Pedone(true, 82);
	public Torre t1 = new Torre(true, 11);
	public Torre t2 = new Torre(true, 81);
	public Cavallo c1 = new Cavallo(true, 21);
	public Cavallo c2 = new Cavallo(true, 71);
	public Alfiere a1 = new Alfiere(true, 31);
	public Alfiere a2 = new Alfiere(true, 61);
	public Regina rg1 = new Regina(true, 41);
	public Re r1 = new Re(true, 51);

	public List<Pezzo> bianchi = new ArrayList<>(
			List.of(p1, p2, p3, p4, p5, p6, p7, p8, t1, t2, c1, c2, a1, a2, rg1, r1));
	public final ArrayList<Integer> ped_bianco_pos_iniziale = new ArrayList<Integer>(
			List.of(12, 22, 32, 42, 52, 62, 72, 82));

	public Pedone p9 = new Pedone(false, 17);
	public Pedone p10 = new Pedone(false, 27);
	public Pedone p11 = new Pedone(false, 37);
	public Pedone p12 = new Pedone(false, 47);
	public Pedone p13 = new Pedone(false, 57);
	public Pedone p14 = new Pedone(false, 67);
	public Pedone p15 = new Pedone(false, 77);
	public Pedone p16 = new Pedone(false, 87);
	public Torre t3 = new Torre(false, 18);
	public Torre t4 = new Torre(false, 88);
	public Cavallo c3 = new Cavallo(false, 28);
	public Cavallo c4 = new Cavallo(false, 78);
	public Alfiere a3 = new Alfiere(false, 38);
	public Alfiere a4 = new Alfiere(false, 68);
	public Regina rg2 = new Regina(false, 58);
	public Re r2 = new Re(false, 48);

	public List<Pezzo> neri = new ArrayList<>(
			List.of(p9, p10, p11, p12, p13, p14, p15, p16, t3, t4, c3, c4, a3, a4, rg2, r2));
	public final ArrayList<Integer> ped_nero_pos_iniziale = new ArrayList<Integer>(
			List.of(17, 27, 37, 47, 57, 67, 77, 87));

	public Scacchiera() {

		this.matrice = new int[riga][colonna];
		this.posIniziale = new Pezzo[riga][colonna];

		int numeroIniziale = 18;

		for (int i = 0; i < riga; i++) {
			for (int j = 0; j < colonna; j++) {
				matrice[i][j] = numeroIniziale;
				numeroIniziale = numeroIniziale + 10;
			}
			numeroIniziale = numeroIniziale - 81;
		}

		posIniziale[1][0] = p9;
		posIniziale[1][1] = p10;
		posIniziale[1][2] = p11;
		posIniziale[1][3] = p12;
		posIniziale[1][4] = p13;
		posIniziale[1][5] = p14;
		posIniziale[1][6] = p15;
		posIniziale[1][7] = p16;
		posIniziale[6][0] = p1;
		posIniziale[6][1] = p2;
		posIniziale[6][2] = p3;
		posIniziale[6][3] = p4;
		posIniziale[6][4] = p5;
		posIniziale[6][5] = p6;
		posIniziale[6][6] = p7;
		posIniziale[6][7] = p8;
		posIniziale[0][0] = t3;
		posIniziale[0][7] = t4;
		posIniziale[0][1] = c3;
		posIniziale[0][6] = c4;
		posIniziale[0][2] = a3;
		posIniziale[0][5] = a4;
		posIniziale[0][3] = r2;
		posIniziale[0][4] = rg2;
		posIniziale[7][0] = t1;
		posIniziale[7][7] = t2;
		posIniziale[7][1] = c1;
		posIniziale[7][6] = c2;
		posIniziale[7][2] = a1;
		posIniziale[7][5] = a2;
		posIniziale[7][3] = rg1;
		posIniziale[7][4] = r1;

	}

	public static void stampaMatricecase(Scacchiera S) {
		int x = S.riga;
		int j = S.colonna;
		for (int i = 0; i < x; i++) {
			for (int k = 0; k < j; k++) {
				System.out.print(" " + S.matrice[i][k]);
			}
			System.out.println();
		}
	}

	public Pezzo get(int pos) {
		for (int i = 0; i < riga; i++) {
			for (int k = 0; k < colonna; k++) {
				if (matrice[i][k] == pos) {
					return posIniziale[i][k];
				}
			}
		}
		return null;
	}

	public void setPezzo(int pos, Pezzo p) {
		for (int i = 0; i < riga; i++) {
			for (int k = 0; k < colonna; k++) {
				if (matrice[i][k] == pos) {
					posIniziale[i][k] = p;
				}
			}
		}
	}

	public int getPos(Pezzo p) {
		if (get(p.pos) == null) {
			return 0;
		} else
			return p.pos;
	}

	public void setPos(int from, int to)  {
		Pezzo p = get(from);
		p.pos = to;
		boolean from_aggiornato = false;
		boolean to_aggiornato = false;
		for (int i = 0; i < riga; i++) {
			for (int k = 0; k < colonna; k++) {
				if (matrice[i][k] == from) {
					posIniziale[i][k] = null; // la vecchia posizione diventa null
					from_aggiornato = true;
				}
				if (matrice[i][k] == to) {
					posIniziale[i][k] = p; // metto il pezzo nella nuova posizione
					to_aggiornato = true;
				}
			}
		}
	}

	public String toString() { // rappresenta la scacchiera
		String stampa = "";
		for (int i = 0; i < riga; i++) {
			for (int k = 0; k < colonna; k++) {
				if (posIniziale[i][k] == null) {
					stampa = stampa + " .";
				} else {
					stampa = stampa + " " + posIniziale[i][k];
				}
			}
			stampa = stampa + "\n";
		}
		return stampa;
	}
}