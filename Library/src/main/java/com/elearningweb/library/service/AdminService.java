package com.elearningweb.library.service;

import com.elearningweb.library.dto.AdminDto;
import com.elearningweb.library.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);
    Admin save(AdminDto adminDto);
}
