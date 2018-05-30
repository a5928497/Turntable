package com.yukoon.turntablegames.utils;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
    private final static String JPG  = ".jpg";
    private final static String PNG  = ".png";
    private final static String JEPG  = ".jepg";


    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static boolean isImg(String fileName) {
        fileName = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        if (fileName.equalsIgnoreCase(JPG) || fileName.equalsIgnoreCase(PNG) || fileName.equalsIgnoreCase(JEPG)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isImg("sfsdfs.pNg"));
    }
}
