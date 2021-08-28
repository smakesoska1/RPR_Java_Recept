package ba.unsa.etf.rpr;


import java.util.ArrayList;
import java.util.stream.Collectors;

public class Recept {
    private String nazivJela;
    private ArrayList<Sastojak> sastojci=new ArrayList<>();
    private int podatak;
    private VrstaPripreme vrstaPripreme=VrstaPripreme.KUHANJE;

    public Recept(String nazivJela){
        this.nazivJela=nazivJela;
    }

    public String getNazivJela() {
        return nazivJela;
    }

    public void setNazivJela(String nazivJela) {
        this.nazivJela = nazivJela;
    }

    public ArrayList<Sastojak> getSastojci() {
        return sastojci;
    }

    public int getPodatak() {
        return podatak;
    }

    public void setPodatak(int podatak) {
        this.podatak = podatak;
    }

    public VrstaPripreme getVrstaPripreme() {
        return vrstaPripreme;
    }

    public void setVrstaPripreme(VrstaPripreme vrstaPripreme) {
        this.vrstaPripreme = vrstaPripreme;
    }

    public void dodajSastojak(Sastojak sastojak) {
        sastojci.stream().filter(sastojak1 -> sastojak1.getNaziv().equals(sastojak.getNaziv()) && sastojak1.getClass().equals(sastojak.getClass()))
                .findFirst().ifPresentOrElse(sastojak1 -> sastojak1.setKolicina(sastojak1.getKolicina()+sastojak.getKolicina()),()->sastojci.add(sastojak));
    }
    public void izbaciSastojak(Sastojak s) {
        sastojci.stream().filter(sast -> sast.getNaziv().equals(s.getNaziv()) && sast.getClass().equals(s.getClass())).
                findFirst().ifPresentOrElse(sast -> sastojci.remove(sast), () -> {
            throw new NoSuchSastojakException("Nepoznat sastojak " + s.getNaziv());
        });
    }

    @Override
    public String toString() { //za ispis sa streamom
        return "Recept za " + nazivJela + "\n" + sastojci.stream().map(Object::toString).collect(Collectors.joining("\n")) +
                "\n" + (vrstaPripreme == VrstaPripreme.PECENJE ? "Peći na "+podatak+" stepeni" :
                vrstaPripreme == VrstaPripreme.KUHANJE ? "Kuhati "+podatak+" minuta" : "Pržiti " + podatak + " minuta");
    }
}
