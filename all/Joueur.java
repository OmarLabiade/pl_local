import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Joueur {

    public enum EtatJoueur {
        ACTIF, EN_PRISON, EN_FAILLITE
    }

    //private List<CaseRue> proprietesAchetable;
    private String nom;
    private int position;
    private int solde;
    private List<CaseProprieteAchetable> proprietes;
    private int toursEnPrison;
    private int cartesSortiePrison;
    private EtatJoueur etat;

    public Joueur(String nom, int soldeInitial) {
        this.nom = nom;
        this.position = 0;
        this.solde = soldeInitial;
        this.proprietes = new ArrayList<>();
        this.toursEnPrison = 0;
        this.cartesSortiePrison = 0;
        this.etat = EtatJoueur.ACTIF;
    }

    /**
     * Extrait toutes les propriétés de type CaseRue depuis une liste de propriétés achetables.
     * 
     * @param proprietes Liste de propriétés achetables (CasePropriete)
     * @return Liste de propriétés de type CaseRue uniquement
     */
    public static List<CaseRue> extraireRuesDepuisProprietes(List<CaseProprieteAchetable> proprietes) {
        return proprietes.stream()
            .filter(prop -> prop instanceof CaseRue)
            .map(prop -> (CaseRue) prop)
            .collect(Collectors.toList());
    }
    
    public void deplacer(int nombreCases) {
        int compteur = 0;
        while (compteur < nombreCases) {
            if (this.position < 39) {
                this.position++;
            }else{
                this.position = 0;
            }
            compteur++;
        }
    }

    public void ajouterArgent(int montant) {
        this.solde += montant;
    }
    //reverifier comment un joueur peut etre en faillite
    public void retirerArgent(int montant) {
        this.solde -= montant;
        if (this.solde < 0 && proprietes.isEmpty()) {
            System.out.println(this.nom + " est en faillite !");
            this.etat = EtatJoueur.EN_FAILLITE;
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
        this.etat = EtatJoueur.EN_PRISON;
        this.toursEnPrison = 0;
        this.position = 10; // Case prison
    }

    public boolean possedePropriete(CaseProprieteAchetable propriete) {
        return this.proprietes.contains(propriete);
    }

    public void retirerPropriete(CaseProprieteAchetable propriete) {
        Boolean test =this.proprietes.remove(propriete); //le joueur pourra n'avoir pas la proprieté
        if (test) {
            propriete.setProprietaire(null);
        }else{
           System.out.println(this.getNom() + " n'est pas propriétaire de " + propriete.getNom() + " , donc impossible de la retiré "); 
        }
    }

    public void vendrePropriete(CaseProprieteAchetable propriete) {
        if (this.possedePropriete(propriete)) {
            int valeurRevente = propriete.getPrixAchat() / 2;
            this.ajouterArgent(valeurRevente);
            this.retirerPropriete(propriete);
            System.out.println(this.getNom() + " a vendu " + propriete.getNom() + " pour " + valeurRevente + "$.");
        }
    }
    
    public boolean peutConstruireMaisonSur(CaseRue terrain) {
        if (!possedePropriete(terrain)){
            return false;
        } else{
            List<CaseRue> ruesPossedees = Joueur.extraireRuesDepuisProprietes(this.proprietes);
            return terrain.aMonopole(ruesPossedees);
        }   
    }


    // Gère l'achat d'une maison sur un terrain donné par un joueur// a modifier caractère couleur
    public void acheterMaison(CaseRue terrain) {
        if (peutConstruireMaisonSur(terrain)) {
            int prixMaison = terrain.getPrixMaison();
            if (this.solde >= prixMaison) {
                this.retirerArgent(prixMaison);
                terrain.construireMaison(this);
                System.out.println(this.getNom() + " a construit une maison sur " + terrain.getNom());
            }
        }
    }

    // Gère la vente d'une maison sur un terrain donné par un joueur
    public void vendreMaison(CaseRue terrain) {
        if (this.possedePropriete(terrain) && terrain.getNbMaison() > 0){
            int valeurRevente = terrain.getPrixMaison() / 2;
            terrain.detruireMaison(this);
            this.ajouterArgent(valeurRevente);
            System.out.println(this.getNom() + " a vendu une maison sur " + terrain.getNom());
        }
    }

   /* // Envoie un joueur en prison
    public void allerEnPrison(Joueur joueur) {
        joueur.setEnPrison(true);
        joueur.setToursRestantsEnPrison(3);
        joueur.setPosition(jeu.getPlateau().getIndexCasePrison());
        System.out.println(joueur.getNom() + " est envoyé en prison !");
    }*/


    // Suggestion : à déplacer vers la classe Jeu ou Banque
    /*
    public void payerLoyer(Joueur de, Joueur a, int montant) {
        de.retirerArgent(montant);
        a.ajouterArgent(montant);
    }
    */

    // Getters / Setters
    public String getNom() { 
        return nom; 
    }

    public int getPosition() { 
        return position; 
    }

    public void setPosition(int position) { 
        this.position = position; 
    }

    public int getSolde() { 
        return solde; 
    }

    public boolean estEnPrison() { 
        return etat == EtatJoueur.EN_PRISON; 
    }

    public boolean estActif() { 
        return etat == EtatJoueur.ACTIF; 
    }

    public EtatJoueur getEtat() { 
        return etat; 
    }

    public List<CaseProprieteAchetable> getProprietes() { 
        return proprietes; 
    }

    public List<CaseProprieteAchetable> getProprietesAchetables() {
        return this.proprietes; // Retourne toutes les propriétés possédées
    }

    public int getToursEnPrison() { 
        return toursEnPrison; 
    }

    public int getCartesSortiePrison() { 
        return cartesSortiePrison; 
    }

    public void ajouterCarteSortiePrison() { 
        this.cartesSortiePrison++; 
    }

    public void utiliserCarteSortiePrison() {
        if (this.cartesSortiePrison > 0) {
            this.cartesSortiePrison--;
            this.etat = EtatJoueur.ACTIF;
            System.out.println(this.nom + " utilise une carte 'Sortie de prison'.");
        }
    }

}
