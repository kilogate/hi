package com.kilogate.hi.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC 事务
 *
 * @author kilogate
 * @create 2021/1/4 23:57
 **/
public class JdbcTransaction {
    public static void main(String[] args) throws SQLException {
        // 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "";
        String passport = "";
        Connection connection = DriverManager.getConnection(url, user, passport);

        // 关闭自动提交
        connection.setAutoCommit(false);

        // 新建语句
        Statement statement = connection.createStatement();

        // 执行更新
        try {
            statement.executeUpdate("UPDATE Books SET Price = 666 WHERE ISBN = '0-201-98766-1';");
            statement.executeUpdate("UPDATE Books SET Price = 666 WHERE ISBN = '0-201-98766-2';");

            // 报错的 SQL
            statement.executeUpdate("UPDATE Book SET Price = 666 WHERE ISBN = '0-201-98766-2';");

            statement.executeUpdate("UPDATE Books SET Price = 666 WHERE ISBN = '0-201-98766-3';");
            statement.executeUpdate("UPDATE Books SET Price = 666 WHERE ISBN = '0-201-98766-4';");
        } catch (Exception e) {
            e.printStackTrace();

            // 回滚
            connection.rollback();
            return;
        }

        // 提交
        connection.commit();
    }
}
