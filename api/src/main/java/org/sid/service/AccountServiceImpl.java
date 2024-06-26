package org.sid.service;

import org.sid.repository.AppRoleRepository;
import org.sid.repository.AppUserRepository;
import org.sid.model.AppRole;
import org.sid.model.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public AppUser saveUser(String username, String password, String passwordC) {
        AppUser user = appUserRepository.findByUsername(username);
        if(user != null) throw new RuntimeException("User already exists");
        if(!(password).equals(passwordC)) throw new RuntimeException("Please confirm your password");
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUser.setActived(true);
        appUserRepository.save(appUser);
        addRoleToUser(username,"USER");
        return appUser;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        appRoleRepository.save(role);
        return role;
    }

    @Override
    public AppUser loadUserByUsername(String username) {

        return appUserRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser user = appUserRepository.findByUsername(username);
        user.getRoles().add(appRoleRepository.findByRoleName(rolename));
        //appUserRepository.save(user);
    }
}
