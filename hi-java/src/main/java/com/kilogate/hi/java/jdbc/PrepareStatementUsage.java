package com.kilogate.hi.java.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * PrepareStatementUsage
 *
 * @author kilogate
 * @create 2021/1/3 16:21
 **/
public class PrepareStatementUsage {
    public static void main(String[] args) throws SQLException {
        // 启用 jdbc 跟踪
        PrintWriter printWriter = new PrintWriter(System.out);
        DriverManager.setLogWriter(printWriter);

        // 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "";
        String passport = "";
        Connection connection = DriverManager.getConnection(url, user, passport);

        // 预备语句
        String sql = "SELECT * FROM Authors WHERE Author_Id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 绑定变量
        preparedStatement.setString(1, "2");

        // 执行查询
        ResultSet resultSet = preparedStatement.executeQuery();

        // 打印结果集
        ExecSql.printResultSet(sql, resultSet);
    }
}
