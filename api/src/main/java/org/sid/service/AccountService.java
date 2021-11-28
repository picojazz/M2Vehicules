package org.sid.service;

import org.sid.model.AppRole;
import org.sid.model.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public AppUser saveUser(String username, String password, String passwordC);
    public AppRole saveRole(AppRole role);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username, String rolename);
}
