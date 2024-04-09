# Representation-and-execution-of-a-game-of-chess
Development in Java of a package for the representation and execution of chess games.

Il package deve contenere le seguenti classi:

  o Scacchiera
  
    Classe pubblica che rappresenta una scacchiera di 64 caselle. Ogni casella deve essere
    capace di ospitare un pezzo. Possiede un costruttore senza parametri che crea una
    scacchiera con i pezzi nella configurazione iniziale.
    In tutti i metodi, se non diversamente specificato, in modo simile a quanto si usa fare nella
    cosiddetta notazione numerica (https://it.wikipedia.org/wiki/Notazione_numerica), le case
    della scacchiera sono indicate con un numero intero a due cifre come di seguito riportato:
    
                                        18 28 38 48 58 68 78 88
                                        17 27 37 47 57 67 77 87
                                        16 26 36 46 56 66 76 86
                                        15 25 35 45 55 65 75 85
                                        14 24 34 44 54 64 74 84
                                        13 23 33 43 53 63 73 83
                                        12 22 32 42 52 62 72 82
                                        11 21 31 41 51 61 71 81
    Contiene i seguenti metodi:
      o Pezzo get (int pos)
        che restituisce il pezzo in posizione pos, null se tale posizione è libera.
      o int getPos (Pezzo p)
        che restituisce la posizione del pezzo p. Restituisce 0 se tale pezzo non è presente sulla
        scacchiera.
      o metodo toString che deve rappresentare la scacchiera nella seguente modalità
        testuale (nell’esempio è riportata la posizione iniziale: si noti che le maiuscole
        identificano i pezzi del bianco e le minuscole quelli del nero; le case vuote sono indicate
        da un punto):
        Le colonne, come le righe (traverse), sono numerate da
        1 a 8. Nella notazione numerica tutte le case sono
        individuate da una coppia di cifre, la prima descrive la
        colonna (dalla sinistra alla destra del Bianco) e la
        seconda la riga (o traversa, dalla prima traversa in basso
        del Bianco all'ultima).
    
  o Stato
  
    Classe pubblica che rappresenta lo stato del gioco. Lo stato del gioco è identificato da tutte
    le informazioni necessarie per codificare:
      o la scacchiera
      o il giocatore che deve fare la prossima mossa
      o le possibilità della mossa di arrocco per il bianco
      o le possibilità della mossa di arrocco per il nero
      o le possibilità della cattura “en passant” per il bianco
      o le possibilità della cattura “en passant” per il nero
      
    Contiene i seguenti metodi:
    
      o boolean sottoAttacco (int pos, boolean white)
        che restituisce true se e solo se la casa pos della scacchiera è sotto attacco da parte di
        un qualsiasi pezzo bianco (qualora il parametro white valga true) o nero (qualora il
        parametro white valgo false). Si noti che pos deve essere una casa vuota o contenente
        un pezzo di colore diverso dal colore che pone sotto attacco.
      o boolean scacco ()
        che restituisce true se e solo se il re del giocatore di turno (che deve fare la prossima
        mossa) si trova sotto scacco, cioè in una casa sotto attacco.
        o boolean scaccoMatto ()
        che restituisce true se e solo se il re del giocatore di turno (che deve fare la prossima
        mossa) si trova sotto scacco e il giocatore di turno non ha nessuna mossa valida a
        disposizione. In altre parole, tutti gli spostamenti potenziali di un qualsiasi suo pezzo o le
        catture da esso realizzate non possono realizzare una mossa valida in quanto
        lascerebbero il proprio re sotto scacco.
      o boolean stallo ()
        che restituisce true se e solo se il giocatore di turno (che deve fare la prossima mossa)
        non ha nessuna mossa valida a disposizione e non ha il proprio re attualmente sotto
        scacco. In altre parole, tutti gli spostamenti potenziali di un qualsiasi suo pezzo o le
        catture da esso realizzate non possono realizzare una mossa valida in quanto
        metterebbero il proprio re sotto scacco.
      o Stato simulaSpostamentoOCattura (int from, int to, int
        promozione)
        che restituisce un nuovo stato risultante dalla mossa del giocatore di turno dalla casa
        from alla casa to, se
        ▪ nella casa from è presente un pezzo del giocatore individuato dal parametro
        white
        ▪ tale mossa rientra tra gli spostamenti potenziali del pezzo in questione, oppure
        è diretta verso una casa, sotto attacco dal pezzo in questione, che contiene un
        pezzo del giocatore avversario.
        Altrimenti restituisce null.
        Il parametro promozione è usato unicamente nel caso in cui si tratti di uno
        spostamento del pedone che raggiunge la traversa più lontana dalla sua posizione
        inziale e indica il pezzo di promozione del pedone: (0=regina, 1=cavallo, 2=alfiere,
        3=torre).
      o Stato simulaSpostamentoOCattura (int from, int to)
        che richiama e restituisce simulaSpostamentoOCattura (from, to, 0)
      o boolean mossaValida (int from, int to, int promozione)
        che simula la mossa del giocatore di turno dalla casa from alla casa to e restituisce
        true se e solo se tale mossa è valida.
        Il parametro promozione è usato unicamente nel caso in cui la mossa corrisponde a
        uno spostamento del pedone che raggiunge la traversa più lontana dalla sua posizione
        inziale e indica il pezzo di promozione del pedone: (0=regina, 1=cavallo, 2=alfiere,
        3=torre).
        Per essere valida, devono valere entrambe le seguenti condizioni:
        ▪ simulaSpostamentoOCattura (from, to, promozione) non deve
        restituire null;
        ▪ lo Stato restituito da simulaSpostamentoOCattura (from, to,
        promozione) non deve essere tale che il proprio re si trovi in una situazione
        di scacco (ovvero si trovi in una casa sotto attacco da parte di un pezzo del
        giocatore avversario).
      o boolean mossaValida (int from, int to)
        che richiama e restituisce mossaValida (from, to, 0)
      o boolean eseguiMossa (int from, int to, int promozione)
        che modifica lo stato in modo da eseguire la mossa del giocatore di turno dalla casa
        from alla casa to, e restituisce true, se tale mossa è valida; altrimenti lascia invariato
        lo stato e restituisce false.
        Il parametro promozione è usato unicamente nel caso in cui la mossa corrisponde a
        uno spostamento del pedone che raggiunge la traversa più lontana dalla sua posizione
        inziale e indica il pezzo di promozione del pedone: (0 = regina, 1= cavallo, 2=alfiere,
        3=torre).
      o boolean eseguiMossa (int from, int to)
        che richiama e restituisce eseguiMossa (from, to, 0)

    
  o Pezzo (classe astratta)
  
    Classe pubblica per rappresentare i pezzi che possono essere posizionati sulla scacchiera.
    Contiene un parametro che specifica se il pezzo è bianco o nero, e i seguenti metodi astratti:
    
    o abstract boolean spostamentoPotenziale (Stato s, int target);
      che restituisce true se e solo se il pezzo può muovere nello stato s dalla propria casa
      alla casa target (che deve essere libera in s). Si tenga presente che negli spostamenti
      del re bisogna considerare anche l’eventuale arrocco (che invece non va considerato tra
      gli spostamenti della torre). Tale metodo non tiene conto di eventuali situazioni di
      scacco (ai danni del proprio re causato dal movimento in questione), che rendono lo
      spostamento non realizzabile in una mossa valida.
    o ArrayList<Integer> listaSpostamentoPotenziale (Stato s)
      che restituisce un arraylist contenente tutte e sole le posizioni della scacchiera verso le
      quali il pezzo può muovere a partire dallo stato s. Si noti che le posizioni restituite
      devono corrispondere a una casa libera in s e che negli spostamenti del re bisogna
      considerare anche l’eventuale arrocco (che invece non va considerato tra gli
      spostamenti della torre). Tale metodo non tiene conto di eventuali situazioni di scacco
      (ai danni del proprio re causato dal movimento in questione), che rendono lo
      spostamento non realizzabile in una mossa valida.
      Si noti infine che, sebbene tale metodo possa essere implementato direttamente nella
      classe Pezzo, può avere senso sovrascriverlo nelle sottoclassi in modo da renderne più
      efficiente l’implementazione.
    o abstract boolean attacco (Stato s, int target);
      che restituisce true se e solo se nello stato s il pezzo pone sotto attacco la casa
      target. Si noti che target deve essere libero oppure occupato da un pezzo
      avversario. A proposito di questo ultimo caso, si tenga presente che tale metodo non
      tiene conto di eventuali situazioni di scacco (ai danni del proprio re causato dalla
      cattura in questione), che rendono la cattura non realizzabile in una mossa valida.
    o ArrayList<Integer> listaAttacco (Stato s)
      che restituisce un arraylist contenente tutte e sole le posizioni della scacchiera che
      sono sotto attacco da parte del pezzo nello stato s. Si noti che le posizioni restituite
      devono corrispondere a una casa libera oppure occupata da un pezzo avversario. Si noti
      infine che, sebbene tale metodo possa essere implementato direttamente nella classe
      Pezzo, può avere senso sovrascriverlo nelle sottoclassi in modo da renderne più
      efficiente l’implementazione.
      
    Tale classe astratta ha le seguenti classi pubbliche che la estendono per rappresentare i vari
    pezzi del gioco:
      o Pedone
      o Torre
      o Alfiere
      o Cavallo
      o Regina
      o Re
      
    
  o Partita
  
      Classe pubblica per rappresentare una partita del gioco. Contiene i campi per memorizzare:
      o lo stato corrente del gioco;
      o le mosse della partita;
      o l’eventuale esito della partita (in corso, vittoria del bianco, vittoria del nero, patta)
      
    Contiene i seguenti metodi:
      o Un costruttore senza parametri che crea una partita senza mosse.
      o public void eseguiMossa (int from, int to, int promozione) throws EccezioneMossa
        che, qualora la partita sia in corso, modifica lo stato in modo da eseguire la mossa del
        giocatore di turno dalla casa from alla casa to, e memorizza la mossa nell’opportuna
        struttura dati, se tale mossa è valida; altrimenti solleva un’eccezione
        EccezioneMossa (controllata) di mossa non valida.
        Si tenga presente che per effettuare una mossa di arrocco i parametri from e to si
        riferiscono allo spostamento del re.
        Se la mossa eseguita conduce in uno stato di stallo, la partita raggiunge l’esito di patta.
        Se la mossa eseguita conduce in uno stato di scacco matto, la partita raggiunge l’esito
        di vittoria dell’opportuno giocatore.
        Il parametro promozione è usato unicamente nel caso in cui la mossa corrisponde a
        uno spostamento del pedone che raggiunge la traversa più lontana dalla sua posizione
        inziale e indica il pezzo di promozione del pedone: (0=regina, 1=cavallo, 2=alfiere,
        3=torre).
      o public void eseguiMossa (int from, int to) throws EccezioneMossa
        che richiama eseguiMossa (from, to, 0)
      o public void abbandona()
        che, qualora la partita sia in corso, causa l’abbandono del giocatore di turno,
        assegnando quindi la vittoria al suo avversario.
      o public boolean inCorso()
        che restituisce true se e solo se la partita è attualmente in corso
      o public boolean vittoriaBianco()
        che restituisce true se e solo se la partita è terminata con la vittoria del bianco
      o public boolean vittoriaNero()
        che restituisce true se e solo se la partita è terminata con la vittoria del nero
      o public boolean patta()
        che restituisce true se e solo se la partita è terminata come patta.

  
  o EccezioneMossa
    Classe pubblica per rappresentare l’opportuna eccezione in caso di mossa non valida.
