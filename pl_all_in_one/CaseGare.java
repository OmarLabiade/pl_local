
import java.util.List;

public class CaseGare extends CaseProprieteAchetable {
    // Règles officielles Monopoly
    private static final int LOYER_BASE = 25; // 25€ pour 1 gare, 50€ pour 2, etc.

    public CaseGare(String nom, int position, int prixAchat) {
        super(nom, position, prixAchat);
    }

    @Override
    public int calculerLoyer() {
        if (getProprietaire() == null) return 0; // Aucun propriétaire = loyer nul
        
        // Compte le nombre de gares possédées par le propriétaire
        List<CaseProprieteAchetable> proprietes = getProprietaire().getProprietes();
        int nbGares = 0;
        
        for (CaseProprieteAchetable p : proprietes) {
            if (p instanceof CaseGare) { // Vérifie si c'est une gare
                nbGares++;
            }
        }

        // Calcul du loyer selon les règles officielles
        return LOYER_BASE * nbGares;
    }

    @Override
    public void surCase(Joueur joueur) {
        System.out.println(joueur.getNom() + " atterit sur la gare " + getNom());
        super.surCase(joueur); // Logique parente (paiement du loyer si nécessaire)
    }
}