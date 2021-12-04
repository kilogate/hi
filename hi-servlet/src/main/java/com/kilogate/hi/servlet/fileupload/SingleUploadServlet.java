package com.kilogate.hi.servlet.fileupload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * SingleUploadServlet
 *
 * @author kilogate
 * @create 2021/12/5 00:16
 **/
@WebServlet(urlPatterns = "/singleUploadServlet")
@MultipartConfig(maxFileSize = -1, maxRequestSize = -1)
public class SingleUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // save uploaded file to WEB-INF
        Part part = req.getPart("filename");
        String filename = getFilename(part);
        if (filename != null && !filename.isEmpty()) {
            part.write(getServletContext().getRealPath("/WEB-INF") + "/" + filename);
        }

        // get author
        String author = req.getParameter("author");

        // write to browser
        PrintWriter writer = resp.getWriter();
        writer.println(String.format("Uploaded filename: %s, size: %s, author: %s", filename, part.getSize(), author));
    }

    private String getFilename(Part part) {
        String contentDispositionHeader = part.getHeader("content-disposition");

        String[] elements = contentDispositionHeader.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }
}
