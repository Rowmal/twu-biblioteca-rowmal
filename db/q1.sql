--Who checked out the book 'The Hobbit’?
--Anand Beck
SELECT name
FROM member
WHERE id
IN (
    SELECT checkout_item.member_id
    FROM checkout_item, book
    WHERE checkout_item.book_id = book.id
    AND book.title = 'The Hobbit'
);