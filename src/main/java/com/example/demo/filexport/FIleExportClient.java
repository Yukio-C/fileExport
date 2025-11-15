package com.example.demo.filexport;

import cn.hutool.core.io.FileTypeUtil;
import com.example.demo.Enum.FileType;

import java.io.File;

public class FIleExportClient {
    public static void export2Db(String filepath) {
        String type = FileTypeUtil.getType(new File(filepath));
        export2Db(filepath, type);
    }

    public static void export2Db(File file) {
        String path = file.getAbsolutePath();
        export2Db(path);
    }

    public static void export2Db(String filepath, String type) {
        FileExport fileExport = FileExportFactory.getFileExport(type);
        fileExport.export2Db(filepath);
    }

    public static void export2Db(String filepath, FileType type) {
        FileExport fileExport = FileExportFactory.getFileExport(type);
        fileExport.export2Db(filepath);
    }

}
