--What books and movies aren't checked out?
SELECT title
FROM book
WHERE id
NOT IN (
    SELECT book_id
    FROM checkout_item
    WHERE book_id IS NOT null
);

SELECT title
FROM movie
WHERE id
NOT IN (
    SELECT movie_id
    FROM checkout_item
    WHERE movie_id IS NOT null
);