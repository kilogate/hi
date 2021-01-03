package com.kilogate.hi.java.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC 测试
 *
 * @author kilogate
 * @create 2021/1/3 09:27
 **/
public class JdbcTest {
    public static void main(String[] args) throws SQLException {
        // 启用 jdbc 跟踪
        PrintWriter printWriter = new PrintWriter(System.out);
        DriverManager.setLogWriter(printWriter);

        // 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "";
        String passport = "";
        Connection connection = DriverManager.getConnection(url, user, passport);

        // 获取元数据
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        int maxStatements = databaseMetaData.getMaxStatements();
        System.out.println(maxStatements);

        int sqlStateType = databaseMetaData.getSQLStateType();
        System.out.println(sqlStateType);
    }
}
