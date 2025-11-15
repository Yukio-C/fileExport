package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;

import com.example.demo.filexport.FIleExportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileExportController {

    @Value("${file.save.dir}")
    private String saveDir;

    @PostMapping("/upload")
    public String uploadAndExport(MultipartFile file) {
        // 保存文件
        if (Objects.isNull(file) || file.isEmpty()) {
            throw new IllegalArgumentException("文件为空");
        }
        if (Objects.isNull(file) || file.isEmpty()) {
            throw new IllegalArgumentException("文件为空");
        }

        File saveFile = toSavePath(file.getOriginalFilename()).toFile();
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FIleExportClient.export2Db(saveFile.getAbsolutePath());
        // 保存文件, 将文件名和保存文件名等存入数据库
        return saveFile.getAbsolutePath();



    }
    private Path toSavePath(String originalFilename) {
        // asdfwqer4324rew
        String filename = IdUtil.fastSimpleUUID();
        // 拓展名/后缀 .json .csv
        String extName = FileUtil.extName(originalFilename);
        String saveFilename = filename + StrUtil.DOT + extName;

        return Paths.get(saveDir, saveFilename);
    }

}
