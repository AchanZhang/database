package com.achanzhang.mydatabase.achandatabase.dao.base;

import com.achanzhang.mydatabase.achandatabase.beans.entry.StudentPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface StudentBaseDao {
    @Delete({
        "delete from student",
        "where student_number = #{studentNumber,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer studentNumber);

    @Insert({
        "insert into student (student_name, class_number)",
        "values (#{studentName,jdbcType=VARCHAR}, #{classNumber,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="studentNumber")
    int insert(StudentPO record);

    int insertSelective(StudentPO record);

    @Select({
        "select",
        "student_number, student_name, class_number",
        "from student",
        "where student_number = #{studentNumber,jdbcType=INTEGER}"
    })
    @ResultMap("com.achanzhang.mydatabase.achandatabase.dao.base.StudentBaseDao.BaseResultMap")
    StudentPO selectByPrimaryKey(Integer studentNumber);

    int updateByPrimaryKeySelective(StudentPO record);

    @Update({
        "update student",
        "set student_name = #{studentName,jdbcType=VARCHAR},",
          "class_number = #{classNumber,jdbcType=INTEGER}",
        "where student_number = #{studentNumber,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(StudentPO record);
}