public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{
    @Override
    public void leggTil(T x){
        if (erTom()){
            //Listen er tom, vi setter inn det nye elementet
            super.leggTil(x);
        } else {
            //Listen er ikke tom
            Node peker = forsteNode;
            //Leter oss fram til første element større enn det vi skal legge inn
            int i = 0;
            boolean plassert = false;
            while (peker != null){
                if (peker.data.compareTo(x) > 0){
                    super.leggTil(i, x);
                    plassert = true;
                    break;
                }
                peker = peker.neste;
                i++;
            }
            if (!plassert){
                super.leggTil(x);
            }
        }
    }
    
    @Override
    public T fjern(){
        //Fjerner site element i listen
            if(antall == 1){
                T data = forsteNode.data;
                //Kun én node i listen
                forsteNode = null; 
                sisteNode = null;
                antall --;
                return data;
            } else {
                return super.fjern(antall-1);
            }
    }
/*
    @Override
    public T fjern(){
        //Fjerner site element i listen
        System.out.println("Kaller på child fjern");
        return super.fjern(antall-1);
    }
*/
    @Override
    public void sett(int pos, T x){
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggTil(int pos, T x){
        throw new UnsupportedOperationException();
    }
}
