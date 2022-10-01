package fontys.s3.Carspacebackend.business.service.impl;
import fontys.s3.Carspacebackend.business.interfaces.IRoleRepository;
import fontys.s3.Carspacebackend.business.interfaces.IUserRepository;
import fontys.s3.Carspacebackend.business.service.IUserService;
import fontys.s3.Carspacebackend.domain.IRole;
import fontys.s3.Carspacebackend.domain.User;
import fontys.s3.Carspacebackend.domain.requests.LoginReq;
import fontys.s3.Carspacebackend.exception.IncorrectCredentialsException;
import fontys.s3.Carspacebackend.exception.ResourceNotFoundException;
import fontys.s3.Carspacebackend.persistence.Entity.RoleEntity;
import fontys.s3.Carspacebackend.persistence.Entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private IRoleRepository roleRepository;
    private IUserRepository userRepository;

    public UserService(IRoleRepository roleRepository, IUserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long registerUser(User u){
        IRole role = roleRepository.findById(1L);
        u.setRole(role);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(u.getPassword());
        u.setPassword(encodedPassword);
        return userRepository.saveUser(u);
    }

    @Override
    public User loginUser(String username, String password){
        User found = userRepository.getUserByUsername(username);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());

        if(!bCryptPasswordEncoder.matches(password, found.getPassword())){
            throw new IncorrectCredentialsException();
        }
        return found;
    }
}