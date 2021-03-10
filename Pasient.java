public class Pasient{
    String navn;
    String fodselsnummer;
    static int teller;
    int id = 0;
    Stabel<Resept> reseptListe;

    public Pasient(String navn, String fodselsnummer){
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        teller ++;
        id = teller;
        reseptListe = new Stabel<Resept>();
    }

    protected int hentPasientId(){
        return id;
    }

    public void leggTilResept(Resept resept){
        reseptListe.leggPaa(resept);
    }

    public Stabel hentReseptListe(){
        return reseptListe;
    }

    public String toString(){
        return ("Navn: " + this.navn + "\nFødselsnummer: " + this.fodselsnummer + "\nPasient ID: " + id);
    }
}