import java.util.ArrayList;
import java.util.List;

public class TestCompteBancaire {

    public static void main(String[] args) {
        // Créer plusieurs comptes bancaires
        CompteBancaire compte1 = new CompteBancaire("12345", "Juan", 500, 200); // Solde initial de 500, découvert autorisé de 200
        CompteBancaire compte2 = new CompteBancaire("67890", "Alice", 1000, 300); // Solde initial de 1000, découvert autorisé de 300
        CompteBancaire compte3 = new CompteBancaire("54321", "Bob", 200, 0); // Solde initial de 200, pas de découvert autorisé

        // Liste pour stocker l'historique des transactions
        List<String> historique = new ArrayList<>();

        // Tester les dépôts
        try {
            compte1.deposer(100); // Dépôt valide
            historique.add("Dépôt de 100 sur compte 1. Nouveau solde: " + compte1.getSolde());
            System.out.println("Dépôt réussi sur compte 1. Nouveau solde: " + compte1.getSolde());
        } catch (MontantInvalideException e) {
            System.out.println("Erreur lors du dépôt: " + e.getMessage());
        }

        // Tester les retraits valides et invalides
        try {
            compte1.retirer(200); // Retrait valide
            historique.add("Retrait de 200 sur compte 1. Nouveau solde: " + compte1.getSolde());
            System.out.println("Retrait réussi sur compte 1. Nouveau solde: " + compte1.getSolde());
        } catch (MontantInvalideException | SoldeInsuffisantException e) {
            System.out.println("Erreur lors du retrait: " + e.getMessage());
        }

        try {
            compte1.retirer(800); // Retrait invalide (solde insuffisant)
        } catch (MontantInvalideException | SoldeInsuffisantException e) {
            System.out.println("Erreur lors du retrait: " + e.getMessage());
        }

        // Tester un virement valide
        try {
            compte1.virement(compte2, 150); // Virement valide
            historique.add("Virement de 150 de compte 1 vers compte 2. Nouveau solde compte 1: " + compte1.getSolde() + ", Nouveau solde compte 2: " + compte2.getSolde());
            System.out.println("Virement réussi de compte 1 vers compte 2. Nouveau solde compte 1: " + compte1.getSolde() + ", Nouveau solde compte 2: " + compte2.getSolde());
        } catch (MontantInvalideException | SoldeInsuffisantException | CompteInexistantException e) {
            System.out.println("Erreur lors du virement: " + e.getMessage());
        }

        // Tester un virement vers un compte inexistant
        try {
            compte1.virement(null, 50); // Virement vers un compte inexistant
        } catch (MontantInvalideException | SoldeInsuffisantException | CompteInexistantException e) {
            System.out.println("Erreur lors du virement: " + e.getMessage());
        }

        // Tester un retrait avec découvert autorisé
        try {
            compte3.retirer(250); // Retrait valide, dans la limite du découvert autorisé
            historique.add("Retrait de 250 sur compte 3. Nouveau solde: " + compte3.getSolde());
            System.out.println("Retrait réussi sur compte 3. Nouveau solde: " + compte3.getSolde());
        } catch (MontantInvalideException | SoldeInsuffisantException e) {
            System.out.println("Erreur lors du retrait: " + e.getMessage());
        }

        // Affichage de l'historique des transactions
        System.out.println("\nHistorique des transactions :");
        for (String transaction : historique) {
            System.out.println(transaction);
        }
    }
}