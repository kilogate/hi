package com.kilogate.hi.servlet.fileupload;

import javax.servlet.http.Part;

/**
 * FileuploadUtil
 *
 * @author kilogate
 * @create 2021/12/5 00:39
 **/
public class FileUploadUtil {
    public static String getFilename(Part part) {
        if (part == null) {
            return null;
        }

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
