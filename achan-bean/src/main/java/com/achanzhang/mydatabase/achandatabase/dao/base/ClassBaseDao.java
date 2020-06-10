package com.achanzhang.mydatabase.achandatabase.dao.base;

import com.achanzhang.mydatabase.achandatabase.beans.entry.ClassPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ClassBaseDao {
    @Delete({
        "delete from class",
        "where class_number = #{classNumber,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer classNumber);

    @Insert({
        "insert into class (class_name)",
        "values (#{className,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="classNumber")
    int insert(ClassPO record);

    int insertSelective(ClassPO record);

    @Select({
        "select",
        "class_number, class_name",
        "from class",
        "where class_number = #{classNumber,jdbcType=INTEGER}"
    })
    @ResultMap("com.achanzhang.mydatabase.achandatabase.dao.base.ClassBaseDao.BaseResultMap")
    ClassPO selectByPrimaryKey(Integer classNumber);

    int updateByPrimaryKeySelective(ClassPO record);

    @Update({
        "update class",
        "set class_name = #{className,jdbcType=VARCHAR}",
        "where class_number = #{classNumber,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ClassPO record);
}