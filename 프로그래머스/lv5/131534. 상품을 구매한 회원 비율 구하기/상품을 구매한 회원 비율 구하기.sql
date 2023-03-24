-- 2021년에 가입한 전체 회원들 중 상품을 구매한 회원수와 
-- 상품을 구매한 회원의 비율(=2021년에 가입한 회원 중 상품을 구매한 회원수 / 2021년에 가입한 전체 회원 수)을 
-- 년, 월 별로 출력하는 SQL문을 작성해주세요.
-- 상품을 구매한 회원의 비율은 소수점 두번째자리에서 반올림하고, 
-- 전체 결과는 년을 기준으로 오름차순 정렬해주시고 년이 같다면 월을 기준으로 오름차순 정렬해주세요.

-- 2021년에 가입해서 구매한 회원 목록 구하기
WITH PURCHASE AS (
    SELECT U.USER_ID AS P_ID, YEAR(O.SALES_DATE) AS YEAR, MONTH(O.SALES_DATE) AS MONTH
    FROM USER_INFO U 
    JOIN ONLINE_SALE O ON U.USER_ID = O.USER_ID
    WHERE YEAR(U.JOINED) = '2021'
)

SELECT P.YEAR, P.MONTH, 
count(distinct(P_ID)) as PUCHASED_USERS, 
round(count(distinct(P_ID))/count(distinct(U.USER_ID)),1) as PUCHASED_RATIO
FROM USER_INFO U, PURCHASE P
WHERE YEAR(U.JOINED) = '2021'
GROUP BY YEAR, MONTH
ORDER BY YEAR, MONTH;