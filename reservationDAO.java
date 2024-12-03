import java.sql.*;

public class ReservationDAO {
    private Connection conn;

    public ReservationDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean addReservation(Reservation reservation) {
        try {
            String query = "INSERT INTO Reservation (id_utilisateur, id_vol, date_reservation, status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, reservation.getIdUtilisateur());
            stmt.setInt(2, reservation.getIdVol());
            stmt.setDate(3, reservation.getDateReservation());
            stmt.setString(4, reservation.getStatus());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Autres méthodes pour récupérer, modifier, supprimer une réservation
}
