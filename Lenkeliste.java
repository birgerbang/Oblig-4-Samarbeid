public class Lenkeliste<T> implements Liste<T> {
    protected int antall = 0;
    class Node {
        Node neste = null;
        T data;
        Node (T x) {
            data = x;
        }

        public String toString(){
            return data.toString();
        }
    }
    protected Node forsteNode = null;
    protected Node sisteNode = null;

    public boolean erTom(){
        return forsteNode == null;
    }
    
    // Legger til element på slutten av listen
    @Override
    public void leggTil(T x){
        Node nyNode = new Node(x);
        if(erTom()){
            forsteNode = nyNode;
            sisteNode = nyNode;
        } else if(sisteNode == null){
            sisteNode = nyNode;
        } else {
            sisteNode.neste = nyNode;
        }
        sisteNode = nyNode;
        antall ++;
    }

    @Override
    public void leggTil(int pos, T x){
        Node nyNode = new Node(x);
        if (erTom() && pos == 1){
            //Kaster unntak hvis listen er tom og man prøver å legge til på index 1
            throw new UgyldigListeIndeks(pos);
        } else if (erTom()){
            forsteNode = nyNode;
            antall ++;
        } else if(pos > antall || pos < 0){
            //Utfør hvis index out of bounds
            throw new UgyldigListeIndeks(pos);
        } else if (pos == 0){
            //Utfør hvis elementet skal legges til i starten
            Node flytteNode = forsteNode;
            forsteNode = nyNode;
            nyNode.neste = flytteNode;
            antall ++;
        } else if (pos == antall){
            //Utfør hvis element skal legges til på slutten
            sisteNode.neste = nyNode;
            sisteNode = nyNode;
            antall ++;
        } else {
            //Utfører hvis posisjon er midt i listen
            Node peker = forsteNode;
            //Setter pekeren til elementet på plassen før der elementet skal legges inn
            for (int i = 0; i < pos-1; i++){
                peker = peker.neste;
            }
            Node flytteNode = peker.neste;
            peker.neste = nyNode;
            nyNode.neste = flytteNode;
            antall ++;
        }
    }

    @Override
    public T fjern(){
    //fjerner og returnerer det første elementet i listen
        if(erTom()){
            throw new UgyldigListeIndeks(-1);
        } else {
            T data = forsteNode.data;
            if(antall == 1){
                //Kun én node i listen
                forsteNode = null; 
                sisteNode = null;
            } else {
                // Fjerner hvis det er flere enn én node i listen
                forsteNode = forsteNode.neste;
            }
            antall --;
            return data; 
        }
    }

    @Override
    public void sett(int pos, T x){
        Node nyNode = new Node(x);
        if (erTom()){
            //Utfør hvis listen er tom
            throw new UgyldigListeIndeks(pos);
        } else if (pos < 0 || pos >= antall) {
            //Utfør hvis index out of bounds
            throw new UgyldigListeIndeks(pos);
        } else if(pos == 0){
            //Utfør hvis element skal overskrive første element
            Node flytteNode = forsteNode.neste;
            forsteNode = nyNode;
            nyNode.neste = flytteNode;
        } else if(pos == antall){
            //Utfør hvis elementet skal inn på slutten av listen
            sisteNode.neste = nyNode;
        } else {
            //Utfør hvis elementet skal inn midt i listen
            Node peker = forsteNode;
            // setter pekeren til det elementet på plassen før det som skal overskrives
            for (int i = 0; i < pos-1; i++){
                peker = peker.neste;
            }
            Node flytteNode = peker.neste.neste;
            peker.neste = nyNode;
            nyNode.neste = flytteNode;
        }
    }

    // Fjerner og returnerer et element fra listen
    @Override
    public T fjern(int pos){
        T data;
        if (this.erTom()){
            //prøver å fjerne fra tom liste
            throw new UgyldigListeIndeks(-1);
        } else if (pos < 0 || pos >= antall){
            //Index utenfor listen
            throw new UgyldigListeIndeks(pos);
        } else if(pos == 0){
            // Utfør hvis første node skal fjernes
            data = this.fjern();
        } else if(pos == antall-1){
            // Utfør hvis siste node skal fjernes
            Node peker = forsteNode;
            // Setter peker til nest siste node
            while (peker.neste.neste != null){
                peker = peker.neste;
            }
            // henter ut data fra siste node
            data = peker.neste.data;
            // fjerner siste node
            sisteNode = peker;
            sisteNode.neste = null;
            antall --;
        } else {
            //Utfør hvis node er midt i listen
            Node peker = forsteNode;
            // setter peker til noden før den som skal fjernes
            for (int i = 0; i < pos-1; i++){
                peker = peker.neste;
            }
            // henter ut data fra noden som skal fjernes
            data = peker.neste.data;
            // fjerner noden fra listen
            peker.neste = peker.neste.neste;
            antall --;
        }
        return data;
    }

    @Override
    public int stoerrelse(){
        return antall;
    }

    @Override
    public T hent(int pos){
        if(erTom()){
            // Utfør hvis listen er tom
            throw new UgyldigListeIndeks(pos);
        } else if(pos < 0 || pos >= antall){
            // Utfør hvis index out of bounds 
            throw new UgyldigListeIndeks(pos);
        } 
        T data;
        if(pos == 0){
            // Utfør hvis første node skal hentes ut
            data = forsteNode.data;
        } else if(pos == antall-1){
            // Utfør hvis siste node skal hentes ut
            data = sisteNode.data;
        } else {
            //Utfør hvis node er midt i listen
            Node peker = forsteNode;
            // setter peker til noden som skal hentes ut
            for (int i = 0; i < pos; i++){
                peker = peker.neste;
            }
            // henter ut data fra noden
            data = peker.data;
        }
        return data;
    }

    public String toString(){
        Node peker = forsteNode;
        String streng = forsteNode + "" + "\n";
        if (erTom()){
            // Tom liste
            return streng;
        } else {
            while (peker.neste != null){
                peker = peker.neste;
                streng = streng + peker + "\n";
            }
        }
        return streng;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iter = new LenkelisteIterator(lenkeliste);
        return iter;
    }
}
