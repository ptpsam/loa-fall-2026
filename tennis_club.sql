-- Create tables

CREATE TABLE users (
id INTEGER PRIMARY KEY,
name TEXT,
email TEXT
);

CREATE TABLE courts (
id INTEGER PRIMARY KEY,
name TEXT,
type TEXT
);

CREATE TABLE reservations (
id INTEGER PRIMARY KEY,
user_id INTEGER REFERENCES users(id),
court_id INTEGER REFERENCES courts(id),
date DATE,
start_time TIME
);

-- Insert data

INSERT INTO users (id, name, email)
VALUES
(1, 'Samuel', 'samuel@example.com'),
(2, 'Alice', 'alice@example.com'),
(3, 'Bob', 'bob.tennis@example.com');

INSERT INTO courts (id, name, type)
VALUES
(1, 'Court 1', 'Indoor'),
(2, 'Court 2', 'Outdoor'),
(3, 'Court 3', 'Outdoor');

INSERT INTO reservations (id, user_id, court_id, date, start_time)
VALUES
(1, 1, 1, '2026-09-25', '18:00'),
(2, 2, 2, '2026-09-25', '19:00'),
(3, 3, 3, '2026-09-25', '10:00');

-- Constraints

ALTER TABLE users
ALTER COLUMN name SET NOT NULL,
ALTER COLUMN email SET NOT NULL;

ALTER TABLE users
ADD CONSTRAINT email_unique UNIQUE(email);

ALTER TABLE reservations
ADD CONSTRAINT already_reserved_court UNIQUE(court_id, date, start_time);

ALTER TABLE reservations
ADD CONSTRAINT no_more_court_allowed UNIQUE(user_id, date, start_time);

ALTER TABLE reservations
ALTER COLUMN user_id SET NOT NULL,
ALTER COLUMN court_id SET NOT NULL,
ALTER COLUMN date SET NOT NULL,
ALTER COLUMN start_time SET NOT NULL;

-- Joins

SELECT users.name, courts.name, reservations.date, reservations.start_time
FROM reservations
INNER JOIN users ON users.id = reservations.user_id
INNER JOIN courts ON courts.id = reservations.court_id;

-- Check if a court is reserved

SELECT EXISTS (
SELECT 1
FROM reservations
WHERE court_id = 1
AND date = '2026-09-25'
AND start_time = '18:00'
);

-- Show every court with its status

SELECT name,
CASE
WHEN EXISTS (
SELECT 1 FROM reservations
WHERE date = '2026-09-25'
AND start_time = '18:00'
AND reservations.court_id = courts.id
) THEN 'Réservé'
ELSE 'Disponible'
END AS status
FROM courts;

-- Show available courts

SELECT name
FROM courts
WHERE NOT EXISTS (
SELECT 1 FROM reservations
WHERE date = '2026-09-25'
AND start_time = '18:00'
AND reservations.court_id = courts.id
);

-- Count reservations for each court

SELECT name, COUNT(court_id)
FROM courts
INNER JOIN reservations ON reservations.court_id = courts.id
GROUP BY name;