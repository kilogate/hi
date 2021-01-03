package com.kilogate.hi.java.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 执行 SQL
 *
 * @author kilogate
 * @create 2021/1/3 10:00
 **/
public class ExecSql {
    public static void main(String[] args) throws IOException, SQLException {
        doExecSql("hi-java/src/main/resources/sql/init.sql");
    }

    private static void doExecSql(String sqlFilePath) throws SQLException, IOException {
        // 获取所有 sql 语句
        List<String> lines = Files.readAllLines(Paths.get(sqlFilePath), StandardCharsets.UTF_8);

        if (lines == null || lines.size() == 0) {
            System.out.println("no sql found");
            return;
        }

        // 启用 jdbc 跟踪
        PrintWriter printWriter = new PrintWriter(System.out);
        DriverManager.setLogWriter(printWriter);

        // 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8";
        String user = "";
        String passport = "";
        Connection connection = DriverManager.getConnection(url, user, passport);

        // 新建语句
        Statement statement = connection.createStatement();

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
                printResultSet(sql, statement.getResultSet());
            } else {
                // 打印更新数
                int updateCount = statement.getUpdateCount();
                System.out.println("==================== update result ====================");
                System.out.println("sql: " + sql);
                System.out.println("updateCount: " + updateCount);
            }
        }
    }

    private static void printResultSet(String sql, ResultSet resultSet) throws SQLException {
        System.out.println("==================== query result ====================");
        System.out.println("sql: " + sql);
        System.out.println("resultSet: ");

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

            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();

        // 打印结果
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) {
                    System.out.print(", ");
                }

                System.out.print(resultSet.getString(i));
            }
            System.out.println();
        }
    }
}
