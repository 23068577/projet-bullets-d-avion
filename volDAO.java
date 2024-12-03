import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolDAO {
    private Connection conn;

    public VolDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Vol> searchVols(String destination, Date dateDepart) {
        List<Vol> vols = new ArrayList<>();
        try {
            String query = "SELECT * FROM Vol WHERE destination = ? AND date_depart >= ? ORDER BY prix ASC";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, destination);
            stmt.setDate(2, dateDepart);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vol vol = new Vol(
                        rs.getInt("id_compagnie"),
                        rs.getString("destination"),
                        rs.getDate("date_depart"),
                        rs.getDate("date_arrivee"),
                        rs.getDouble("prix"),
                        rs.getInt("duree")
                );
                vols.add(vol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vols;
    }

    // Autres méthodes pour ajouter, modifier, supprimer un vol
}
