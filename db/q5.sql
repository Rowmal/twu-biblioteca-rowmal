--Who has checked out more than 1 item?
--Anand Beck
--Frank Smith
SELECT name
FROM member
WHERE id
IN (
    SELECT member_id
    FROM checkout_item
    GROUP BY member_id
    HAVING count(member_id) > 1
);