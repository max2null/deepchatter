package org.max.deepchatter.Authentication;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserDetailsRepository extends JpaRepository<MyUserDetails, Long> {
    Optional<MyUserDetails> findByUsername(String username);

    Optional<MyUserDetails> findByPassword(String password);
}
