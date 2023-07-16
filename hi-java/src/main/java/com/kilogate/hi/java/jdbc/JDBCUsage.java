package com.kilogate.hi.java.jdbc;

import lombok.Cleanup;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

/**
 * JDBCUsage
 *
 * @author kilogate
 * @create 2023/7/14 23:14
 **/
public class JDBCUsage {
    public static void main(String[] args) throws SQLException, IOException {
        blob();
    }

    /**
     * 执行SQL文件
     *
     * @throws SQLException
     * @throws IOException
     */
    private static void init() throws SQLException, IOException {
        // 获取所有 sql 语句
        List<String> lines = Files.readAllLines(Paths.get("hi-java/src/main/resources/sql/init.sql"), StandardCharsets.UTF_8);
        if (lines == null || lines.size() == 0) {
            System.out.println("no sql found");
            return;
        }

        // 启用 jdbc 跟踪
        PrintWriter printWriter = new PrintWriter(System.out);
        DriverManager.setLogWriter(printWriter);

        // 获取连接
        @Cleanup Connection connection = getConnection();

        // 新建语句
        @Cleanup Statement statement = connection.createStatement();

        // 遍历并执行 SQL
        for (String sql : lines) {
            if (sql == null || sql.length() == 0) {
                continue;
            }

            // 移除结尾的分号
            if (sql.endsWith(";")) {
                sql = sql.substring(0, sql.length() - 1);
            }

            // 执行 sql
            boolean isResult = statement.execute(sql);

            if (isResult) {
                // 打印结果集
                @Cleanup ResultSet resultSet = statement.getResultSet();
                printSelectResult(sql, resultSet);
            } else {
                // 打印更新数
                int updateCount = statement.getUpdateCount();
                printUpdateResult(sql, updateCount);
            }
        }
    }

    /**
     * 增删改查
     *
     * @throws SQLException
     */
    private static void crud() throws SQLException {
        // 获取连接
        @Cleanup Connection connection = getConnection();

        // 新建语句
        @Cleanup Statement statement = connection.createStatement();

        // 执行插入
        String sql = "INSERT INTO Authors VALUES ('101', '鲁迅', '近代');";
        int updateCount = statement.executeUpdate(sql);
        printUpdateResult(sql, updateCount);

        // 执行查询
        sql = "SELECT * FROM Authors;";
        @Cleanup ResultSet resultSet = statement.executeQuery(sql);
        printSelectResult(sql, resultSet);

        // 执行更新
        sql = "UPDATE Authors SET Name = '张爱玲' WHERE Author_Id = 101;";
        updateCount = statement.executeUpdate(sql);
        printUpdateResult(sql, updateCount);

        // 使用 PreparedStatement
        sql = "SELECT * FROM Authors WHERE Author_Id = ?;";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 101);
        resultSet = preparedStatement.executeQuery();
        printSelectResult(sql, resultSet);

        // 执行删除
        sql = "DELETE FROM Authors WHERE Author_Id = 101;";
        updateCount = statement.executeUpdate(sql);
        printUpdateResult(sql, updateCount);

        sql = "SELECT * FROM Authors;";
        resultSet = statement.executeQuery(sql);
        printSelectResult(sql, resultSet);
    }

    /**
     * 事务
     *
     * @throws SQLException
     */
    private static void trans() throws SQLException {
        @Cleanup Connection connection = getConnection();
        connection.setAutoCommit(false);

        @Cleanup Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO Authors VALUES ('101', '鲁迅', '近代');");
        statement.executeUpdate("INSERT INTO Authors VALUES ('102', '老舍', '近代');");
        statement.executeUpdate("INSERT INTO Authors VALUES ('103', '沈从文', '近代');");

        // 事务未提交，数据库中无数据，但是此处可以查到
        String sql = "SELECT * FROM Authors;";
        @Cleanup ResultSet resultSet = statement.executeQuery(sql);
        printSelectResult(sql, resultSet);

        // 提交事务后，数据库中有数据
        connection.commit();

        statement.executeUpdate("DELETE FROM Authors WHERE Author_Id IN ('101', '102', '103')");
        connection.commit();
    }

    /**
     * 批处理
     *
     * @throws SQLException
     */
    private static void batch() throws SQLException {
        @Cleanup Connection connection = getConnection();
        connection.setAutoCommit(false);

        @Cleanup Statement statement = connection.createStatement();
        statement.addBatch("INSERT INTO Authors VALUES ('101', '鲁迅', '近代');");
        statement.addBatch("INSERT INTO Authors VALUES ('102', '老舍', '近代');");
        statement.addBatch("INSERT INTO Authors VALUES ('103', '沈从文', '近代');");
        int[] res = statement.executeBatch();
        connection.commit();

        String sql = "SELECT * FROM Authors;";
        @Cleanup ResultSet resultSet = statement.executeQuery(sql);
        printSelectResult(sql, resultSet);

        statement.executeUpdate("DELETE FROM Authors WHERE Author_Id IN ('101', '102', '103')");
        connection.commit();
    }


    /**
     * SQL注入
     *
     * @throws SQLException
     */
    private static void inject() throws SQLException {
        @Cleanup Connection connection = getConnection();
        @Cleanup Statement statement = connection.createStatement();

        // 演示SQL注入
        String name = "hello' or 'a' = 'a";
        String sql = String.format("SELECT * FROM Authors WHERE Name = '%s';", name);
        @Cleanup ResultSet resultSet = statement.executeQuery(sql);
        printSelectResult(sql, resultSet);

        // 避免SQL注入
        sql = "SELECT * FROM Authors WHERE Name = ?;";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        resultSet = preparedStatement.executeQuery();
        printSelectResult(sql, resultSet);
    }

    /**
     * 元数据
     *
     * @throws SQLException
     */
    private static void meta() throws SQLException {
        @Cleanup Connection connection = getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        System.out.println(databaseMetaData.getMaxStatements());
        System.out.println(databaseMetaData.getSQLStateType());
    }

    /**
     * 大对象
     *
     * @throws SQLException
     * @throws IOException
     */
    private static void blob() throws SQLException, IOException {
        @Cleanup Connection connection = getConnection();

        // 写大对象
        String sql = "INSERT INTO Covers values (?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "ABC");
        preparedStatement.setBlob(2, new FileInputStream("hi-java/src/main/resources/image/onepiece.jpg"));
        int update = preparedStatement.executeUpdate();
        printUpdateResult(sql, update);


        // 读大对象
        sql = "SELECT * FROM Covers WHERE ISBN = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "ABC");
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Blob blob = resultSet.getBlob(2);
            InputStream binaryStream = blob.getBinaryStream();

            FileOutputStream fileOutputStream = new FileOutputStream("onepiece.jpg");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = binaryStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "root";
        String passport = "kilogate";
        return DriverManager.getConnection(url, user, passport);
    }

    private static void printUpdateResult(String sql, int updateCount) throws SQLException {
        System.out.println("==================== update result ====================");
        System.out.println("sql: " + sql);
        System.out.println("updateCount: " + updateCount);
    }

    private static void printSelectResult(String sql, ResultSet resultSet) throws SQLException {
        System.out.println("==================== select result ====================");

        if (sql != null && sql != "") {
            System.out.println("sql: " + sql);
            System.out.println("selectResult: ");
        }

        if (resultSet == null) {
            return;
        }

        // 获取元数据
        ResultSetMetaData metaData = resultSet.getMetaData();

        // 获取列数
        int columnCount = metaData.getColumnCount();

        // 打印列名
        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) {
                System.out.print(", ");
            }

            System.out.print("\t" + metaData.getColumnLabel(i));
        }
        System.out.println();

        // 打印结果
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) {
                    System.out.print(", ");
                }

                System.out.print("\t" + resultSet.getString(i));
            }
            System.out.println();
        }
    }
}
