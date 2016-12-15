-- IN MYSQL
--DROP PROCEDURE IF EXISTS getEmployee

DELIMITER //

CREATE PROCEDURE getEmployee
(IN _id INT,
OUT Emp_Name VARCHAR(20),
OUT Emp_Age INT
)
BEGIN
    SELECT Name, Age
    INTO Emp_Name, Emp_Age
    FROM employee where Id = _id;
END;

//

DELIMITER ;