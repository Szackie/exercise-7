import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Zamowienie implements Serializable {
    ArrayList<Pozycja> pozycje = new ArrayList<>();
    int maksRozmiar;
    double rabat=0;

    @SuppressWarnings("unused")
    Zamowienie() {
        this.maksRozmiar = 10;
    }

    Zamowienie(int maksRozmiar) {
        this.maksRozmiar = maksRozmiar;
    }

    void dodajPozycje(Pozycja p) {

        if (!pozycje.contains(p))
            this.pozycje.add(p);
        else {
            p.setIleSztuk(p.ileSztuk + pozycje.get(pozycje.indexOf(p)).ileSztuk);
            pozycje.remove(p);
            pozycje.add(p);
        }

    }

    double obliczWartosc() {
        double wartosc = 0;
        rabat=0;

        for (Pozycja pozycja : this.pozycje) {
            wartosc += pozycja.obliczWartoscZRabatem();
            rabat += pozycja.obliczWartosc() - pozycja.obliczWartoscZRabatem();
        }

        return wartosc;
    }

    public String toString() {
        StringBuilder lancuch = new StringBuilder();
        int id = 1;
        for (Pozycja pozycja : this.pozycje) {
            lancuch.append(id).append(". ").append(pozycja.toString());
            lancuch.append("\n");
            id++;
        }
        lancuch.append("Razem: ").append(obliczWartosc()).append(" zl.");
        lancuch.append("\n");

        if(rabat>0)
            lancuch.append("Suma rabatu: ").append(rabat);

        return "Zamowienie:\n" + lancuch;
    }

    void usunPozycje(int indeks) {
        this.pozycje.remove(indeks);
    }

    void edytujPozycje(int indeks) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj nową nazwę i wciśnij \"Enter\"");
        this.pozycje.get(indeks - 1).nazwaTowaru = scanner.next();

        System.out.println("Podaj nową cenę i wciśnij \"Enter\"");
        double nowaCena;
        nowaCena = scanner.nextDouble();
        this.pozycje.get(indeks - 1).cena = nowaCena;

        System.out.println("Podaj nową liczbę sztuk i wciśnij \"Enter\"");
        this.pozycje.get(indeks - 1).ileSztuk = scanner.nextInt();

    }

    public static void zapiszZamowienie(Zamowienie z,String nazwaPliku){
        File plik=new File(nazwaPliku);
        while(plik.exists())
        {
            System.out.println("Plik o tej nazwie już istnieje. Potwierdź nazwę, jeśli chcesz go nadpisać, lub wpisz nową nazwę, jeśli chcesz utworzyć nowy plik");
            Scanner scanner=new Scanner(System.in);
            String nowaNazwaPliku=scanner.next();
            if(nowaNazwaPliku.equals(nazwaPliku))
                break;
            nazwaPliku=nowaNazwaPliku;
            plik=new File(nazwaPliku);
        }
        try {
            ObjectOutputStream outS= new ObjectOutputStream(new FileOutputStream(nazwaPliku));
            outS.writeObject(z);
            outS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Zamowienie wczytajZamowienie(String nazwaPliku){
        Zamowienie zamowienie=null;
        try{
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(nazwaPliku));
            zamowienie=(Zamowienie)objectInputStream.readObject();
            objectInputStream.close();
            return zamowienie;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return zamowienie;
    }


    public static void main(String[] args) {
        Pozycja p1 = new Pozycja("Chleb", 1, 3.5);
        System.out.println(p1);
        Pozycja p2 = new Pozycja("Cukier", 3, 4);
        Pozycja p3 = new Pozycja("Cukier", 1, 4);
        Pozycja p4 = new Pozycja("Cukier", 1, 4.5);

        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        Zamowienie z = new Zamowienie(20);
        z.dodajPozycje(p1);
        z.dodajPozycje(p2);
        z.dodajPozycje(p3);
        z.dodajPozycje(p4);
        System.out.println(z);
 //       z.edytujPozycje(2);
        System.out.println(z);
        z.usunPozycje(2);
        System.out.println(z);
    zapiszZamowienie(z,"C:\\Users\\Szymon\\IdeaProjects\\Exercise 7\\zamowienie.bin");
        System.out.println( wczytajZamowienie("C:\\Users\\Szymon\\IdeaProjects\\Exercise 7\\zamowienie.bin"));
    }
}