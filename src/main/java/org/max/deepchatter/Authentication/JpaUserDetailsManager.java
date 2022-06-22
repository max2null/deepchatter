package org.max.deepchatter.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class JpaUserDetailsManager implements UserDetailsManager {

    @Autowired
    private MyUserDetailsRepository repository;




    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("No user found with username = " + username));
    }

    @Override
    public void createUser(UserDetails user) {
        repository.save((MyUserDetails) user);
    }

    @Override
    public void updateUser(UserDetails user) {
        repository.save((MyUserDetails) user);
    }

    @Override
    public void deleteUser(String username) {
        MyUserDetails userDetails = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No User found for username -> " + username));
        repository.delete(userDetails);
    }


    @Override
    @Transactional
    public void changePassword(String oldPassword, String newPassword) {
        MyUserDetails userDetails = repository.findByPassword(oldPassword)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid password "));
        userDetails.setPassword(newPassword);
        repository.save(userDetails);
    }

    @Override
    public boolean userExists(String username) {
        return repository.findByUsername(username).isPresent();
    }
}