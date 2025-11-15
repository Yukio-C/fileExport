package com.example.demo.filexport;

import com.example.demo.Enum.FileType;

public interface FileExport {
    void export2Db(String fileName);
    FileType getFileType();
}