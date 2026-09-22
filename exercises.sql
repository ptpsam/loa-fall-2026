-- Topics: SELECT queries & constraints, Multi-table queries, Queries with aggregates.

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

-- 