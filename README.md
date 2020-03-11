# CyberAegisAI

DB의 user_level_result 테이블에 접근하여 각 콘텐츠(system, network, web/app, malware) 사용자 수준을 획득한다.



획득한 데이터는 Fuzzy의 입력으로 사용한다.

```java
// fuzzylogic.java
fb.setVariable("system", 9);
fb.setVariable("network", 9);
fb.setVariable("webapp", 9);
fb.setVariable("malware", 9);
```

위 소스에서 각 콘텐츠 사용자 수준을 숫자(예: 9)에 넣는다. 단, 사용자의 수준은 0~9이다.



Fuzzy의 결과는 'result' 변수에 저장된다.

```java
// fuzzylogic.java
int result = (int)Math.round( row_result );
System.out.println( "Fuzzy Result: " + result );
```

결과는 1부터 81까지 나온다.



Fuzzy 결과를 가지고 'customizing_content.sql'의 rule_set에 조건문으로 넣는다.



 아래 쿼리(customizing_content.sql)를 사용하여 사용자 맞춤형 콘텐츠를 생성한다(실행할 때마다 결과 다름).

```sql
-- customizing_content.sql

-- 침투: 시스템 콘텐츠 추출
( select * from fuzzy_result_content_set where rule_set = 81  and pidnum = 'sys' order by rand() limit 1 )
union
-- 검색: 네트워크 콘텐츠 추출
( select * from fuzzy_result_content_set where rule_set = 81  and pidnum = 'net' order by rand() limit 1 )
union
-- 수집: 웹/응용  콘텐츠 추출
( select * from fuzzy_result_content_set where rule_set = 81  and pidnum = 'web' order by rand() limit 1 )
union
-- 유출: 악성코드  콘텐츠 추출
( select * from fuzzy_result_content_set where rule_set = 81  and pidnum = 'mal' order by rand() limit 1 )
```

파일은 '\MySQL\customizing_content.sql' 참고



위 사용자 맞춤형 콘텐츠는 APT 공격 프로세스 기반으로 생성했다.

APT 공격 프로세스(4단계)에 따라 콘텐츠를 배치한다.

* 침투: 공격자가 취약한 시스템이나 직원들을 악성코드로 감염시켜 네트워크 내부로 침투
* 검색: 침투한 내부 시스템 및 인프라 구조에 대한 정보를 수집한 후 다음 단계를 계획
* 수집: 보호되지 않은 시스템 상의 데이터 수집 또는 시스템 운영 방해
* 유출: 공격자의 근거로 데이터 전송, 시스템 운영 방해, 또는 장비 파괴