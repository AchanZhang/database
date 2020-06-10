package com.achanzhang.mydatabase.achandatabase.mybatis;


import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * @since 1.9.10
 * @version 1.9.10
 * @作者：niexiaohui
 * @创建时间：2016年11月22日
 * @修改记录：
 */
public class MyBatisGenerator{

    public static void main(String[] args){
        try{
            List<String> warnings = new ArrayList<>();
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("mybatis-generator.xml");
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(is);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            org.mybatis.generator.api.MyBatisGenerator myBatisGenerator = new org.mybatis.generator.api.MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

