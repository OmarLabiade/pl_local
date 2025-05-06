
public class CaseAllezEnPrison extends Case {
    private final int positionPrison;

    public CaseAllezEnPrison(int position, int positionPrison) {
        super("Allez en Prison", position);
        this.positionPrison = positionPrison;
    }

    @Override
    public void surCase(Joueur joueur) { 
        System.out.println(joueur.getNom() + " est envoyé en prison !");
        joueur.setPosition(positionPrison);
    }
}