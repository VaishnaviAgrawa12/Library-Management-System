package com.example.library_management.controllers;


import com.example.library_management.dtos.CreateAdminRequest;
import com.example.library_management.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

  @PostMapping("/admin")
  public void createAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest){
    adminService.create(createAdminRequest.to());
}
}
