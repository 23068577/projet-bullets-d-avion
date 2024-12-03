<?php
// db.php
$host = 'localhost';
$dbname = 'flight_comparator';
$username = 'root'; // Remplacez par vos informations
$password = '';

try {
    $pdo = new PDO("mysql:host=$host;dbname=$dbname", $username, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo 'Connection failed: ' . $e->getMessage();
}
?>
<?php
// models/Flight.php
class Flight {
    public $id;
    public $company_id;
    public $destination;
    public $departure_date;
    public $arrival_date;
    public $price;
    public $duration;

    public function __construct($id, $company_id, $destination, $departure_date, $arrival_date, $price, $duration) {
        $this->id = $id;
        $this->company_id = $company_id;
        $this->destination = $destination;
        $this->departure_date = $departure_date;
        $this->arrival_date = $arrival_date;
        $this->price = $price;
        $this->duration = $duration;
    }

    public static function searchFlights($pdo, $destination, $departure_date) {
        $sql = "SELECT * FROM flights WHERE destination LIKE ? AND departure_date = ?";
        $stmt = $pdo->prepare($sql);
        $stmt->execute([$destination, $departure_date]);
        $flights = $stmt->fetchAll(PDO::FETCH_ASSOC);
        
        $result = [];
        foreach ($flights as $flight) {
            $result[] = new Flight(
                $flight['id'], 
                $flight['company_id'], 
                $flight['destination'], 
                $flight['departure_date'], 
                $flight['arrival_date'], 
                $flight['price'], 
                $flight['duration']
            );
        }
        return $result;
    }
}
?>
<?php
// controllers/FlightController.php
require_once 'models/Flight.php';

class FlightController {
    public function searchFlights($pdo, $destination, $departure_date) {
        return Flight::searchFlights($pdo, $destination, $departure_date);
    }
}
?>
<!-- views/search.php -->
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Comparateur de Vols</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <header>
        <h1>Comparateur de Billets d'Avion</h1>
    </header>

    <section id="search-section">
        <h2>Rechercher un Vol</h2>
        <form action="results.php" method="GET">
            <label for="destination">Destination :</label>
            <input type="text" id="destination" name="destination" required>

            <label for="date-depart">Date de départ :</label>
            <input type="date" id="date-depart" name="date-depart" required>

            <button type="submit">Rechercher</button>
        </form>
    </section>

    <footer>
        <p>&copy; 2024 Comparateur de Billets d'Avion</p>
    </footer>
</body>
</html>
<?php
// results.php
require_once 'includes/db.php';
require_once 'controllers/FlightController.php';

$flightController = new FlightController();

if (isset($_GET['destination']) && isset($_GET['date-depart'])) {
    $destination = $_GET['destination'];
    $date_depart = $_GET['date-depart'];
    $flights = $flightController->searchFlights($pdo, $destination, $date_depart);
}
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Résultats de Recherche</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <header>
        <h1>Résultats de la Recherche</h1>
    </header>

    <section id="results-section">
        <table>
            <thead>
                <tr>
                    <th>Compagnie</th>
                    <th>Destination</th>
                    <th>Date de départ</th>
                    <th>Date d'arrivée</th>
                    <th>Prix</th>
                    <th>Durée</th>
                    <th>Réserver</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($flights as $flight): ?>
                    <tr>
                        <td><?php echo $flight->company_id; ?></td>
                        <td><?php echo $flight->destination; ?></td>
                        <td><?php echo $flight->departure_date; ?></td>
                        <td><?php echo $flight->arrival_date; ?></td>
                        <td><?php echo $flight->price; ?> €</td>
                        <td><?php echo $flight->duration; ?> min</td>
                        <td><a href="reservation.php?flight_id=<?php echo $flight->id; ?>">Réserver</a></td>
                    </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    </section>

    <footer>
        <p>&copy; 2024 Comparateur de Billets d'Avion</p>
    </footer>
</body>
</html>
<?php
// reservation.php
require_once 'includes/db.php';
require_once 'models/Reservation.php';

if (isset($_GET['flight_id'])) {
    $flight_id = $_GET['flight_id'];
    // Gérer la réservation ici
    $reservation = new Reservation();
    $reservation->reserve($pdo, $flight_id);
    echo "Réservation effectuée avec succès.";
}
?>
