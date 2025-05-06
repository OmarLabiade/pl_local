import java.util.List;
import java.util.ArrayList;

public class Joueur {

    private String nom;
    private int position;
    private int solde;
    private List<CaseProprieteAchetable> proprietes;
    private boolean enPrison;
    private int toursEnPrison;
    private int cartesSortiePrison;
    private boolean estActif;

    public Joueur(String nom, int soldeInitial) {
        this.nom = nom;
        this.position = 0;
        this.solde = soldeInitial;
        this.proprietes = new ArrayList<CaseProprieteAchetable>();
        this.enPrison = false;
        this.toursEnPrison = 0;
        this.cartesSortiePrison = 0;
        this.estActif = true;
    }

    public void deplacer(int nombreCases, int taillePlateau) {
        this.position = (this.position + nombreCases) % taillePlateau;
    }

    public void ajouterArgent(int montant) {
        this.solde += montant;
    }

    public void retirerArgent(int montant) {
        this.solde -= montant;
        if (this.solde < 0 && proprietes.isEmpty()) {
            this.estActif = false;
        }
    }

    public void acheterPropriete(CaseProprieteAchetable propriete) {
        if (this.solde >= propriete.getPrixAchat()) {
            retirerArgent(propriete.getPrixAchat());
            proprietes.add(propriete);
            propriete.setProprietaire(this);
        }
    }

    public void allerEnPrison() {
        this.enPrison = true;
        this.toursEnPrison = 0;
        this.position = 10; // Par défaut, la case prison
    }

    public String getNom() { 
        return this.nom; 
    }

    public int getPosition() { 
        return this.position; 
    }

    public int getSolde() { 
        return this.solde; 
    }

    public boolean estEnPrison() { 
        return this.enPrison; 
    }

    public boolean estActif() { 
        return this.estActif; 
    }

    public List<CaseProprieteAchetable> getProprietes() { 
        return this.proprietes; 
    }

    public int getToursEnPrison() { 
        return this.toursEnPrison; 
    }

    public int getCartesSortiePrison() { 
        return this.cartesSortiePrison; 
    }
}