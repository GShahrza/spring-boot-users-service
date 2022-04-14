package az.javadevs.usersservice.security;

import az.javadevs.usersservice.dao.entity.Account;
import az.javadevs.usersservice.dao.entity.RoleEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private Boolean isActive;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(Account account) {
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.isActive = account.getIsActive();
        this.authorities = account.getRoles().stream()
                .map((RoleEntity role) -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
