package ro.sda.websecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.sda.websecurity.service.book.BookService;
import ro.sda.websecurity.service.user.UserService;

@Component
public class OnStart implements ApplicationRunner {

    private final UserService userService;
    private final BookService bookService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OnStart(UserService userService, BookService bookService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.bookService = bookService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.save("admin",passwordEncoder.encode("admin"),20);
        userService.save("Alina",passwordEncoder.encode("admin"),23);
        bookService.save(("Ion Creanga"),"Punguta cu 2 bani");
        bookService.save("Dovstoievsky", "Crima si pedeapsa");
        bookService.save(("Alexandre Dumas"),"Dama cu camelii");
        bookService.save(("OSho"),"Bani si avere");

    }
}
