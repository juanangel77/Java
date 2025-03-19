public class CompteBancaire {
    private double solde;
    private String numeroCompte;
    private String titulaire;
    private double decouvertAutorise;  // Nouvel attribut pour le découvert autorisé

    // Constructeur modifié pour inclure le découvert autorisé
    public CompteBancaire(String numeroCompte, String titulaire, double soldeInitial, double decouvertAutorise) {
        this.numeroCompte = numeroCompte;
        this.titulaire = titulaire;
        this.solde = soldeInitial;
        this.decouvertAutorise = decouvertAutorise;
    }

    // Méthode pour déposer de l'argent
    public void deposer(double montant) throws MontantInvalideException {
        if (montant <= 0) {
            throw new MontantInvalideException("Le montant à déposer doit être positif.");
        }
        solde += montant;
    }

    // Méthode pour retirer de l'argent, en tenant compte du découvert autorisé
    public void retirer(double montant) throws MontantInvalideException, SoldeInsuffisantException {
        if (montant <= 0) {
            throw new MontantInvalideException("Le montant à retirer doit être positif.");
        }
        if (solde - montant < -decouvertAutorise) {
            throw new SoldeInsuffisantException(solde, montant);
        }
        solde -= montant;
    }

    // Méthode pour effectuer un virement
    public void virement(CompteBancaire destinataire, double montant) throws MontantInvalideException, SoldeInsuffisantException, CompteInexistantException {
        if (destinataire == null) {
            throw new CompteInexistantException("Le compte destinataire n'existe pas.");
        }
        this.retirer(montant); // Vérifie d'abord le retrait
        destinataire.deposer(montant); // Puis dépose sur le compte destinataire
    }

    // Getters et Setters
    public double getSolde() {
        return solde;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public String getTitulaire() {
        return titulaire;
    }
}