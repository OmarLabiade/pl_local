import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Plateau {
    private final List<Case> cases = new ArrayList<>(40);
    
    public Plateau() {
        initialiserCases();
    }

    private void initialiserCases() {
        // 0 - Case Départ
        cases.add(new CaseGo(0, 200));

        // 1-39: Follow official Monopoly order
        cases.add(new CaseRue("Boulevard de Belleville", 1, 60, "Marron", 50, 50, new int[]{2, 10, 30, 90, 160, 250}));
        cases.add(new CaseCommunaute(2, initPaquetCommunaute()));
        cases.add(new CaseRue("Rue Lecourbe", 3, 60, "Marron", 50, 50, new int[]{4, 20, 60, 180, 320, 450}));
        cases.add(new CaseImpot("Impôt sur le revenu", 4, 200));
        cases.add(new CaseGare("Gare Montparnasse", 5, 200));
        cases.add(new CaseRue("Rue de Vaugirard", 6, 100, "Bleu clair", 50, 50, new int[]{6, 30, 90, 270, 400, 550}));
        cases.add(new CaseChance(7, initPaquetChance()));
        cases.add(new CaseRue("Rue de Courcelles", 8, 100, "Bleu clair", 50, 50, new int[]{6, 30, 90, 270, 400, 550}));
        cases.add(new CaseRue("Avenue de la République", 9, 120, "Bleu clair", 50, 50, new int[]{8, 40, 100, 300, 450, 600}));
        
        // 10 - Prison/Simple Visite
        cases.add(new CasePrison(10));
        
        // 11-20
        cases.add(new CaseRue("Boulevard de la Villette", 11, 140, "Rose", 100, 100, new int[]{10, 50, 150, 450, 625, 750}));
        cases.add(new CaseCompagnie("Compagnie d'Électricité", 12, 150));
        cases.add(new CaseRue("Avenue de Neuilly", 13, 140, "Rose", 100, 100, new int[]{10, 50, 150, 450, 625, 750}));
        cases.add(new CaseRue("Rue de Paradis", 14, 160, "Rose", 100, 100, new int[]{12, 60, 180, 500, 700, 900}));
        cases.add(new CaseGare("Gare de Lyon", 15, 200));
        cases.add(new CaseRue("Avenue Mozart", 16, 180, "Orange", 100, 100, new int[]{14, 70, 200, 550, 750, 950}));
        cases.add(new CaseCommunaute(17, initPaquetCommunaute()));
        cases.add(new CaseRue("Boulevard Saint-Michel", 18, 180, "Orange", 100, 100, new int[]{14, 70, 200, 550, 750, 950}));
        cases.add(new CaseRue("Place Pigalle", 19, 200, "Orange", 100, 100, new int[]{16, 80, 220, 600, 800, 1000}));
        
        // 20 - Parc Gratuit
        cases.add(new CaseParcGratuit(20));

        // 21-30
        cases.add(new CaseRue("Avenue Matignon", 21, 220, "Rouge", 150, 150, new int[]{18, 90, 250, 700, 875, 1050}));
        cases.add(new CaseChance(22, initPaquetChance()));
        cases.add(new CaseRue("Boulevard Malesherbes", 23, 220, "Rouge", 150, 150, new int[]{18, 90, 250, 700, 875, 1050}));
        cases.add(new CaseRue("Avenue Henri-Martin", 24, 240, "Rouge", 150, 150, new int[]{20, 100, 300, 750, 925, 1100}));
        cases.add(new CaseGare("Gare du Nord", 25, 200));
        cases.add(new CaseRue("Faubourg Saint-Honoré", 26, 260, "Jaune", 150, 150, new int[]{22, 110, 330, 800, 975, 1150}));
        cases.add(new CaseRue("Place de la Bourse", 27, 260, "Jaune", 150, 150, new int[]{22, 110, 330, 800, 975, 1150}));
        cases.add(new CaseCompagnie("Compagnie des Eaux", 28, 150));
        cases.add(new CaseRue("Rue La Fayette", 29, 280, "Jaune", 150, 150, new int[]{24, 120, 360, 850, 1025, 1200}));
        
        // 30 - Allez en Prison
        cases.add(new CaseAllezEnPrison(30, 10)); // Position 10 = prison

        // 31-39
        cases.add(new CaseRue("Avenue de Breteuil", 31, 300, "Vert", 200, 200, new int[]{26, 130, 390, 900, 1100, 1275}));
        cases.add(new CaseRue("Avenue Foch", 32, 300, "Vert", 200, 200, new int[]{26, 130, 390, 900, 1100, 1275}));
        cases.add(new CaseCommunaute(33, initPaquetCommunaute()));
        cases.add(new CaseRue("Boulevard des Capucines", 34, 320, "Vert", 200, 200, new int[]{28, 150, 450, 1000, 1200, 1400}));
        cases.add(new CaseGare("Gare Saint-Lazare", 35, 200));
        cases.add(new CaseChance(36, initPaquetChance()));
        cases.add(new CaseRue("Avenue des Champs-Élysées", 37, 350, "Bleu foncé", 200, 200, new int[]{35, 175, 500, 1100, 1300, 1500}));
        cases.add(new CaseImpot("Taxe de luxe", 38, 100));
        cases.add(new CaseRue("Rue de la Paix", 39, 400, "Bleu foncé", 200, 200, new int[]{50, 200, 600, 1400, 1700, 2000}));
    }

    // Initialize card decks (simplified)
    private Stack<Carte> initPaquetChance() {
        Stack<Carte> paquet = new Stack<>();
        // Add chance cards here
        return paquet;
    }

    private Stack<Carte> initPaquetCommunaute() {
        Stack<Carte> paquet = new Stack<>();
        // Add community chest cards here
        return paquet;
    }

    public Case getCase(int position) {
        return cases.get(position % cases.size());
    }

    public int getNombreCases() {
        return cases.size();
    }
}