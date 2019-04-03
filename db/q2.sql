--How many people have not checked out anything?
SELECT name
FROM member
WHERE id
NOT IN (
    SELECT member_id
    FROM checkout_item
);