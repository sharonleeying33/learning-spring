package org.packt.spring.chapter6.hibernate.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 *
 */
@Component
public class DBUtils {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void initialize() {

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS EMPLOYEE_INFO");
            statement.executeUpdate("CREATE TABLE EMPLOYEE_INFO("
                    + "ID serial NOT NULL Primary key, "
                    + "FIRST_NAME varchar(30) not null, "
                    + "LAST_NAME varchar(30) not null, "
                    + "JOB_TITLE varchar(100) not null, "
                    + "DEPARTMENT varchar(100) not null, "
                    + "SALARY INTEGER)");
            statement.executeUpdate("INSERT INTO EMPLOYEE_INFO (FIRST_NAME, LAST_NAME, JOB_TITLE,DEPARTMENT, SALARY) VALUES ('RAVI', 'SONI', 'AUTHOR','TECHNOLOGY', 5000)");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
