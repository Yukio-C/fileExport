package com.example.demo.filexport;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.example.demo.Enum.FileType;
import org.apache.ibatis.reflection.ReflectorFactory;

import java.util.Arrays;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


public class FileExportFactory {
    private static final Map<FileType, FileExport> CACHE;
    static {
        Map<String, FileExport> beansOfType = SpringUtil.getBeansOfType(FileExport.class);
        CACHE = beansOfType.values().stream()
                .collect(Collectors.toMap(
                        FileExport::getFileType,
                        Function.identity(),
                        (v1,v2) -> v2
                ));


//        Set<Class<?>> classes = ClassUtil.scanPackage("com.example.demo.filexport",
//                FileExport.class::isAssignableFrom);
//
//        CACHE = classes.stream()
//                .filter(ClassUtil::isNormalClass)
//                .map(ReflectUtil::newInstance)
//                .map(obj -> (FileExport) obj)
//                .collect(Collectors.toMap(
//                        FileExport::getFileType,
//                        Function.identity(),
//                        (v1, v2) -> v2
//                ));

//        // 使用静态代码块初始化缓存，确保在类加载时就创建所有文件导出器实例
//        // 遍历所有文件类型枚举值
//        CACHE = Arrays.stream(FileType.values())
//                .map(fileType -> {// 将每个文件类型映射为Pair<FileType, FileExport>键值对
//                    String className = fileType.getClassName();// 获取文件类型对应的类名
//                    try {
//                        Class<?> clazz = Class.forName(className);// 通过反射加载类
//                        FileExport fileExport = (FileExport) clazz.getConstructor().newInstance();
//                        return new Pair<>(fileType, fileExport);// 返回文件类型和导出器的键值对
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                }).collect(Collectors.toMap( // 收集为Map，键为文件类型
//                        Pair::getKey,
//                        Pair::getValue,
//                        (v1, v2) -> v2
//                ));

    }

    public static FileExport getFileExport(FileType fileType) {
        return CACHE.get(fileType);
    }

    public static FileExport getFileExport(String type) {
        FileType fileType = FileType.from(type);
        return CACHE.get(fileType);
    }
}