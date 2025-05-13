
import java.util.List;

public class CaseRue extends CaseProprieteAchetable {
    private final String couleurGroupe;
    private final int prixMaison;
    private final int prixHotel;
    private final int[] loyers; // [0-4] = maisons, [5] = hôtel
    private int nbMaisons;
    private boolean aHotel;

    // === CONSTRUCTEUR ===
    public CaseRue(String nom, int position, int prixAchat, 
                  String couleurGroupe, int prixMaison, int prixHotel, 
                  int[] loyers) {
        super(nom, position, prixAchat);
        this.couleurGroupe = couleurGroupe;
        this.prixMaison = prixMaison;
        this.prixHotel = prixHotel;
        this.loyers = loyers.clone(); // Copie défensive
    }

    // === GETTERS  ===
    public Joueur getProprietaire() { 
        return super.getProprietaire(); // Hérité de CaseProprieteAchetable 
    }

    public String getCouleurGroupe() { 
        return this.couleurGroupe; 
    }

    public int getPrixMaison() { 
        return this.prixMaison; 
    }

    public int getPrixHotel() { 
        return this.prixHotel; 
    }

    public int getNbMaison() { 
        return this.nbMaisons; 
    }
    // 
    @Override
    public int calculerLoyer() {
        if (aHotel) return this.loyers[5];
        return this.loyers[this.nbMaisons];
    }

    public boolean aMonopole(List<CaseRue> groupeCouleur) {
        // Vérifie si le propriétaire possède TOUTES les rues du groupe
        return groupeCouleur.stream()
            .allMatch(rue -> rue.getProprietaire() == this.getProprietaire());
    }

    public void construireMaison(Joueur joueur) {
        if (joueur != getProprietaire()) {
            throw new IllegalStateException("Vous n'êtes pas le propriétaire !");
        }
        if (nbMaisons < 4) {
            joueur.retirerArgent(this.prixMaison);
            this.nbMaisons++;
        }
    }

    public void detruireMaison(Joueur joueur) {
        if (joueur != getProprietaire()) {
            throw new IllegalStateException("Vous n'êtes pas le propriétaire !");
        }
        if (nbMaisons > 0) {
            this.nbMaisons--;
        }
    }
}