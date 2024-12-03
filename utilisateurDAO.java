import java.sql.*;

public class UtilisateurDAO {
    private Connection conn;

    public UtilisateurDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean addUtilisateur(Utilisateur utilisateur) {
        try {
            String query = "INSERT INTO Utilisateur (nom, email, mot_de_passe, role) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getEmail());
            stmt.setString(3, utilisateur.getMotDePasse());
            stmt.setString(4, utilisateur.getRole());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Autres méthodes pour récupérer, modifier, supprimer un utilisateur
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;

public class FenetreRecherche extends JFrame {
    private JTextField tfDestination;
    private JButton btnRechercher;
    private JTable tableVols;
    private VolDAO volDAO;

    public FenetreRecherche(VolDAO volDAO) {
        this.volDAO = volDAO;
        setTitle("Recherche de Vols");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel pour contenir les champs de recherche
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        JLabel lblDestination = new JLabel("Destination :");
        tfDestination = new JTextField();
        JLabel lblDate = new JLabel("Date de départ :");
        JDateChooser dateChooser = new JDateChooser();  // Vous pouvez utiliser JDateChooser pour une date
        btnRechercher = new JButton("Rechercher");

        panel.add(lblDestination);
        panel.add(tfDestination);
        panel.add(lblDate);
        panel.add(dateChooser);
        panel.add(btnRechercher);

        btnRechercher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = tfDestination.getText();
                Date dateDepart = new Date(dateChooser.getDate().getTime());
                List<Vol> vols = volDAO.searchVols(destination, dateDepart);
                // Afficher les vols dans un JTable
                afficherVols(vols);
            }
        });

        // Ajout du panel à la fenêtre
        add(panel, BorderLayout.NORTH);

        // Initialisation du tableau de résultats
        tableVols = new JTable();
        add(new JScrollPane(tableVols), BorderLayout.CENTER);

        setVisible(true);
    }

    private void afficherVols(List<Vol> vols) {
        // Convertir la liste de vols en données pour JTable
        String[] columnNames = {"ID", "Compagnie", "Destination", "Date Départ", "Date Arrivée", "Prix", "Durée"};
        Object[][] data = new Object[vols.size()][7];
        for (int i = 0; i < vols.size(); i++) {
            Vol vol = vols.get(i);
            data[i][0] = vol.getId();
            data[i][1] = vol.getIdCompagnie();
            data[i][2] = vol.getDestination();
            data[i][3] = vol.getDateDepart();
            data[i][4] = vol.getDateArrivee();
            data[i][5] = vol.getPrix();
            data[i][6] = vol.getDuree();
        }

        tableVols.setModel(new DefaultTableModel(data, columnNames));
    }

    public static void main(String[] args) {
        new FenetreRecherche(new VolDAO(Database.getConnection()));  // Database.getConnection() à implémenter
    }
}
