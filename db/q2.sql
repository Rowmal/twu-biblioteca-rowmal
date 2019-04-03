--How many people have not checked out anything?
--37
SELECT count(id)
FROM member
WHERE id
NOT IN (
    SELECT member_id
    FROM checkout_item
    GROUP BY member_id
);