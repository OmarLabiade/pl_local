

public class CarteCommunaute extends Carte {
    public CarteCommunaute(String nom, String description, boolean instant) {
        super(nom, description, instant);
    }

    @Override
    public void appliquerEffet(Joueur joueur) {
        // Implémentation des effets Caisse de Communauté
        switch (getNom()) {
            case "Erreur de la banque":
                joueur.recevoir(200);
                break;
            case "Payez les frais de scolarité":
                joueur.payer(100);
                break;
            // Ajoutez d'autres cas selon les besoins
            default:
                System.out.println("Effet non implémenté: " + getDescription());
        }
    }
}