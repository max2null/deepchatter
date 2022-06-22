package org.max.deepchatter.Authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @OneToMany(mappedBy = "myUserDetails", fetch = FetchType.EAGER)
    private Set<MyGrantedAuthority> authorities;

    public void setDefaults() {

        MyGrantedAuthority authority = new MyGrantedAuthority();
        authority.setAuthority("USER");
        Set<MyGrantedAuthority> authorities2 = new HashSet<>();
        authorities2.add(authority);
        setAuthorities(authorities2);

        setAccountNonExpired(true);
        setAccountNonLocked(true);
        setEnabled(true);
        setCredentialsNonExpired(true);

    }


}
