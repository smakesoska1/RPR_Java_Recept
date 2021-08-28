package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Kuhar {
    private HashMap<String, Recept> recepti=new HashMap<>();

    public void dodajRecept(Recept recept) {
        recepti.put(recept.getNazivJela(),recept);
    }


    public int brojRecepata() {
        return recepti.size();
    }

    public Recept dajRecept(String naziv) {
        return recepti.get(naziv);
    }

    public ArrayList<Recept> receptiSaSastojkom(Sastojak sastojak) {
        return recepti.values().stream().filter(recept -> recept.getSastojci().stream().anyMatch(sastojak1 -> sastojak1.getNaziv().equals(sastojak.getNaziv()))).collect(Collectors.toCollection(ArrayList::new));
    }


    public Set<Sastojak> sviSastojci() {
        Set<Sastojak> novisas=new TreeSet<>();
        for(Recept r:recepti.values()){
            for(Sastojak s:r.getSastojci()){
                novisas.add(s);
            }
        }
        return novisas;
    }
    public ArrayList<Recept> filtriraj(Predicate<Recept> uslov){
        return recepti.values().stream().filter(uslov).collect(Collectors.toCollection(ArrayList::new));
    }
}

