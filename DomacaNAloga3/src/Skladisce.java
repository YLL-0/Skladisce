import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

class Element {

    int idElementa;
    String opisElementa;
    double tezaElementa;

    Element(int id, String opis, int teza) {
        this.idElementa = idElementa;
        this.opisElementa = opisElementa;
        this.tezaElementa = tezaElementa;
    }

}

class Polica {
    List<Element> tabelaElementov;

    int stevilkaPolice;
    double maksimalnaDovoljenaTeza;


    Polica(double teza, int id) {
        this.maksimalnaDovoljenaTeza = teza;
        this.stevilkaPolice = id;
        tabelaElementov = new ArrayList<>();
    }


     void dodajaElementa(Element element) {
      tabelaElementov.add(element);

    }
}

class Skladisce {
    List<Polica> tabelaPolic;

    Skladisce(int steviloPolic, int maksimalnaTeza) {
        this.tabelaPolic = new ArrayList<>();
        for (int i = 1; i <= steviloPolic; i++) {
            Polica polica = new Polica(maksimalnaTeza, i);
            this.tabelaPolic.add(polica);
        }
    }

    void izpis() {
        for (Polica polica : tabelaPolic) {
            System.out.println("Stevilka Police: " + polica.stevilkaPolice);
            System.out.println("Maksimalna Dovoljena Teza: " + polica.maksimalnaDovoljenaTeza);
            System.out.println(" ");

            for (Element element : polica.tabelaElementov) {
                System.out.println("Element ID: " + element.idElementa);
                System.out.println("Opis Elementa: " + element.opisElementa);
                System.out.println("Teza Elementa: " + element.tezaElementa);
            }
        }
    }




    public static void main(String[] args) {
        Skladisce skladisce = new Skladisce(12,1234);
        skladisce.izpis();


    }
}



