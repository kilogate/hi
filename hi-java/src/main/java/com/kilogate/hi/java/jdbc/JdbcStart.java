package com.kilogate.hi.java.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC 起步
 *
 * @author kilogate
 * @create 2021/1/2 21:51
 **/
public class JdbcStart {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 启用 jdbc 跟踪
        PrintWriter printWriter = new PrintWriter(System.out);
        DriverManager.setLogWriter(printWriter);

        // 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "";
        String passport = "";
        Connection connection = DriverManager.getConnection(url, user, passport);

        // 新建语句
        Statement statement = connection.createStatement();

        // 执行更新
        statement.executeUpdate("UPDATE user SET salary = 2 WHERE id = 1;");

        // 执行查询
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user LIMIT 100;");

        // 打印结果集
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            long salary = resultSet.getLong(4);

            System.out.println(String.format("%s,%s,%s,%s", id, firstName, lastName, salary));
        }
    }
}
