package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.models.Role;
import web.models.User;
import web.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return (List<User>) users;
    }

    @Override
    @Transactional
    public User show(Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    @Transactional
    public void update(Long id, User updateUser) {
        User userToBeUpdated = userRepository.findById(id).get();
        userToBeUpdated.setName(updateUser.getName());
        userToBeUpdated.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        userToBeUpdated.setRoles(updateUser.getRoles());
        userRepository.save(userToBeUpdated);
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByName(userName).get();
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        return userRepository.findByName(name).get();
    }
}
