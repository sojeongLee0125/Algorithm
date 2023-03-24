-- 코드를 입력하세요
-- 상반기 아이스크림 총주문량이 3,000보다 높으면서 
-- 아이스크림의 주 성분이 과일인 아이스크림의 맛을 
-- 총주문량이 큰 순서대로 조회하는 SQL 문을 작성해주세요.

SELECT H.FLAVOR
FROM FIRST_HALF AS H
JOIN ICECREAM_INFO AS I ON I.FLAVOR = H.FLAVOR
WHERE I.INGREDIENT_TYPE LIKE "fruit_based" AND
H.TOTAL_ORDER >= 3000
ORDER BY H.TOTAL_ORDER DESC;