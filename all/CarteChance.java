

public class CarteChance extends Carte {
    public CarteChance(String nom, String description, boolean instant) {
        super(nom, description, instant);
    }

    @Override
    public void appliquerEffet(Joueur joueur) {
        // Implémentation des effets Chance
        switch (getNom()) {
            case "Avancez au départ":
                joueur.setPosition(0);
                joueur.recevoir(200);
                break;
            case "Allez en prison":
                joueur.allerEnPrison();
                break;
            // Ajoutez d'autres cas selon les besoins
            default:
                System.out.println("Effet non implémenté: " + getDescription());
        }
    }
}