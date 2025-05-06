

public class Jouer {
    public static void main(String[] args) {
        // Initialisation
        IJeu jeu = new JeuMonopoly();
        Scanner scanner = new Scanner(System.in);
        
        // Configuration des joueurs
        System.out.println("=== MONOPOLY ===");
        System.out.print("Nombre de joueurs (2-8) : ");
        int nbJoueurs = scanner.nextInt();
        scanner.nextLine(); // Nettoyer le buffer
        
        for (int i = 0; i < nbJoueurs; i++) {
            System.out.print("Nom du joueur " + (i+1) + " : ");
            String nom = scanner.nextLine();
            jeu.ajouterJoueur(nom, i+1); // iconeId = index+1
        }
        
        // Boucle principale
        System.out.println("\nDébut de la partie !");
        jeu.demarrerJeu();
        
        while (!jeu.jeuTermine()) {
            Joueur joueurActuel = jeu.getJoueurActuel();
            
            System.out.println("\n--- Tour de " + joueurActuel.getNom() + " ---");
            System.out.println("Position : " + joueurActuel.getPosition());
            System.out.println("Argent : " + joueurActuel.getArgent() + "€");
            
            // Menu simplifié
            System.out.println("\n1. Lancer les dés");
            System.out.println("2. Gérer mes propriétés");
            System.out.println("3. Passer mon tour");
            System.out.print("Choix : ");
            
            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    jeu.faireJouer(joueurActuel);
                    break;
                case 2:
                    // TODO: Implémenter la gestion des propriétés
                         case 2:
                        // Gestion simplifiée des propriétés
                        System.out.println("\n=== GESTION DES PROPRIÉTÉS ===");
                        List<Propriete> proprietes = joueurActuel.getProprietes();
                        
                        if (proprietes.isEmpty()) {
                            System.out.println("Vous ne possédez aucune propriété.");
                            break;
                        }
                        
                        // Affichage simplifié
                        System.out.println("Vos propriétés :");
                        for (int i = 0; i < proprietes.size(); i++) {
                            Propriete p = proprietes.get(i);
                            System.out.printf("%d. %s - Loyer: %d€ - Maisons: %d\n", 
                                i+1, p.getNom(), p.getLoyer(), p.getNbMaisons());
                        }
                        
                        // Menu simplifié
                        System.out.println("\n1. Construire une maison");
                        System.out.println("2. Vendre une propriété");
                        System.out.println("3. Retour");
                        System.out.print("Choix : ");
                        
                        int choixPropriete = scanner.nextInt();
                        
                        switch (choixPropriete) {
                            case 1: // Construction maison
                                System.out.print("Numéro de la propriété : ");
                                int propIndex = scanner.nextInt() - 1;
                                if (propIndex >= 0 && propIndex < proprietes.size()) {
                                    if (joueurActuel.getArgent() >= proprietes.get(propIndex).getPrixMaison()) {
                                        proprietes.get(propIndex).ajouterMaison();
                                        System.out.println("+1 maison ! Nouveau loyer : " + 
                                            proprietes.get(propIndex).getLoyer() + "€");
                                    } else {
                                        System.out.println("Fonds insuffisants.");
                                    }
                                }
                                break;
                                
                            case 2: // Vente
                                System.out.print("Numéro de la propriété à vendre : ");
                                propIndex = scanner.nextInt() - 1;
                                if (propIndex >= 0 && propIndex < proprietes.size()) {
                                    int prixVente = proprietes.get(propIndex).getPrixVente();
                                    joueurActuel.getProprietes().remove(propIndex);
                                    joueurActuel.recevoirArgent(prixVente);
                                    System.out.println("Vendu ! Vous recevez " + prixVente + "€");
                                }
                                break;
                        }
                        break;  
                    break;
                default:
                    System.out.println("Tour passé");
            }
            
            // Vérification fin de partie
            if (jeu.getJoueurs().stream().filter(j -> !j.estEnFaillite()).count() == 1) {
                break;
            }
        }
        
        // Fin de partie
         if (jeu.jeuTermine()) {
    Joueur vainqueur = null;
    for (Joueur j : jeu.getJoueurs()) {
        if (!j.estEnFaillite()) {
            vainqueur = j;
            break;
        }
            }
            System.out.println("\n=== " + vainqueur.getNom() + " remporte la partie ! ===");
        } else {
            System.out.println("\nErreur : La partie est terminée mais aucun vainqueur trouvé !");
        } 
        scanner.close();
    }
}