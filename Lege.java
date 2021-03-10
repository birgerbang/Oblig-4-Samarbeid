public class Lege {
    String legeNavn;

    public Lege(String legeNavn){
        this.legeNavn = legeNavn;
    }

    public String hentLegeNavn(){
        return this.legeNavn;
    }

    public String toString(){
        return "Legens navn: " + hentLegeNavn();
    }
}
