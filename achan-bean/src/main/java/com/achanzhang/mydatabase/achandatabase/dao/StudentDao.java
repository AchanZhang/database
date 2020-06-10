package com.achanzhang.mydatabase.achandatabase.dao;

import com.achanzhang.mydatabase.achandatabase.dao.base.StudentBaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * @Author: zyc
 * @Date: 2020/6/10
 */
@Mapper
public interface StudentDao extends StudentBaseDao {
}
