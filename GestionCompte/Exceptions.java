// Exception pour solde insuffisant
class SoldeInsuffisantException extends Exception {
    private double soldeDisponible;
    private double montantDemande;

    // Constructeur modifié pour inclure solde disponible et montant demandé
    public SoldeInsuffisantException(double soldeDisponible, double montantDemande) {
        super("Solde insuffisant. Solde disponible: " + soldeDisponible + " €, montant demandé: " + montantDemande + " €.");
        this.soldeDisponible = soldeDisponible;
        this.montantDemande = montantDemande;
    }

    public double getSoldeDisponible() {
        return soldeDisponible;
    }

    public double getMontantDemande() {
        return montantDemande;
    }
}

// Exception pour montant invalide
class MontantInvalideException extends Exception {
    public MontantInvalideException(String message) {
        super(message);
    }
}

// Exception pour compte inexistant
class CompteInexistantException extends Exception {
    public CompteInexistantException(String message) {
        super(message);
    }
}