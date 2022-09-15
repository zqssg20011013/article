package com.example.admin;

import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.example.admin.Controller.AdminController;
import com.example.admin.PO.Admin;
import com.example.admin.PO.Result;

import com.example.admin.PO.User;
import com.example.admin.Service.Userservice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
class AdminApplicationTests {
    @Autowired
    Userservice userservice;
    @Autowired
    AdminController adminController;
    @Test
    void contextLoads() {

    }

}
