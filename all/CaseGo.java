

public class CaseGo extends Case {
    private final int bonus; // Montant reçu quand on passe la case

    public CaseGo(int position, int bonus) {
        super("Go", position); // Nom de la case
        this.bonus = bonus;
    }
    
    @Override
    public void surCase(Joueur joueur) {
        // Méthode vide car le joueur ne reçoit l'argent que quand il **passe** la case
        // (Géré par Plateau lors du déplacement)
    }

    // Getter pour le montant
    public int getbonus() {
        return bonus;
    }
}