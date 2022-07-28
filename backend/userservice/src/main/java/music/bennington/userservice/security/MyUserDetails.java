package music.bennington.userservice.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import music.bennington.userservice.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

    private String emailId;
    private String password;
    private boolean isActive;
    private List<SimpleGrantedAuthority> authorities;

    public MyUserDetails(UserEntity userModel) {
        this.emailId = userModel.getEmailId();
        this.password = userModel.getPassword();
        this.isActive = userModel.isActive();
        this.authorities = Arrays.stream(userModel.getRole().split(","))
                .map(SimpleGrantedAuthority::new).toList();
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
        return emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
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
