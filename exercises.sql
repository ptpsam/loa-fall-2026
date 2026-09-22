-- Problems from SQLBolt.com

-- Topics: SELECT queries & constraints, Multi-table queries, Queries with aggregates.


-- Simple SELECT queries.

-- Exercise 1: Order all the cities in the United States by their latitude from north to south
SELECT city
FROM north_american_cities
WHERE country LIKE "United States"
ORDER BY latitude DESC;

-- Exercise 2: List the third and fourth largest cities (by population) in the United States and their population
SELECT city, population 
FROM north_american_cities
WHERE country LIKE "United States"
ORDER BY population DESC
LIMIT 2 OFFSET 2;


-- Focus on INNER/OUTER JOINs.

-- Exercise 1: Show the sales numbers for each movie that did better internationally rather than domestically
SELECT title, domestic_sales, international_sales
FROM movies
INNER JOIN boxoffice
ON id = movie_id
WHERE international_sales > domestic_sales;

-- Exercise 2: List all buildings and the distinct employee roles in each building (including empty buildings) 
SELECT DISTINCT building_name, role
FROM buildings
LEFT JOIN employees
ON building_name = building;


-- Overall review.

-- Exercise 1: Find the number of movies each director has directed 
SELECT director, COUNT() AS Movies 
FROM movies
GROUP BY director;

-- Exercise 2: Find the total domestic and international sales that can be attributed to each director
SELECT director, SUM(domestic_sales + international_sales) AS Total_sales
FROM movies
INNER JOIN boxoffice
ON movie_id = id
GROUP BY director;



-- Topics: Inserting/Updating/Deleting rows, Creating/Altering/Dropping tables.

-- Exercise 1: Both the title and director for Toy Story 8 is incorrect! The title should be "Toy Story 3" and it was directed by Lee Unkrich
UPDATE movies
SET title = "Toy Story 3",
    director = "Lee Unkrich"
WHERE id = 11;

-- Exercise 2: Create a new table named Database with the following columns:
-- Name A string (text) describing the name of the database
-- Version A number (floating point) of the latest version of this database
-- Download_count An integer count of the number of times this database was downloaded
CREATE TABLE Database(
    Name TEXT,
    Version FLOAT,
    Download_count INTEGER);

-- Exercise 3: Add another column named Language with a TEXT data type to store the language that the movie was released in. Ensure that the default for this language is English.
ALTER TABLE Movies
ADD COLUMN Language TEXT
DEFAULT "English";