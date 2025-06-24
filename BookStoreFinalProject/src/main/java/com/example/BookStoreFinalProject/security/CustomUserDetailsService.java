package com.example.BookStoreFinalProject.security;

import com.example.BookStoreFinalProject.entities.User;
import com.example.BookStoreFinalProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User foundUser = null;
        try {
            foundUser = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException()
                    );
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

        return AuthUser.builder()
                .user(foundUser)
                .build();
    }
}
