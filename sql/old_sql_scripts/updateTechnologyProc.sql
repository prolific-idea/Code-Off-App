USE dbo;

DROP PROCEDURE IF EXISTS updateTechnologies$$

DELIMITER $$
CREATE PROCEDURE updateTechnologies()

BEGIN    
	DECLARE limiter INT;
    DECLARE increment INT;
    DECLARE tech VARCHAR(12);

    SET limiter = 20;
    SET increment = 0;
    SET tech = 'C#';
    
	loop1: LOOP
		UPDATE dbo.technology SET dbo.technology.description = tech WHERE dbo.technology.techId = FLOOR(RAND() * (100 - 1 + 1) + 1);
        SELECT FLOOR(RAND() * (100 - 1 + 1) + 1);
        SET increment = increment + 1;
        IF increment > limiter THEN
			LEAVE loop1;
		END IF;
    END LOOP loop1;
    
END$$
DELIMITER ;