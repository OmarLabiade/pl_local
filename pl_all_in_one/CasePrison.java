

public class CasePrison extends Case {
    
    public CasePrison(int position) {
        super("Prison", position);
    }

    @Override
    public void surCase(Joueur joueur) {
        // Ne rien faire si le joueur visite simplement la prison
        // Seulement logique de "rester en prison" si déjà emprisonné
    }
}