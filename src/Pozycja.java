import java.io.Serializable;

class Pozycja implements Serializable {
    String nazwaTowaru;
    int ileSztuk;
    double cena;


    void setIleSztuk(int ileSztuk) {
        this.ileSztuk = ileSztuk;
    }

    Pozycja(String nazwaTowaru, int ileSztuk, double cena) {
        this.nazwaTowaru = nazwaTowaru;
        this.ileSztuk = ileSztuk;
        this.cena = cena;
    }
    double obliczWartosc(){return this.ileSztuk*this.cena;}
    double obliczWartoscZRabatem(){
        if(this.ileSztuk>=5&&this.ileSztuk<=10)
            return obliczWartosc()*0.95;
        else if(this.ileSztuk<=20&&this.ileSztuk>10)
            return obliczWartosc()*0.90;
        else if(this.ileSztuk>20)
            return obliczWartosc()*0.85;
        else
            return obliczWartosc();
    }
    public String toString(){
        StringBuilder nazwaTowaru20=new StringBuilder(20);
        nazwaTowaru20.append(this.nazwaTowaru);
        nazwaTowaru20.setLength(20);
        StringBuilder cena10=new StringBuilder(10);
        cena10.append(this.cena+" zl");
        cena10.setLength(13);
        StringBuilder liczbaSztuk4= new StringBuilder(4);
        liczbaSztuk4.append(this.ileSztuk+"szt.");
        liczbaSztuk4.setLength(8);
        StringBuilder wartoscZamowienia10=new StringBuilder(10);
        wartoscZamowienia10.append(obliczWartosc()+" zl");
        wartoscZamowienia10.setLength(13);
        return nazwaTowaru20.toString()+cena10.toString()+liczbaSztuk4.toString()+wartoscZamowienia10.toString();

    }
    public boolean equals(Object o){
        return this.nazwaTowaru.equals(((Pozycja)o).nazwaTowaru)&&this.cena==((Pozycja) o).cena;
    }
}
