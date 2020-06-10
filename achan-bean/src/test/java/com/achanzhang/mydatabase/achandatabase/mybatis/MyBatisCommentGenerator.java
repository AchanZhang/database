package com.achanzhang.mydatabase.achandatabase.mybatis;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * 描述：
 * @since 1.9.10
 * @version 1.9.10
 * @作者：niexiaohui
 * @创建时间：2016年11月22日
 * @修改记录：
 */
public class MyBatisCommentGenerator implements CommentGenerator{
    private Properties properties;
    private Properties systemPro;
    private boolean suppressDate;
    private boolean suppressAllComments;
    private String currentDateStr;

    public MyBatisCommentGenerator() {
        super();
        properties = new Properties();
        systemPro = System.getProperties();
        suppressDate = false;
        suppressAllComments = false;
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        // add no file level comments by default
        return;
    }

    /**
     * Adds a suitable comment to warn users that the element was generated, and
     * when it was generated.
     */
    @Override
    public void addComment(XmlElement xmlElement) {
        return;
    }

    @Override
    public void addRootComment(XmlElement rootElement) {
        // add no document level comments by default
        return;
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);

        suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));

        suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
    }

    /**
     * This method adds the custom javadoc tag for. You may do nothing if you do
     * not wish to include the Javadoc tag - however, if you do not include the
     * Javadoc tag then the Java merge capability of the eclipse plugin will
     * break.
     *
     * @param javaElement
     *            the java element
     */
    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
        javaElement.addJavaDocLine(" *");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        if (markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }
        String s = getDateString();
        if (s != null) {
            sb.append(' ');
            sb.append(s);
        }
        javaElement.addJavaDocLine(sb.toString());
    }

    /**
     * This method returns a formated date string to include in the Javadoc tag
     * and XML comments. You may return null if you do not want the date in
     * these documentation elements.
     *
     * @return a string representing the current timestamp, or null
     */
    protected String getDateString() {
        String result = null;
        if (!suppressDate) {
            result = currentDateStr;
        }
        return result;
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();

        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append(" ");
        sb.append(getDateString());
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        innerClass.addJavaDocLine(" */");
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerEnum.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerEnum.addJavaDocLine(sb.toString().replace("\n", " "));
        innerEnum.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field,IntrospectedTable introspectedTable,IntrospectedColumn introspectedColumn){
        if(suppressAllComments){
            return;
        }
        String require = "required = true";
        if(!introspectedColumn.isNullable()){
            if("String".equals(introspectedColumn.getFullyQualifiedJavaType().getShortName())){
                field.addJavaDocLine("@NotEmpty");
                field.addJavaDocLine("@Length(min = 1, max = " + introspectedColumn.getLength() + ")");
            }else if("Integer".equals(introspectedColumn.getFullyQualifiedJavaType().getShortName())){
                field.addJavaDocLine("@NotNull");
                field.addJavaDocLine("@Range(min = 1)");
            }else if("Long".equals(introspectedColumn.getFullyQualifiedJavaType().getShortName())){
                field.addJavaDocLine("@NotNull");
                field.addJavaDocLine("@Range(min = 1)");
            }else{
                field.addJavaDocLine("@NotNull");
            }
        }else{
            require = "";
        }
        if(StringUtils.isNotEmpty(introspectedColumn.getRemarks())){
            if(StringUtils.isNotEmpty(require)){
                field.addJavaDocLine("@ApiModelProperty(value = \"" + introspectedColumn.getRemarks() + "\", " + require + ")");
            }else{
                field.addJavaDocLine("@ApiModelProperty(value = \"" + introspectedColumn.getRemarks() + "\")");
            }
        }
        if("createBy".equals(introspectedColumn.getJavaProperty())){
            field.addJavaDocLine("@UserName(value = \"createName\")");
            field.addJavaDocLine("@JsonSerialize(using = UserNameSerializer.class)");
        }
        if("updateBy".equals(introspectedColumn.getJavaProperty())){
            field.addJavaDocLine("@UserName(value = \"updateName\")");
            field.addJavaDocLine("@JsonSerialize(using = UserNameSerializer.class)");
        }
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        field.addJavaDocLine(sb.toString().replace("\n", " "));
        field.addJavaDocLine(" */");
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        topLevelClass.addImportedType("io.swagger.annotations.*");
        topLevelClass.addImportedType("org.hibernate.validator.constraints.Range");
        topLevelClass.addImportedType("javax.validation.constraints.NotNull");
        topLevelClass.addImportedType("javax.validation.constraints.NotEmpty");
        topLevelClass.addImportedType("org.hibernate.validator.constraints.Length");

        boolean isUserName = false;
        for(IntrospectedColumn column : introspectedTable.getBaseColumns()){
            if("createBy".equals(column.getJavaProperty()) || "updateBy".equals(column.getJavaProperty())){
                isUserName = true;
                break;
            }
        }
        if(isUserName){
            topLevelClass.addImportedType("com.fasterxml.jackson.databind.annotation.JsonSerialize");
            topLevelClass.addImportedType("com.achanzhang.mydatabase.annotation.UserName");
            topLevelClass.addImportedType("com.achanzhang.mydatabase.common.serializer.UserNameSerializer");
        }

        StringBuilder sb = new StringBuilder();
        topLevelClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        topLevelClass.addJavaDocLine(sb.toString().replace("\n", " "));
        sb.setLength(0);
        sb.append(" * @author ");
        sb.append(systemPro.getProperty("user.name"));
        sb.append(" ");
        sb.append(currentDateStr);
        topLevelClass.addJavaDocLine(" */");
        topLevelClass.addJavaDocLine("@ApiModel(value = \""+introspectedTable.getFullyQualifiedTable().getDomainObjectName()+"\", description = \""+introspectedTable.getRemarks()+"\")");
    }

    @Override
    public void addGeneralMethodAnnotation(Method method,IntrospectedTable introspectedTable,Set<FullyQualifiedJavaType> imports){

    }

    @Override
    public void addGeneralMethodAnnotation(Method method,IntrospectedTable introspectedTable,IntrospectedColumn introspectedColumn,Set<FullyQualifiedJavaType> imports){

    }

    @Override
    public void addFieldAnnotation(Field field,IntrospectedTable introspectedTable,Set<FullyQualifiedJavaType> imports){

    }

    @Override
    public void addFieldAnnotation(Field field,IntrospectedTable introspectedTable,IntrospectedColumn introspectedColumn,Set<FullyQualifiedJavaType> imports){

    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports){

    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        //        if (suppressAllComments) {
        //            return;
        //        }
        //        method.addJavaDocLine("/**");
        //        addJavadocTag(method, false);
        //        method.addJavaDocLine(" */");
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        //        if (suppressAllComments) {
        //            return;
        //        }
        //        method.addJavaDocLine("/**");
        //        StringBuilder sb = new StringBuilder();
        //        sb.append(" * ");
        //        sb.append(introspectedColumn.getRemarks());
        //        method.addJavaDocLine(sb.toString().replace("\n", " "));
        //        sb.setLength(0);
        //        sb.append(" * @return ");
        //        sb.append(introspectedColumn.getActualColumnName());
        //        sb.append(" ");
        //        sb.append(introspectedColumn.getRemarks());
        //        method.addJavaDocLine(sb.toString().replace("\n", " "));
        //        method.addJavaDocLine(" */");
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        //        if (suppressAllComments) {
        //            return;
        //        }
        //        method.addJavaDocLine("/**");
        //        StringBuilder sb = new StringBuilder();
        //        sb.append(" * ");
        //        sb.append(introspectedColumn.getRemarks());
        //        method.addJavaDocLine(sb.toString().replace("\n", " "));
        //        Parameter parm = method.getParameters().get(0);
        //        sb.setLength(0);
        //        sb.append(" * @param ");
        //        sb.append(parm.getName());
        //        sb.append(" ");
        //        sb.append(introspectedColumn.getRemarks());
        //        method.addJavaDocLine(sb.toString().replace("\n", " "));
        //        method.addJavaDocLine(" */");
    }



    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        sb.setLength(0);
        sb.append(" * @author ");
        sb.append(systemPro.getProperty("user.name"));
        sb.append(" ");
        sb.append(currentDateStr);
        innerClass.addJavaDocLine(" */");
    }
}

