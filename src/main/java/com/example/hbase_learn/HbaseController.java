package com.example.hbase_learn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @BelongsProject: hbase_learn
 * @BelongsPackage: com.example.hbase_learn
 * @ClassName HbaseController
 * @Author: RookieFu
 * @CreateTime: 2022-08-20  23:59
 * @UpdateTime: 2022-08-20  23:59
 * @Description: TODO
 * @Version:
 */
@Slf4j
@RestController
public class HbaseController {
    @Autowired
    private HbaseService hbaseService;

    @RequestMapping("hbaseCreate")
    public String create(){
        try {
            hbaseService.createTable();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "hbase创建表成功";
    }
}
