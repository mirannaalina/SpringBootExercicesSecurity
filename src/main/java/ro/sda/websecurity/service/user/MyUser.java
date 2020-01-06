package ro.sda.websecurity.service.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUser implements UserDetails {

    //spring are nevoie de UserDetails(username, password...)
    //avem un tabel cu valori pt useri.(sau fisier, sau db, sau alt serviciu unde sunt stocati userii)
    //ca sa facem conexiunea intre spring si baza de date unde se afla userii,
    //trb sa implementam interfata UserDetails
    //interfata userDetails ------intermediara e clasa curenta(MyUser) pt sa ajunga la --------userEntity

    private final String username;
    private final String password;
    private final String role;
    private final int age;

    public MyUser(String username, String password, String role, int age) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.age = age;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role));
        return roles;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
