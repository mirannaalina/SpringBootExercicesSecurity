package ro.sda.websecurity.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.websecurity.persistence.user.UserEntity;
import ro.sda.websecurity.persistence.user.UserRepository;

@Service
public class UserService implements UserDetailsService{

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository){
        this.repository=repository;
    }

    @Transactional
    public void save(String username, String password, int age){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setAge(age);
        userEntity.setRole("ADMIN");
        repository.save(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //trebuie sa obtinem userul dupa username
        UserEntity userEntity =repository.findByUsername(username);//ia userul din baza de date
        if(userEntity==null){
            throw new UsernameNotFoundException("Could not find username");
        }
        if(userEntity.getRole()== null || userEntity.getRole().isEmpty()){
            throw new UsernameNotFoundException("No role found for user");
        }
        return new MyUser(userEntity.getUsername(),userEntity.getPassword(),userEntity.getRole(),userEntity.getAge());
        //returneaza clasa MyUser cu datele de care are nevoie spring sa faca match-ul mai departe
    }
}
