package com.kilogate.hi.java.jdbc;

import lombok.Cleanup;

import java.sql.*;

/**
 * JDBCUsage
 *
 * @author kilogate
 * @create 2023/7/14 23:14
 **/
public class JDBCUsage {
    public static void main(String[] args) throws SQLException {
        usage4();
    }

    private static void usage1() throws SQLException {
        // 获取连接
        @Cleanup Connection connection = getConnection();

        // 新建语句
        @Cleanup Statement statement = connection.createStatement();

        // 执行插入
        int updateRows = statement.executeUpdate("insert into user values (1, 'AAA');");
        updateRows = statement.executeUpdate("insert into user values (2, 'BBB');");
        updateRows = statement.executeUpdate("insert into user values (3, 'CCC');");

        // 执行查询
        @Cleanup ResultSet resultSet = statement.executeQuery("select * from user;");
        printResultSet(resultSet);

        // 执行更新
        updateRows = statement.executeUpdate("update user set name = 'A' where id = 1;");

        // 使用 PreparedStatement
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?;");
        preparedStatement.setInt(1, 1);
        resultSet = preparedStatement.executeQuery();
        printResultSet(resultSet);

        // 执行删除
        updateRows = statement.executeUpdate("delete from user;");
        resultSet = statement.executeQuery("select * from user;");
        printResultSet(resultSet);
    }

    private static void usage2() throws SQLException {
        @Cleanup Connection connection = getConnection();
        connection.setAutoCommit(false);

        @Cleanup Statement statement = connection.createStatement();
        statement.executeUpdate("insert into user values (1, 'AAA');");
        statement.executeUpdate("insert into user values (2, 'BBB');");
        statement.executeUpdate("insert into user values (3, 'CCC');");

        // 事务未提交，数据库中无数据，但是此处可以查到
        @Cleanup ResultSet resultSet = statement.executeQuery("select * from user;");
        printResultSet(resultSet);

        // 提交事务后，数据库中有数据
        connection.commit();

        clearTable(connection);
    }

    private static void usage3() throws SQLException {
        @Cleanup Connection connection = getConnection();
        connection.setAutoCommit(false);

        // 批处理
        @Cleanup Statement statement = connection.createStatement();
        statement.addBatch("insert into user values (1, 'AAA');");
        statement.addBatch("insert into user values (2, 'BBB');");
        statement.addBatch("insert into user values (3, 'CCC');");
        int[] res = statement.executeBatch();
        connection.commit();

        @Cleanup ResultSet resultSet = statement.executeQuery("select * from user;");
        printResultSet(resultSet);

        clearTable(connection);
    }

    private static void usage4() throws SQLException {
        @Cleanup Connection connection = getConnection();
        @Cleanup Statement statement = connection.createStatement();
        statement.executeUpdate("insert into user values (1, 'AAA');");
        statement.executeUpdate("insert into user values (2, 'BBB');");
        statement.executeUpdate("insert into user values (3, 'CCC');");

        // 演示SQL注入
        String name = "hello' or 'a' = 'a";
        String sql = String.format("select * from user where name = '%s';", name);
        @Cleanup ResultSet resultSet = statement.executeQuery(sql);
        printResultSet(resultSet);

        // 避免SQL注入
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select * from user where name = ?;");
        preparedStatement.setString(1, name);
        resultSet = preparedStatement.executeQuery();
        printResultSet(resultSet);

        clearTable(connection);
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "root";
        String passport = "kilogate";
        return DriverManager.getConnection(url, user, passport);
    }

    private static void clearTable(Connection connection) throws SQLException {
        @Cleanup Statement statement = connection.createStatement();
        statement.executeUpdate("delete from user;");
        if (connection.getAutoCommit() == false) {
            connection.commit();
        }
    }

    private static void printResultSet(ResultSet resultSet) throws SQLException {
        boolean hasData = false;
        while (resultSet.next()) {
            hasData = true;

            int id = resultSet.getInt(1); // 根据 index 取
            String name = resultSet.getString("name"); // 根据 column 取
            System.out.println(String.format("id: %d, name: %s", id, name));
        }

        if (!hasData) {
            System.out.println("<blank>");
        }

        System.out.println("--------------------------------------------------");
    }
}
