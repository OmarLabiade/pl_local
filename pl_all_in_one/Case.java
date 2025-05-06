
public abstract class Case {
    private String nom;
    private int position;

    public Case(String nom, int position) {
        this.nom = nom;
        this.position = position;
    }

    public String getNom() {
        return this.nom;
    }

    public int getPosition() {
        return this.position;
    }

    /**
     * Méthode abstraite à implémenter : action à effectuer quand un joueur atterrit sur cette case
     */
    public abstract void surCase(Joueur joueur);
}
