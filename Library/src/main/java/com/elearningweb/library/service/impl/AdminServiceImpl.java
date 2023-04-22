package com.elearningweb.library.service.impl;

import com.elearningweb.library.dto.AdminDto;
import com.elearningweb.library.model.Admin;
import com.elearningweb.library.repository.AdminRepository;
import com.elearningweb.library.repository.RoleRepository;
import com.elearningweb.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());;
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(admin.getUsername());
        admin.setPassword(admin.getPassword());
        admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        return adminRepository.save(admin);
    }
}
