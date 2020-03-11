-- 침투: 시스템 콘텐츠 추출
( select * from content_set where rule_set = 81  and pidnum = 'sys' order by rand() limit 1 )
union
-- 검색: 네트워크 콘텐츠 추출
( select * from content_set where rule_set = 81  and pidnum = 'net' order by rand() limit 1 )
union
-- 수집: 웹/응용  콘텐츠 추출
( select * from content_set where rule_set = 81  and pidnum = 'web' order by rand() limit 1 )
union
-- 유출: 악성코드  콘텐츠 추출
( select * from content_set where rule_set = 81  and pidnum = 'mal' order by rand() limit 1 )