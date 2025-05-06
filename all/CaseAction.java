
import java.util.Stack;

public abstract class CaseAction extends Case {
    protected Stack<Carte> paquet;

    public CaseAction(String nom, int position, Stack<Carte> paquet) {
        super(nom, position);
        this.paquet = paquet;
    }

    @Override
    public void surCase(Joueur joueur) {
        if (!paquet.isEmpty()) {
            Carte carte = paquet.pop();
            System.out.println(joueur.getNom() + " tire : " + carte.description);
            carte.appliquerEffet();
        }
    }

    public void melangerPaquet() {
        java.util.Collections.shuffle(paquet);
    }
}