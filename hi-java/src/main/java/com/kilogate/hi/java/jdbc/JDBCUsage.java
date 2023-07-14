package com.kilogate.hi.java.jdbc;

import java.sql.*;

/**
 * JDBCUsage
 *
 * @author kilogate
 * @create 2023/7/14 23:14
 **/
public class JDBCUsage {
    public static void main(String[] args) throws SQLException {
        usage1();
    }

    private static void usage1() throws SQLException {
        // 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "root";
        String passport = "kilogate";
        Connection connection = DriverManager.getConnection(url, user, passport);

        // 新建语句
        Statement statement = connection.createStatement();

        // 执行插入
        statement.executeUpdate("insert into user values (1, 'AAA');");
        statement.executeUpdate("insert into user values (2, 'BBB');");
        statement.executeUpdate("insert into user values (3, 'CCC');");

        // 执行查询
        ResultSet resultSet = statement.executeQuery("select * from user;");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            System.out.println(String.format("id: %d, name: %s", id, name));
        }

        // 执行更新
        statement.executeUpdate("update user set name = 'A' where id = 1;");
        resultSet = statement.executeQuery("select * from user where id = 1;");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println(String.format("id: %d, name: %s", id, name));
        }

        // 执行删除
        statement.executeUpdate("delete from user;");
        resultSet = statement.executeQuery("select * from user;");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            System.out.println(String.format("id: %d, name: %s", id, name));
        }
    }
}
