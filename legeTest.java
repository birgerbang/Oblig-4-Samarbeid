public class legeTest {
    public static void main(String[] args) throws UlovligUtskrift{
        kjorTest();
    }

    public static void kjorTest() throws UlovligUtskrift{
        Lege heidi = new Lege("Heidi");
        Lege ina = new Lege("Ina");
        Spesialist fredrik = new Spesialist("Fredrik", "593");
        Pasient john = new Pasient("John", "11080829974");
        Narkotisk oxicotin = new Narkotisk("Oxicotin", 200, 12.5, 20);
        Vanlig prevasjonstabletter = new Vanlig("prevasjonstabletter", 200, 20);
        try {
            ina.skrivBlaaResept(oxicotin, john, 10);
        } catch (UlovligUtskrift e) {
            System.out.println(e);
        }

        ina.skrivPResept(prevasjonstabletter, john);
        ina.skrivHvitResept(prevasjonstabletter, john, 10);
        fredrik.skrivMilitaerResept(prevasjonstabletter, john, 10);
        fredrik.skrivBlaaResept(oxicotin, john, 10);

        
        System.out.println(heidi.compareTo(ina));
        System.out.println(heidi.compareTo(fredrik));
        System.out.println(ina.hentUtskrevedeResepter());
    }
}
