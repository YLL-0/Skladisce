import java.util.ArrayList;
import java.util.List;

class Element {

    int idElementa;
    String opisElementa;
    double tezaElementa;

    Element(int id, String opis, double teza) {
        this.idElementa = id;
        this.opisElementa = opis;
        this.tezaElementa = teza;
    }
}

class Polica {
    List<Element> tabelaElementov;
    int stevilkaPolice;
    int maksimalnaDovoljenaTeza;

    Polica(int maksimalnaTeza, int stevilkaPolice) {
        this.stevilkaPolice = stevilkaPolice;
        this.maksimalnaDovoljenaTeza = maksimalnaTeza;
        this.tabelaElementov = new ArrayList<>();
    }

    boolean dovoljProstora(Element element) {
        return maksimalnaDovoljenaTeza >= TezaElementov() + element.tezaElementa;
    }

    double TezaElementov() {
        double Teza = 0;
        for (Element elem : tabelaElementov) {
            Teza += elem.tezaElementa;
        }
        return Teza;
    }

    void dodajaElementa(Element element) {
        boolean idJeEnak = false;
        for (Element elem : tabelaElementov) {
            if (elem.idElementa == element.idElementa) {
                idJeEnak = true;
                break;
            }
        }
        if (dovoljProstora(element) && !idJeEnak) {
            tabelaElementov.add(element);
        } else {
            System.out.println("Na polici je premalo prostora, ali elementa imata enak ID");
        }
    }
}

class Skladisce {

        List<Polica> tabelaPolic;

        Skladisce(int steviloPolic, int maksimalnaTeza) {
            this.tabelaPolic = new ArrayList<>();
            for (int i = 1; i <= steviloPolic; i++) {
                this.tabelaPolic.add(new Polica(maksimalnaTeza, i));
            }
        }


    void izpis() {
        for (Polica polica : tabelaPolic) {
            double skupnaTeza = 0;
            System.out.println("Stevilka Police: " + polica.stevilkaPolice);
            System.out.println("Maksimalna Dovoljena Teza: " + polica.maksimalnaDovoljenaTeza);
            System.out.println(" ");
            for (Element element : polica.tabelaElementov) {
                System.out.println("Element ID: " + element.idElementa);
                System.out.println("Opis Elementa: " + element.opisElementa);
                System.out.println("Teza Elementa: " + element.tezaElementa);
                skupnaTeza += element.tezaElementa;
            }
            System.out.println("Skupna Teza Elementov na Polici: " + skupnaTeza);
            System.out.println("Preostala Dovoljena Teza na Polici: " + (polica.maksimalnaDovoljenaTeza - skupnaTeza));
        }
    }


        void odstrani(int idElementa) {
            if(tabelaPolic.isEmpty()){
                System.out.println("Na polici ni nobenega elementa");

            }else {
                for (Polica polica : tabelaPolic) {
                    for (Element elm : polica.tabelaElementov){
                        if (elm.idElementa == idElementa){
                            polica.tabelaElementov.remove(idElementa);
                            System.out.println("Element s ID-jem: " + idElementa + " je bil uspesno odstranjen");
                        }else
                            System.out.println("Element s ID-jem " + idElementa + " ni bil mogoče najti.");

                        }

                    }
            }
        }

    void sprazniPolico(int stevilkaPolice) {
        // Iskanje police s podano številko
        Polica ciljnaPolica = null;
        for (Polica polica : tabelaPolic) {
            if (polica.stevilkaPolice == stevilkaPolice) {
                ciljnaPolica = polica;
                break;
            }
        }

        if (ciljnaPolica == null) {
            System.out.println("Polica " + stevilkaPolice + " ne obstaja.");
            return;//prekine koo
        }

        // List vseh elementov, ki jih želimo premestiti oz. prerazporediti
        List<Element> elementiZaPremestitev = new ArrayList<>(ciljnaPolica.tabelaElementov);

        // Zanka čez vse elemente za premestitev
        for (Element element : elementiZaPremestitev) {
            boolean jePremestit = false; // zastavica za označevanje, ali je bil element uspešno premaknjen

            // Zanka čez vse police v skladišču
            for (Polica polica : tabelaPolic) {
                // Preverimo če na polici ni elementa z enakim id-jem in če je na polici dovolj prostora za element
                if (polica.stevilkaPolice != stevilkaPolice && polica.dovoljProstora(element)) {
                    // Dodamo element na polico
                    polica.tabelaElementov.add(element);
                    // Nastavimo zastavico na true
                    jePremestit = true;
                    break;
                }
            }

            // Če je bil element uspešno premaknjen, ga odstranimo s prvotne police
            if (jePremestit) {
                ciljnaPolica.tabelaElementov.remove(element);
            } else {
                // Če elementa ni bilo mogoče premakniti, obvestimo uporabnika
                System.out.println("Elementa z ID-jem: " + element.idElementa + " ni bilo mogoče premakniti.");
                return;//konca funkcio
            }
        }
    }

    public static void main(String[] args) {
        Skladisce skl = new Skladisce(3, 10); // Create warehouse with 3 shelves each having maximum weight of 10

        Element e1 = new Element(1, "Element 1", 2.5);
        Element e2 = new Element(2, "Element 2", 3.5);
        Element e3 = new Element(3, "Element 3", 1.5);
        Element e4 = new Element(4, "Element 4", 4.5);
        Element e5 = new Element(5, "Element 5", 5.5);
        Element e6 = new Element(6, "Element 6", 6.5);

        skl.tabelaPolic.get(0).dodajaElementa(e1); // Add element 1 to shelf 1
        skl.tabelaPolic.get(0).dodajaElementa(e2); // Add element 2 to shelf 1
        skl.tabelaPolic.get(1).dodajaElementa(e3); // Add element 3 to shelf 2
        skl.tabelaPolic.get(1).dodajaElementa(e4); // Add element 4 to shelf 2
        skl.tabelaPolic.get(2).dodajaElementa(e5); // Add element 5 to shelf 3

        skl.izpis(); // Print the state of the warehouse

        skl.odstrani(1); // Remove element 1 from warehouse
        skl.izpis(); // Check if element 1 was removed

        skl.sprazniPolico(2); // Clear the second shelf
        skl.izpis(); // Check if the second shelf was cleared

        skl.tabelaPolic.get(0).dodajaElementa(e6); // Add element 6 to shelf 1
        skl.izpis(); // Check if element 6 was added to the first shelf
    }

}
