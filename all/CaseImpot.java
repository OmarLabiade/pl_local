

public class CaseImpot extends Case {
    
    private final int montant;
    //monopoly regles maison pas encore implemente => pas d'argent lors du atterisage sur parc gratuit 
    // private CaseParcGratuit parcGratuit; 
    
    /* 
    public CaseImpot(String nom, int position, int montant, CaseParcGratuit parcGratuit) {
        super(nom, position);
        this.montant = montant;
        this.parcGratuit = parcGratuit;
    }

    @Override
    public void surCase(Joueur joueur) {
        System.out.println(joueur.getNom() + " doit payer " + montant + "€ d'impôts !");
        joueur.retirerArgent(montant);
        
        // Si l'option est activée, l'argent va dans la cagnotte du Parc Gratuit
        if (parcGratuit != null) {
            parcGratuit.ajouterCagnotte(montant);
        }
    }
    */

    // Pas de référence à CaseParcGratuit

    public CaseImpot(String nom, int position, int montant) {
        super(nom, position);
        if (montant <= 0) throw new IllegalArgumentException("Montant invalide");
        this.montant = montant;
    }

    public int getMontantImpot() {
        return montant;
    }
    @Override
    public void surCase(Joueur joueur) {
        System.out.println(joueur.getNom() + " paie " + montant + "€ à la banque.");
        joueur.retirerArgent(montant); // L'argent disparaît (banque virtuelle)
    }
}