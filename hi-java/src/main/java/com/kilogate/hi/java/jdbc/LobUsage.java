package com.kilogate.hi.java.jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 大对象的用法
 *
 * @author kilogate
 * @create 2021/1/3 16:59
 **/
public class LobUsage {
    public static void main(String[] args) throws SQLException, IOException {
        // 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "";
        String passport = "";
        Connection connection = DriverManager.getConnection(url, user, passport);

        String ISBN = "1";

        // 写入二进制大对象
        writeBlob(connection, ISBN);

        // 读取二进制大对象
        readBlob(connection, ISBN);
    }

    private static void writeBlob(Connection connection, String ISBN) throws SQLException, IOException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Covers VALUES (?, ?)");
        preparedStatement.setString(1, ISBN);
        preparedStatement.setBlob(2, new FileInputStream("hi-java/src/main/resources/image/onepiece.jpg"));

        int update = preparedStatement.executeUpdate();
        System.out.println(update);
    }

    private static void readBlob(Connection connection, String ISBN) throws SQLException, IOException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Covers WHERE ISBN = ?");
        preparedStatement.setString(1, ISBN);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
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
}
