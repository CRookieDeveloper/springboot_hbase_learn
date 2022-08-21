package com.example.hbase_learn;

/**
 * @BelongsProject: hbase_learn
 * @BelongsPackage: com.example.hbase_learn
 * @ClassName HbaseService
 * @Author: RookieFu
 * @CreateTime: 2022-08-20  23:46
 * @UpdateTime: 2022-08-20  23:46
 * @Description: TODO
 * @Version:
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class HbaseService {

    @Autowired
    private Connection connection;

    public void createTable() throws IOException {
        //获取管理员对象，用于创建表、删除表等
        Admin admin = connection.getAdmin();

        // 表名
        String TABLE_NAME = "WATER_BILL";
        // 列蔟名
        String COLUMN_FAMILY = "C1";

        // 1. 判断表是否存在
        if(admin.tableExists(TableName.valueOf(TABLE_NAME))) {
            return;
        }

        // 2. 构建表描述构建器
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(TABLE_NAME));

        // 3. 构建列蔟描述构建器
        ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(COLUMN_FAMILY));

        // 4. 构建列蔟描述
        ColumnFamilyDescriptor columnFamilyDescriptor = columnFamilyDescriptorBuilder.build();

        // 5. 构建表描述
        // 添加列蔟
        tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
        TableDescriptor tableDescriptor = tableDescriptorBuilder.build();

        // 6. 创建表
        admin.createTable(tableDescriptor);

        //关闭admin
        admin.close();

    }


}

