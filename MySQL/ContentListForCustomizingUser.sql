SELECT *
FROM content c
JOIN rules r ON c.`level` = r.sys
WHERE c.pidnum = 'sys'
UNION
SELECT *
FROM content c
JOIN rules r ON c.`level` = r.net
WHERE c.pidnum = 'net'
UNION
SELECT *
FROM content c
JOIN rules r ON c.`level` = r.web
WHERE c.pidnum = 'web'
UNION
SELECT *
FROM content c
JOIN rules r ON c.`level` = r.mal
WHERE c.pidnum = 'mal'