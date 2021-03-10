public class pasientTest {
    public static void main(String[] args){
        testPasient();
    }

    public static void testPasient(){
        Pasient john = new Pasient("John", "11080829974");
        Lege nina = new Lege("Nina");
        Narkotisk oxicotin = new Narkotisk("Oxicotin", 200, 12.5, 20);
        BlaaResept resept = new BlaaResept(oxicotin, nina, john, 10);
        john.leggTilResept(resept);
        System.out.println(john);
        System.out.println(john.hentReseptListe());
    }
}
