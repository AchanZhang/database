package com.achanzhang.mydatabase.achandatabase.controller;

import com.achanzhang.mydatabase.achandatabase.beans.entry.StudentPO;
import com.achanzhang.mydatabase.achandatabase.dao.StudentDao;
import com.achanzhang.mydatabase.achandatabase.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zyc
 * @Date: 2020/6/10
 */
@RestController
@RequestMapping("/achan/findStudent")
public class StudentController {
    @Autowired
    private StudentDao studentDao;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public StudentPO findStudent(@PathVariable() Integer id) {
        System.out.println("id:" + id);
        return studentDao.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String find() {
        System.out.println("hello");
        return "hello";
    }
}
