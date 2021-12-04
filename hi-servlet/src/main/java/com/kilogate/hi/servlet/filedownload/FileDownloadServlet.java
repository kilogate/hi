package com.kilogate.hi.servlet.filedownload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * FileDownloadServlet
 *
 * @author kilogate
 * @create 2021/12/5 00:51
 **/
@WebServlet("/fileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletContext().getRealPath("/WEB-INF");
        File file = new File(path, "MDS_2953.jpg");
        if (file.exists()) {
            resp.setContentType("image/jpeg");
            resp.addHeader("Content-Disposition", "attachment; filename=MDS_2953.jpg");

            byte[] buffer = new byte[1024];

            try (FileInputStream fis = new FileInputStream(file);
                 BufferedInputStream bis = new BufferedInputStream(fis)) {
                ServletOutputStream os = resp.getOutputStream();

                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
