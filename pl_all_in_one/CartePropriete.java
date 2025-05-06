

public class CartePropriete extends Carte {
    private final int prixAchat;
    private final int loyer;
    private Joueur proprietaire;

    public CartePropriete(String nom, int prixAchat, int loyer) {
        super(nom, "Propriété: " + nom, false);
        this.prixAchat = prixAchat;
        this.loyer = loyer;
        this.proprietaire = null;
    }

    @Override
    public void appliquerEffet(Joueur joueur) {
        if (proprietaire == null) {
            if (joueur.getArgent() >= prixAchat) {
                joueur.payer(prixAchat);
                proprietaire = joueur;
            }
        } else if (!proprietaire.equals(joueur)) {
            joueur.payer(loyer);
            proprietaire.recevoir(loyer);
        }
    }

    // Getters
    public Joueur getProprietaire() { return proprietaire; }
    public int getPrixAchat() { return prixAchat; }
    public int getLoyer() { return loyer; }
}