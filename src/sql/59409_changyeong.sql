SELECT ANIMAL_ID, NAME, if(SEX_UPON_INTAKE like "Intact%", "X", "O") AS "중성화"
FROM ANIMAL_INS