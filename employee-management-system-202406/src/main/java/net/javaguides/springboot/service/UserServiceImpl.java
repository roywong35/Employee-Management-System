package net.javaguides.springboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setIdNumber(registrationDto.getIdNumber());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRoles((Arrays.asList(new Role("ROLE_USER"))));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByIdNumber(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid idNumber or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getIdNumber(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
