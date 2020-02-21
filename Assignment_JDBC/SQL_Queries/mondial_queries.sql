-- SQL1. All attributes of all volcanic islands.

select *
from island
where type = 'volcanic';

-- SQL2. The name, latitude, and longitude of all airports. Rename the resulting attributes to ‘n‘, ‘la‘, and ‘lo‘.

SELECT name as n, latitude as la, longitude as lo
FROM airport;

-- SQL3. The names of islands which are part of either, the Inner, Outer, or New Hebrides.

select name
from island
where islands = 'Inner Hebrides'
   OR islands = 'Outer Hebrides'
   OR islands = 'New Hebrides';

-- SQL4. The names of all deserts, and the country codes of all countries bordering them. Ensure that the
-- result does not contain duplicate rows.

select DISTINCT desert, country
from geo_desert;

-- SQL5. The country codes and names of all countries which are members of the ’G-24’ organization and
-- which are at least partially located in Asia.

SELECT i.country, c.name, e.continent
from ISMEMBER i,
     COUNTRY c,
     encompasses e
WHERE i.organization = 'G-24'
  AND i.country = c.code
  AND c.code = e.country
  AND e.continent = 'Asia'
  AND i.type = 'member';

-- SQL6. The name of all countries where more than half of the population speaks English Hint: use the
-- “percentage” attribute in the “Language” table.

SELECT c.name
FROM COUNTRY c,
     LANGUAGE l
WHERE c.code = l.country
  AND l.name = 'English'
  AND l.percentage > 50;

-- SQL7. The names of “waters” of all kinds (i.e., lakes, rivers, and seas), ordered by name in ascending order.

(SELECT l.name
 FROM LAKE l)

UNION

(SELECT r.name
 FROM RIVER r)

UNION

(SELECT s.name
 FROM SEA s)

ORDER BY name ASC;

-- SQL8. The average infant mortality across all countries of the world (a single number, not per country!).
-- Report the result in an attribute called ‘avg_inf_mort’.

SELECT AVG(infant_mortality) as avg_inf_mort
FROM POPULATION;

-- SQL9. The continent name and average GDP for each continent. If a country is on multiple continents,
-- count their GDP towards the average calculation for all their continents.

SELECT en.continent, AVG(ec.gdp) as avg_gdp
FROM ECONOMY ec,
     encompasses en
WHERE ec.country = en.country
GROUP BY en.continent;

-- SQL10. The names of all islands and the lakes that are on them. If an island does not have any lake on it,
-- report a NULL value for the name of the lake. Hint: consider the table “lakeonisland”.

SELECT island.name as Island_Name, lakeonisland.lake
FROM Island
       LEFT OUTER JOIN Lakeonisland ON island.name = lakeonisland.island;


-- SQL11. The name of all countries that only have borders longer than 100 km.

with result as (SELECT code
                FROM COUNTRY
                  EXCEPT ((SELECT country1
                           FROM borders
                           where length < 100)

                          UNION

                          (SELECT country2
                           FROM borders
                           where length < 100)

                          UNION

                          (select code
                           from country except ((select country1 from borders) union (select country2 from borders)))))

select name
from country,
     result
WHERE country.code = result.code;



-- SQL12. All countries in Asia for which the database records at least one spoken language that is not English
-- and that is also spoken by at least 50% of the population in any country within America.
-- Report the name of the country and the name of the language.

-- Returns all countries in ASIA where at least one spoken language is not English
WITH Asian_countries as (SELECT e.country as CountryName, l.name as LanguageName, l.percentage
                         FROM ENCOMPASSES e,
                              LANGUAGE l
                         WHERE e.country = l.country
                           AND e.continent = 'Asia'
                           AND l.name != 'English'),

     --Returns all countries in AMERICA where the population speak more than 50% of a language
     American_countries as (SELECT en.country as CountryName, l.name as LanguageName, l.percentage
                            FROM ENCOMPASSES en,
                                 LANGUAGE l
                            WHERE en.country = l.country
                              AND en.continent = 'America'
                              AND l.percentage >= 50)

SELECT Asian_countries.CountryName
FROM Asian_countries,
     American_countries
WHERE Asian_countries.LanguageName = American_countries.LanguageName;
