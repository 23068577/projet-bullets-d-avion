public class Utilisateur {
    private int id;
    private String nom;
    private String email;
    private String motDePasse;
    private String role; // 'utilisateur' ou 'administrateur'

    // Constructeurs, getters, setters
    public Utilisateur(String nom, String email, String motDePasse, String role) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }
    public String getRole() { return role; }
    // autres setters et getters
}
