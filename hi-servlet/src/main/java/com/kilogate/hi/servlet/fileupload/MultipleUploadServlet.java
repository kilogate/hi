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
import java.util.Collection;

/**
 * MultipleUploadServlet
 *
 * @author fengquanwei
 * @create 2021/12/5 00:38
 **/
@WebServlet("/multipleUploadServlet")
@MultipartConfig
public class MultipleUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        Collection<Part> parts = req.getParts();

        for (Part part : parts) {
            if (part.getContentType() != null) {
                // save file to disk
                String filename = FileUploadUtil.getFilename(part);
                if (filename != null && !filename.isEmpty()) {
                    part.write(getServletContext().getRealPath("/WEB-INF") + "/" + filename);
                    writer.println(String.format("<br/>Uploaded filename: %s, size: %s", filename, part.getSize()));
                }
            } else {
                String partName = part.getName();
                String fieldValue = req.getParameter(partName);
                writer.println(String.format("<br/>%s: %s", partName, fieldValue));
            }
        }
    }
}
