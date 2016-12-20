-- use database hr

CREATE TABLE EMPLOYEE_INFO(
    ID int not null auto_increment,
    FIRST_NAME varchar(30) ,
    LAST_NAME varchar(30) ,
    JOB_TITLE varchar(100) ,
    DEPARTMENT varchar(100) ,
    SALARY int,
    PRIMARY KEY(ID)
);

INSERT INTO EMPLOYEE_INFO
(FIRST_NAME, LAST_NAME, JOB_TITLE, DEPARTMENT, SALARY)
VALUES
('RAVI', 'SONI', 'AUTHOR', 'TECHNOLOGY', 5000);
