public class Stabel<T> extends Lenkeliste<T> {
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
    
    public void leggPaa(T x){
        //legger til på slutten av listen
        super.leggTil(x);
    }

    public T taAv(){
        //Fjerner siste element fra listen
        return super.fjern(antall - 1);
    }
}
