
import java.util.List;

public class CaseCompagnie extends CaseProprieteAchetable {
    private static final int MULTIPLICATEUR_1 = 4;  // Si 1 compagnie possédée
    private static final int MULTIPLICATEUR_2 = 10; // Si 2 compagnies possédées

    public CaseCompagnie(String nom, int position, int prixAchat) {
        super(nom, position, prixAchat);
    }

    // Méthode pour calculer le loyer BASÉ SUR LE LANCER DE DÉS
    public int calculerLoyer(int sommeDes) {
        if (getProprietaire() == null) return 0; // Aucun propriétaire = loyer nul
    
        List<CaseProprieteAchetable> proprietes = getProprietaire().getProprietes();
        int nbCompagnies = 0;
    
        // Boucle explicite pour compter les compagnies
        for (CaseProprieteAchetable p : proprietes) {
            if (p instanceof CaseCompagnie) { // On vérifie si c'est une compagnie
                nbCompagnies++;
            }
        }
    
        // Règles officielles du Monopoly :
        return sommeDes * (nbCompagnies == 1 ? 4 : 10);
    }

    @Override
    public void surCase(Joueur joueur) {
        System.out.println(joueur.getNom() + " atterit sur la compagnie " + getNom());
        if (getProprietaire() != null && getProprietaire() != joueur && !estHypothequee()) {
            // Récupère le lancer de dés depuis la logique du jeu (à adapter)
            int sommeDes = joueur.getDernierLancerDeDes(); 
            int loyer = calculerLoyer(sommeDes);
            
            joueur.retirerArgent(loyer);
            getProprietaire().ajouterArgent(loyer);
        }
    }

    // Implémentation obligatoire 
    @Override
    public int calculerLoyer() {
        throw new UnsupportedOperationException("Utilisez calculerLoyer(int sommeDes)");
    }
}
