public class TestIterator {
    public static void main(String[] args) {
    
    Lenkeliste<Lege> lista = new Lenkeliste<Lege>();
    Lege overlegen = new Lege("Over Legen");
    Lege overlegg = new Lege("Under Legen");
    Lege underlegg = new Lege("Over Under");
    Lege absolutt = new Lege("Streng Lege");
    lista.leggTil(overlegen);
    lista.leggTil(overlegg);
    lista.leggTil(underlegg);
    lista.leggTil(absolutt);
    for (Lege e : lista){
        System.out.println(e.hentNavn());
        }
    } 
}
