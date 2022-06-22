package org.max.deepchatter.Authentication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyGrantedAuthorityRepository extends JpaRepository<MyGrantedAuthority, Long> {
}
