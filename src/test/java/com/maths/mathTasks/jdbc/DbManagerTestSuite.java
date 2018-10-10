package com.maths.mathTasks.jdbc;

import com.maths.mathTasks.service.DbManager;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;

public class DbManagerTestSuite {

    @Test
    public void testConnect()throws SQLException{
        //Given
        //When
        DbManager dbManager = DbManager.getInstance();
        //Then
        Assert.assertNotNull(dbManager.getConnection());
        }

        @Test
    public void testSelectMathTaskWithTaskLvl2() throws SQLException{
        //Given
            DbManager dbManager = DbManager.getInstance();

            //When

            String sqlQuery = "SELECT * FROM math_tasks " +
                    "WHERE task_lvl = 2";
            Statement statement = dbManager.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);

            //Then
            int counter = 0;
            while (rs.next()){
                System.out.println(rs.getString("task_id") + ", " +
                rs.getString("a") + ", " +
                rs.getString("task_type") + ", " +
                rs.getString("b") + ", " +
                rs.getString("result") + ", " +
                rs.getString("correct") + ", " +
                rs.getString("task_lvl") + ", " +
                rs.getString("reply"));
                counter ++;
            }
            rs.close();
            statement.close();
            Assert.assertEquals(2,counter);
        }
}
