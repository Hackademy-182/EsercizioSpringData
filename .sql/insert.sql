
INSERT INTO authors(name, surname, email)
value("Valentino", "Rossi", "vr46@boh.it" );

INSERT INTO authors(name, surname, email)
value("Marco", "Bezzecchi", "bez@boh.it");

INSERT INTO posts(title, body, publish_date, author_id)
SELECT "Primo", "Sono troppo forte", null, id FROM authors 
WHERE name = "Valentino"
AND surname = "Rossi";

INSERT INTO posts(title, body, publish_date, author_id)
SELECT "Secondo", "Sono un pò meno forte", null, id FROM authors
WHERE name = "Marco"
AND surname = "Bezzecchi";

INSERT INTO comments(email, body, date, post_id)
SELECT "vr16@boh.it", "Sono troppo forte", null, id
FROM posts
WHERE title = "Primo";

INSERT INTO comments(email, body, date, post_id)
SELECT "bez@boh.it", "Sono un pò meno forte", null, id
FROM posts 
WHERE title = "Secondo"