package com.example.library_management.services;


import com.example.library_management.models.Admin;
import com.example.library_management.repositaries.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public void create(Admin admin){
        adminRepository.save(admin);
    }

    public Admin find(Integer adminId){
       return adminRepository.findById(adminId).orElse(null);
    }




}
