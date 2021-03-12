public class legeTest {
    public static void main(String[] args) throws UlovligUtskrift{
        kjorTest();
    }

    public static void kjorTest() throws UlovligUtskrift{
        Lege heidi = new Lege("Heidi");
        Lege ina = new Lege("Ina");
        Lege fredrik = new Lege("Fredrik");
        Pasient john = new Pasient("John", "11080829974");
        Narkotisk oxicotin = new Narkotisk("Oxicotin", 200, 12.5, 20);
        Vanlig prevasjonstabletter = new Vanlig("prevasjonstabletter", 200, 20);
        BlaaResept resept = new BlaaResept(oxicotin, ina, john, 10);
        try {
            ina.skrivBlaaResept(oxicotin, john, 10);
        } catch (UlovligUtskrift e) {
            System.out.println(e);
        }

        ina.skrivPResept(prevasjonstabletter, john);
        
        System.out.println(heidi.compareTo(ina));
        System.out.println(heidi.compareTo(fredrik));
        System.out.println(ina.hentUtskrevedeResepter());
    }
}
