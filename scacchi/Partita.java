package cleii.scacchi;

import java.util.ArrayList;

class Mossa {
	int from;
	int to;
	public Mossa(int a, int b) {
		from = a;
		to = b;
	}
}

public class Partita {
	private Stato s;
	private ArrayList<Mossa> mosse;
	private int esito; // 0 = in corso; 1 = vittoria bianco; -1 vittoria nero; 2 patta.
	
	public Partita() {
		s = new Stato();
		mosse = new ArrayList<>();
		esito = 0;
	}
	
	public void eseguiMossa (int from, int to, int promozione) throws EccezioneMossa{
		if(esito != 0) {
			try {
				throw new EccezioneMossa();
			} catch (EccezioneMossa eccezione) {
				System.out.println(eccezione);
			}
			
		} else {
			if(s.mossaValida(from, to, promozione)) {
				s.eseguiMossa(from, to, promozione);
				Mossa x = new Mossa(from, to);
				mosse.add(x);
				
				if(s.getGiocatore()) {
					s.setGiocatore(false);
				} else {
					s.setGiocatore(true);
				}
			}
		}
		if(s.stallo()) esito = 2;
		
		if(s.scaccoMatto()) {
			System.out.println("SCACCO MATTO!");
			if(s.getGiocatore()) {
				esito = 1;
			} else {
				esito = -1;
			}
		} else {
			try {
				throw new EccezioneMossa();
			} catch (EccezioneMossa eccezione) {
				System.out.println(eccezione);
			}
		}
		
		
		
	}
	
	public void eseguiMossa (int from, int to) throws EccezioneMossa{
		eseguiMossa (from, to, 0);
	}
	public void abbandona() {
		if(esito == 0) {
			if(s.getGiocatore()) {
				esito = -1;
			} else {
				esito = 1;
			}
		}
	}
	public boolean inCorso() {
		if(esito == 0) {
			return true;
		} else  {
			return false;
		}
	}
	public boolean vittoriaBianco() {
		if(esito == 1) {
			return true;
		}else {
			return false;
		}
	}
	public boolean vittoriaNero() {
		if(esito == -1) {
			return true;
		}else {
			return false;
		}
	}
	public boolean patta() {
		if(esito == 2) {
			return true;
		}else {
			return false;
		}
	}

}
