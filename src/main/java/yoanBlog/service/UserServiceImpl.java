package yoanBlog.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import yoanBlog.bindingModel.UserBindingModel;
import yoanBlog.entity.Role;
import yoanBlog.entity.User;
import yoanBlog.repository.RoleRepository;
import yoanBlog.repository.UserRepository;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User getCurrentUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User userEntity = this.userRepository.findByEmail(user.getUsername());
        return userEntity;
    }

    @Override
    public User getById(Integer id) throws NotFoundException {
        User user = this.userRepository.findOne(id);
        if (user == null){
            throw new NotFoundException("Invalid USER!");
        }
        return user;
    }

    @Override
    public User create(UserBindingModel bindingModel) throws IOException {
        Role userRole = this.roleRepository.findByName("ROLE_USER");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User(
                bindingModel.getEmail(),
                bindingModel.getFullName(),
                bCryptPasswordEncoder.encode(bindingModel.getPassword()),
                bindingModel.getAuthorInfo());

        if (bindingModel.getProfilePicture() != null){
            byte[] profilePicture = bindingModel.getProfilePicture().getBytes();
            user.setProfilePicture(profilePicture);
        }

        user.addRole(userRole);
        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public User edit(User user, UserBindingModel bindingModel) throws IOException {
        if (bindingModel.getProfilePicture() != null){
            user.setProfilePicture(bindingModel.getProfilePicture().getBytes());
        }


        if (!StringUtils.isEmpty(bindingModel.getPassword())
                && !StringUtils.isEmpty(bindingModel.getConfirmPassword())){

            if (bindingModel.getPassword().equals(bindingModel.getConfirmPassword())){
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

                user.setPassword(bCryptPasswordEncoder.encode(bindingModel.getPassword()));
            }
        }

        user.setFullName(bindingModel.getFullName());
        user.setEmail(bindingModel.getEmail());

        Set<Role> roles = new HashSet<>();

        for (Integer roleId : bindingModel.getRoles()){
            roles.add(this.roleRepository.findOne(roleId));

        }
        if (bindingModel.getProfilePicture() != null) {
            user.setProfilePicture(bindingModel.getProfilePicture().getBytes());
        }


        user.setRoles(roles);

        this.userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAllByOrderByIdAsc();
    }
}
