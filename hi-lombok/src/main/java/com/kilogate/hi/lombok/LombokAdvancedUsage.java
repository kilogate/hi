package com.kilogate.hi.lombok;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.val;

import java.io.*;
import java.util.ArrayList;

/**
 * LombokAdvancedUsage
 *
 * @author kilogate
 * @create 2023/7/14 21:30
 **/
public class LombokAdvancedUsage {
    private static void cleanup() throws IOException {
        @Cleanup InputStream in = new FileInputStream("");
        @Cleanup OutputStream out = new FileOutputStream("");
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) break;
            out.write(b, 0, r);
        }
    }

    @Synchronized
    private static void synchronize() {
        System.out.println();
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    public String utf8ToString(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }
}
