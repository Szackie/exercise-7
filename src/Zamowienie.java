import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Zamowienie {
    ArrayList<Pozycja> pozycje = new ArrayList<>();
    int ileDodanych;
    int maksRozmiar;
    double rabat=0;

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
            lancuch.append(id + ". " + pozycja.toString());
            lancuch.append("\n");
            id++;
        }
        lancuch.append("Razem: " + obliczWartosc() + " zl.");
        lancuch.append("\n");

        if(rabat>0)
            lancuch.append("Suma rabatu: "+rabat);

        return "Zamowienie:\n" + lancuch.toString();
    }

    void usunPozycje(int indeks) {
        this.pozycje.remove(indeks);
    }

    void edytujPozycje(int indeks) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj nową nazwę i wciśnij \"Enter\"");
        String nowaNazwa = scanner.next();
        this.pozycje.get(indeks - 1).nazwaTowaru = nowaNazwa;

        System.out.println("Podaj nową cenę i wciśnij \"Enter\"");
        double nowaCena = 0;
        nowaCena = scanner.nextDouble();
        this.pozycje.get(indeks - 1).cena = nowaCena;

        System.out.println("Podaj nową liczbę sztuk i wciśnij \"Enter\"");
        int nowaLiczbaSztuk = scanner.nextInt();
        this.pozycje.get(indeks - 1).ileSztuk = nowaLiczbaSztuk;

    }

    public static void main(String[] args) throws IOException {
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
        z.edytujPozycje(2);
        System.out.println(z);
        z.usunPozycje(2);
        System.out.println(z);
    }
}