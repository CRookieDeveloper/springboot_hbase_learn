package com.example.hbase_learn;

/**
 * @BelongsProject: hbase_learn
 * @BelongsPackage: com.example.hbase_learn
 * @ClassName HbaseConfiguration
 * @Author: RookieFu
 * @CreateTime: 2022-08-20  23:42
 * @UpdateTime: 2022-08-20  23:42
 * @Description: TODO
 * @Version:
 */

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.util.function.Supplier;

@Configuration
public class HbaseConfiguration {

    @Value("${hbase.defaults.for.version.skip}")
    private String skip;

    @Value("${hbase.zookeeper.property.clientPort}")
    private String clientPort;

    @Value("${hbase.zookeeper.quorum}")
    private String quorum;

    @Value("${hbase.master}")
    private String master;

    @Bean
    public org.apache.hadoop.conf.Configuration config() {
        //这里启动会打印异常java.io.FileNotFoundException: java.io.FileNotFoundException: HADOOP_HOME and hadoop.home.dir are unset.
        //该异常不影响应用，源码里面是window需要配置对应版本的hadoop客户端才不抛出这个错误，这个不影响程序运行，linux服务器不会走这个逻辑，所以不管就行了
        org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
        config.set("hbase.defaults.for.version.skip", skip);
        config.set("hbase.zookeeper.property.clientPort", clientPort);
        config.set("hbase.zookeeper.quorum", quorum);
        config.set("hbase.master", master);
        return config;
    }

    @Bean
    public Supplier<Connection> hbaseConnSupplier() {
        return () -> {
            try {
                return connection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Bean
    @Scope(value = "prototype")
    public Connection connection() throws IOException {
        return ConnectionFactory.createConnection(config());
    }
}

