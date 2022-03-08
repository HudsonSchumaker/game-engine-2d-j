package com.schumakerteam.alpha.common;

import java.io.File;

/**
 * @author Hudson Schumaker
 */
public final class FileUtils {

    private FileUtils() {
    }

    public static String getClearName(File file) {
        return file.getName().split("\\.")[0];
    }

    public static String getClearName(String path) {
        return getClearName(new File(path));
    }

    public static String getName(String path) {
        File file = new File(path);
        return file.getName();
    }

    public static String getClearPath(String path) {
        int pos = path.lastIndexOf(System.getProperty("file.separator"));
        return path.substring(0, pos + 1);
    }

    public static String getFileExtension(File file) {
        return getFileExtension(file.getAbsolutePath());
    }

    public static String getFileExtension(String fileName) {
        String[] ext = fileName.split("\\.");
        int size = ext.length;
        String result = "";
        if (size > 1) {
            result = ext[size - 1];
        }
        return result.toLowerCase();
    }
}
