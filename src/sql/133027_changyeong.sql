SELECT tb.FLAVOR
FROM (SELECT *
      FROM FIRST_HALF
      
      Union
      
      Select *
      FROM July
     ) tb

GROUP BY tb.FLAVOR
ORDER BY SUM(tb.TOTAL_ORDER) DESC LIMIT 3