package com.achanzhang.mydatabase.achandatabase.beans.entry;

import io.swagger.annotations.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * student
 */
@ApiModel(value = "StudentPO", description = "null")
public class StudentPO {
    @NotNull
    @Range(min = 1)
    @ApiModelProperty(value = "学号", required = true)
    private Integer studentNumber;

    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "班级号")
    private Integer classNumber;

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }
}