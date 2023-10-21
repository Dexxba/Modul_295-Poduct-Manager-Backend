package ch.csbe.modul_295.auth;

import ch.csbe.modul_295.login.LoginDto;
import ch.csbe.modul_295.users.Users;
import ch.csbe.modul_295.users.UsersRepository;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    @Autowired
    private UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void login(LoginDto loginDto) {
        Users users = usersRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (bCryptPasswordEncoder.matches(loginDto.getPassword(), users.getPassword())) {
            System.out.println("Successful");
        } else {
            System.out.println("Not Successful");
        }
    }
}