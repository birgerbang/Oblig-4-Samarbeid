public class LenkelisteIterator extends Iterator<T>{

    public LenkelisteIterator(Lenkeliste<T> lenkeliste) {
    }
    boolean hasNext(){
        if this.lenkeliste.erTom(){
            return false
        }
    }
    T next(){
        
    }
    void remove();

} 