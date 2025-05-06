

public class CaseParcGratuit extends Case {
    
    // Règles officielles : pas de cagnotte
    // private int cagnotte;
    
    public CaseParcGratuit(int position) {
        super("Parc Gratuit", position);
        // this.cagnotte = 0;
    }
    
    // Méthode désactivée pour règles officielles
    // public void ajouterCagnotte(int montant) {
    //     this.cagnotte += montant;
    // }
    
    @Override
    public void surCase(Joueur joueur) {
        // Version officielle - aucune action
        System.out.println(joueur.getNom() + " se repose au Parc Gratuit.");
        
        // Règles maison (commentées)
        // System.out.println(joueur.getNom() + " est sur le Parc Gratuit et récupère " + cagnotte + "€ !");
        // joueur.ajouterArgent(cagnotte);
        // cagnotte = 0;
    }
}