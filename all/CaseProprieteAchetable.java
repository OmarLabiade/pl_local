

public abstract class CaseProprieteAchetable extends Case { // Suppression de l'interface
    protected final int prixAchat; 
    protected Joueur proprietaire;
    protected boolean estHypothequee; 

    public CaseProprieteAchetable(String nom, int position, int prixAchat) {
        super(nom, position);
        if (prixAchat <= 0) throw new IllegalArgumentException("Prix invalide");
        this.prixAchat = prixAchat;
        this.proprietaire = null;
        this.estHypothequee = false;
    }

    // === Getters  ===
    public int getPrixAchat() { return prixAchat; }
    public Joueur getProprietaire() { return proprietaire; }
    public boolean estHypothequee() { return estHypothequee; }

    // === Setters contrôlés ===
    public void setProprietaire(Joueur proprietaire) {
        if (this.proprietaire != null) {
            throw new IllegalStateException("Propriété déjà possédée");
        }
        this.proprietaire = proprietaire;
    }

    // === Méthodes d'hypothèque ===
    public int hypothequer() {
        estHypothequee = true;
        return prixAchat / 2; // 50% du prix
    }

    public int leverHypotheque() {
        estHypothequee = false;
        return (int)(prixAchat * 0.55); // 50% + 10% d'intérêt
    }

    // === Logique d'atterrissage ===
    @Override
    public void surCase(Joueur joueur) {
        if (proprietaire == null) {
            System.out.println(joueur.getNom() + " peut acheter " + getNom() + " pour " + prixAchat + "€");
        } else if (proprietaire != joueur && !estHypothequee) {// Sans paramètre
            int loyer = calculerLoyer();
            joueur.retirerArgent(loyer);
            proprietaire.ajouterArgent(loyer);
        }
    }

    public abstract int calculerLoyer(); 
}