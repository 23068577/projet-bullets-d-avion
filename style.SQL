-- Table des utilisateurs
CREATE TABLE Utilisateur (
    id_utilisateur INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    mot_de_passe VARCHAR(100),
    role VARCHAR(50) -- 'utilisateur' ou 'administrateur'
);

-- Table des compagnies aériennes
CREATE TABLE Compagnie (
    id_compagnie INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    description TEXT
);

-- Table des vols
CREATE TABLE Vol (
    id_vol INT AUTO_INCREMENT PRIMARY KEY,
    id_compagnie INT,
    destination VARCHAR(100),
    date_depart DATE,
    date_arrivee DATE,
    prix DECIMAL(10,2),
    duree INT,
    FOREIGN KEY (id_compagnie) REFERENCES Compagnie(id_compagnie)
);

-- Table des réservations
CREATE TABLE Reservation (
    id_reservation INT AUTO_INCREMENT PRIMARY KEY,
    id_utilisateur INT,
    id_vol INT,
    date_reservation DATE,
    status VARCHAR(50), -- 'confirmée', 'annulée', etc.
    FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur),
    FOREIGN KEY (id_vol) REFERENCES Vol(id_vol)
);

CREATE TABLE flights (
    id INT AUTO_INCREMENT PRIMARY KEY,
    company_id INT NOT NULL,
    destination VARCHAR(255) NOT NULL,
    departure_date DATE NOT NULL,
    arrival_date DATE NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    duration INT NOT NULL, 
    FOREIGN KEY (company_id) REFERENCES companies(id)
);
