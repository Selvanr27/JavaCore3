package com.ani.springdatajdbc.controller;

import com.ani.springdatajdbc.domain.Emp;
import com.ani.springdatajdbc.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/db")
@RestController
public class DbController {

    @Autowired
    private DbService service;

    @PostMapping // POST - http://localhost:8080/db
    public String save(@RequestBody Emp emp) {
        service.saveOperation(
                emp.getId(),
                emp.getName(),
                emp.getDob(),
                emp.getManager()
        );
        return "Employee Saved";
    }
}