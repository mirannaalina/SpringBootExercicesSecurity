package ro.sda.websecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.sda.websecurity.service.user.UserService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final UserService userService;
    private PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    //logica pt autentificare
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String encodedPassword = passwordEncoder.encode("admin");

        //apelam aici userService care are userServiceDetails-ul si stie sa faca match-ul cu username-ul si parola
        auth.userDetailsService(userService);

    }


    //in metoda asta ii spunem pe ce sa se faca securizarile, vrem sa dam acces la all, dar intrerupem accesul la SAVE
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/book/save").hasRole("ADMIN")
                .antMatchers("/book/all").permitAll();
    }
    //--------------------------folosim memoria interna, fara db-----------------
    //       auth.inMemoryAuthentication()//le putem stoca si intr-o baza de date
 //               .withUser("admin").password(encodedPassword).roles("ADMIN") //avem nevoie si de un rol
 //               .and()
 //               .withUser("Alina").password(encodedPassword).roles("admin")
 //               .and()
 //               .withUser("Alex").password(encodedPassword).roles("admin");
 //   }
    //------------------encode flow----------------------------------------------
    //DB : admin | 3428aga98342352952wgehs
    // admin, admin => aplica encoder pe parola => admin, 3428aga98342352952wgehs

    //face parola encodata

}
