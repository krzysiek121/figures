package pl.kurs.figures.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kurs.figures.model.security.AppRole;
import pl.kurs.figures.model.security.AppUser;
import pl.kurs.figures.repository.AppUserRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsernameWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}