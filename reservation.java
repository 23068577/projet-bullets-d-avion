public class Reservation {
    private int id;
    private int idUtilisateur;
    private int idVol;
    private Date dateReservation;
    private String status; // 'confirmée', 'annulée', etc.

    // Constructeurs, getters, setters
    public Reservation(int idUtilisateur, int idVol, Date dateReservation, String status) {
        this.idUtilisateur = idUtilisateur;
        this.idVol = idVol;
        this.dateReservation = dateReservation;
        this.status = status;
    }

    public int getId() { return id; }
    public int getIdUtilisateur() { return idUtilisateur; }
    public int getIdVol() { return idVol; }
    public Date getDateReservation() { return dateReservation; }
    public String getStatus() { return status; }
}
