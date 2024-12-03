public class Vol {
    private int id;
    private int idCompagnie;
    private String destination;
    private Date dateDepart;
    private Date dateArrivee;
    private double prix;
    private int duree;

    // Constructeurs, getters, setters
    public Vol(int idCompagnie, String destination, Date dateDepart, Date dateArrivee, double prix, int duree) {
        this.idCompagnie = idCompagnie;
        this.destination = destination;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.prix = prix;
        this.duree = duree;
    }

    public int getId() { return id; }
    public int getIdCompagnie() { return idCompagnie; }
    public String getDestination() { return destination; }
    public Date getDateDepart() { return dateDepart; }
    public Date getDateArrivee() { return dateArrivee; }
    public double getPrix() { return prix; }
    public int getDuree() { return duree; }
}
