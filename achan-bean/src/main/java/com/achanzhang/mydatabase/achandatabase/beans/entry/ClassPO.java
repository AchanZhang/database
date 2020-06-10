package com.achanzhang.mydatabase.achandatabase.beans.entry;

import io.swagger.annotations.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * class
 */
@ApiModel(value = "ClassPO", description = "null")
public class ClassPO {
    @NotNull
    @Range(min = 1)
    @ApiModelProperty(value = "班级编号", required = true)
    private Integer classNumber;

    @ApiModelProperty(value = "班级名")
    private String className;

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }
}