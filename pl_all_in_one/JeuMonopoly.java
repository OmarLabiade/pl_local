

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant l'arbitre du jeu de Monopoly.
 * Elle est responsable de l'initialisation et de la gestion globale d'une partie.
 * Elle contient les joueurs, le plateau et gère les règles et les interactions entre les joueurs.
 */
public class JeuMonopoly {
    /** Le plateau du jeu, contenant les cases */
    private Plateau plateau;
    /** La liste des joueurs participant à la partie */
    private List<Joueur> joueurs;
    /** L'index du joueur actuel dans la liste des joueurs */
    private int joueurActuelIndex;

    /**
     * Constructeur de la classe Arbitre.
     * Initialise le plateau et la liste des joueurs.
     */
    public JeuMonopoly() {
        this.plateau = new Plateau();
        this.joueurs = new ArrayList<>();
        this.joueurActuelIndex = 0;
    }

    /**
     * Démarre la partie de Monopoly.
     * Cette méthode contient la boucle principale du jeu.
     */
    public void demarrerJeu() {
        while (!jeuTermine()) {
            Joueur joueurActuel = getJoueurActuel();
            
            System.out.println("\n--- Tour de " + joueurActuel.getNom() + " ---");
            System.out.println("Position : " + joueurActuel.getPosition());
            System.out.println("Argent : " + joueurActuel.getArgent() + "€");
            
            faireJouer(joueurActuel);
            
            // Passer au joueur suivant
            joueurSuivant();
        }
        
        System.out.println("\nLe jeu est terminé !");
    }

    /**
     * Ajoute un joueur au jeu.
     * @param nom Le nom du joueur
     * @param iconeId Une icône ou identifiant visuel associé au joueur
     */
    public void ajouterJoueur(String nom, int iconeId) {
        Joueur joueur = new Joueur(nom, iconeId);
        joueurs.add(joueur);
    }

        /**
     * Vérifie si la partie est terminée.
     * Affiche le nom du gagnant si un seul joueur reste actif.
     * @return true si un seul joueur ou aucun joueur reste actif, sinon false
     */
    public boolean jeuTermine() {
        int joueursActifs = 0;
        Joueur gagnant = null;

        for (Joueur joueur : joueurs) {
            if (joueur.estActif()) {
                joueursActifs++;
                gagnant = joueur;
            }
        }

        if (joueursActifs == 1) {
            System.out.println(" Le gagnant est : " + gagnant.getNom() + " !");
        } else if (joueursActifs == 0) {
            System.out.println("Tous les joueurs ont été éliminés. Pas de gagnant !");
        }

        return joueursActifs <= 1;
    }


    /**
     * Permet à un joueur de jouer son tour
     * @param joueur Le joueur qui doit jouer son tour
     */
    public void faireJouer(Joueur joueur) { 
        if (joueur.estEnPrison()) {
            System.out.println(joueur.getNom() + " est en prison.");
            joueur.reduireToursEnPrison();
            
            // Gérer le cas où le joueur sort de prison ce tour
            if (!joueur.estEnPrison()) {
                System.out.println(joueur.getNom() + " sort de prison !");
                // Continuer le tour normalement
                jouerTourNormal(joueur);
            } else {
                System.out.println(joueur.getNom() + " reste en prison.");
                // Possibilité d'ajouter ici des options pour sortir de prison (payer, utiliser une carte, etc.)
            }
        } else {
            // Joueur libre, joue normalement
            jouerTourNormal(joueur);
        }
    }
    
    /**
     * Gère un tour normal (lancer de dés, déplacement et action de la case)
     * @param joueur Le joueur qui joue son tour
     */
    private void jouerTourNormal(Joueur joueur) {
        int des = lancerDes();
        System.out.println(joueur.getNom() + " a lancé un " + des);
        jouerUnite(joueur, des);
        
        Cases caseActuelle = plateau.getCase(joueur.getPosition());
        caseActuelle.surCase(joueur);
    }
    
    /**
     * Simule le lancer de dés
     * @return La somme des dés
     */
    public int lancerDes() {
        // Simulation d'un lancer de deux dés (valeurs entre 1 et 6)
        int de1 = (int) (Math.random() * 6) + 1;
        int de2 = (int) (Math.random() * 6) + 1;
        return de1 + de2;
    }
    
    /**
     * Déplace le joueur du nombre de cases indiqué par les dés
     * @param joueur Le joueur à déplacer
     * @param valeurDes La valeur des dés
     */
    private void jouerUnite(Joueur joueur, int valeurDes) {
        int nouvellePosition = (joueur.getPosition() + valeurDes) % plateau.getNombreCases();
        joueur.setPosition(nouvellePosition);
        System.out.println(joueur.getNom() + " se déplace à la case " + nouvellePosition);
    }

    /**
     * Retourne la liste des joueurs du jeu.
     * @return la liste des joueurs
     */
    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * Retourne le plateau de jeu.
     * @return le plateau
     */
    public Plateau getPlateau() {
        return plateau;
    }
    
    /**
     * Retourne le joueur dont c'est actuellement le tour.
     * @return le joueur actuel
     */
    public Joueur getJoueurActuel() {
        if (joueurs.isEmpty()) {
            return null;
        }
        return joueurs.get(joueurActuelIndex);
    }
    
    /**
     * Passe au joueur suivant.
     */
    public void joueurSuivant() {
        if (!joueurs.isEmpty()) {
            joueurActuelIndex = (joueurActuelIndex + 1) % joueurs.size();
        }
    }
}