package com.example.demo.filexport;

import com.example.demo.Enum.FileType;
import com.example.demo.domain.dto.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVExport extends  AbstractFileExport{

    public CSVExport(UserService userService) {
        super(userService);
    }

    @Override
    public FileType getFileType() {
        return FileType.CSV;
    }

    @Override
    protected List<User> readFile(String filePath) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    User user = new User();

                    user.setUsername(data[1].trim());
                    user.setName(data[2].trim());
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    protected boolean needProcessData() {
        return false;
    }




}