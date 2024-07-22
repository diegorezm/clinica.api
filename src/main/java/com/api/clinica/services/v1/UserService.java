package com.api.clinica.services.v1;

import com.api.clinica.domain.user.User;
import com.api.clinica.domain.user.UserDTO;
import com.api.clinica.domain.user.exceptions.UserEmailExistsException;
import com.api.clinica.domain.user.exceptions.UserNotFoundExeception;
import com.api.clinica.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(UserNotFoundExeception::new);
    }

    public User getByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(UserNotFoundExeception::new);
    }

    // there are better ways to do this
    public User create(UserDTO payload) {
        try {
            this.getByEmail(payload.email());
            throw new UserEmailExistsException();
        } catch (UserNotFoundExeception e) {
            return this.userRepository.save(new User(payload));
        } catch (Exception e){
            throw e;
        }
    }

    public User update(UserDTO payload, UUID id) {
        User user = this.getById(id);
        user.setName(payload.name());
        user.setUpdatedAt(LocalDateTime.now());
        this.userRepository.save(user);
        return user;
    }

    public void delete(UUID id) {
        this.userRepository.delete(this.getById(id));
    }
}
