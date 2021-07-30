package com.prads.bet.controllers;

import com.prads.bet.controllers.dto.UserDTO;
import com.prads.bet.controllers.form.PassUpdateForm;
import com.prads.bet.controllers.form.UserForm;
import com.prads.bet.controllers.form.UserUpdateForm;
import com.prads.bet.models.User;
import com.prads.bet.repository.UserRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authManager;

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {

        Optional<User> optional = userRepository.findById(id);

        if (!optional.isPresent()) {
            LOGGER.error("User not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User not found with id %s", id));
        }

        return ResponseEntity.ok(UserDTO.fromUser(optional.get()));

    }

    @GetMapping("/exists/{email}")
    public ResponseEntity emailExists(@PathVariable String email) throws NotFoundException {
        Optional<User> optional = userRepository.findByEmail(email);

        if (optional.isPresent()) {
            return ResponseEntity.ok().body(true);
        }

        return ResponseEntity.ok().body(false);
    }

    @PostMapping()
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity register(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder) {

        User user = userForm.toUser();

        Optional<User> anotherUser = userRepository.findByEmail(user.getEmail());

        if (anotherUser.isPresent()) {
            LOGGER.error("Email already registered!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered!");
        }

        userRepository.save(user);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(UserDTO.fromUser(user));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity update(@RequestBody @Valid UserUpdateForm userForm, UriComponentsBuilder uriBuilder, @PathVariable Long id) {

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(id).toUri();

        Optional<User> updatedUser = userRepository.findById(id).map(user -> {
            if ( userForm.getName() != null ) { user.setName(userForm.getName()); }
            if ( userForm.getEmail() != null ) { user.setEmail(userForm.getEmail()); }

            return userRepository.save(user);
        });

        if (!updatedUser.isPresent()) {
            LOGGER.error("User not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User not found with id %s", id));
        }

        return ResponseEntity.created(uri).body(UserDTO.fromUser(updatedUser.get()));
    }

    @PutMapping("/pass/{id}")
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity updatePass(@RequestBody @Valid PassUpdateForm passUpdateForm, @PathVariable Long id) {

        Optional<User> optional = userRepository.findById(id);

        if (!optional.isPresent()) {
            LOGGER.error("User not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User not found with id %s", id));
        }

        UsernamePasswordAuthenticationToken loginData = new UsernamePasswordAuthenticationToken(optional.get().getEmail(), passUpdateForm.getOldPassword());

        try {
            authManager.authenticate(loginData);
        } catch (AuthenticationException e) {
            LOGGER.error("Wrong password! - {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong password!");
        }

        if (passUpdateForm.getNewPassword().equals(passUpdateForm.getCheckPassword())) {
            userRepository.findById(id).map(user -> {
                user.setPassword(new BCryptPasswordEncoder().encode(passUpdateForm.getNewPassword()));
                return userRepository.save(user);
            });
        } else {
            LOGGER.error("Passwords do not match.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match.");
        }

        return ResponseEntity.ok().body("Password changed!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity delete(@PathVariable Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().body("User deleted.");
        }).orElseGet(() -> {
            LOGGER.error("User not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User not found with id %s", id));
        });
    }
}
