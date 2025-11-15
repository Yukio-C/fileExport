package com.example.demo.filexport;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.example.demo.Enum.FileType;
import com.example.demo.domain.dto.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;


import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JsonExport extends  AbstractFileExport{

    public JsonExport(UserService userService) {
        super(userService);
    }

    @Override
    public FileType getFileType() {
        return FileType.JSON;
    }

    @Override
    protected List<User> readFile(String filPath) {
        //知道文件地址以及文件类型，读取json转换为user对象，user对象不止一个
        String json = FileUtil.readUtf8String(new File(filPath));
        TypeReference<List<User>> userListType = new TypeReference<List<User>>() {};

        return JSONUtil.toBean(json, userListType, false);

    }

    @Override
    boolean needProcessData() {
        return true;
    }

    @Override
    protected List<User> processData(List<User> users) {
        //去除json数据可能存在的前后空格
        return users.stream()
                .map(this::processData)
                .collect(Collectors.toList());
    }


    private User processData(User user) {
        User processedUser = new User();
        processedUser.setUsername(user.getUsername().trim());
        processedUser.setName(user.getName().trim());

        return processedUser;
    }

}