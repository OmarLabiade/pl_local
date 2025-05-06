

public abstract class Carte {
    private final String nom;
    private final String description;
    private final boolean instant;

    public Carte(String nom, String description, boolean instant) {
        this.nom = nom;
        this.description = description;
        this.instant = instant;
    }

    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public boolean isInstant() { return instant; }

    public abstract void appliquerEffet(Joueur joueur);
}