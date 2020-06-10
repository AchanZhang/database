package com.achanzhang.mydatabase.achandatabase.mybatis;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * Created by zyc on 2020-06-10.
 */
public class OverwriteXmlPlugin extends PluginAdapter{

    @Override
    public boolean validate(List<String> warnings){
        return true;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap,IntrospectedTable introspectedTable){
        sqlMap.setMergeable(false);
        return super.sqlMapGenerated(sqlMap,introspectedTable);
    }
}
