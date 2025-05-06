
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
        return couleurGroupe; 
    }

    // 
    @Override
    public int calculerLoyer() {
        if (aHotel) return loyers[5];
        return loyers[nbMaisons];
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
            joueur.retirerArgent(prixMaison);
            nbMaisons++;
        }
    }
}