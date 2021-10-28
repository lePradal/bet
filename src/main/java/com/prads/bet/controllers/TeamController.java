package com.prads.bet.controllers;

import com.prads.bet.controllers.dto.TeamDTO;
import com.prads.bet.controllers.form.PassUpdateForm;
import com.prads.bet.controllers.form.TeamForm;
import com.prads.bet.controllers.form.TeamUpdateForm;
import com.prads.bet.enums.RolesIdEnums;
import com.prads.bet.models.Profile;
import com.prads.bet.models.Team;
import com.prads.bet.repository.ProfileRepository;
import com.prads.bet.repository.TeamRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/team")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    private final static Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {

        Optional<Team> optional = teamRepository.findById(id);

        if (!optional.isPresent()) {
            LOGGER.error("Team not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Team not found with id %s", id));
        }

        return ResponseEntity.ok(TeamDTO.fromTeam(optional.get()));
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "team", allEntries = true)
    public ResponseEntity register(@RequestBody @Valid TeamForm teamForm, UriComponentsBuilder uriBuilder) {

        Team team = teamForm.toTeam();

        Optional<Team> anotherTeam = teamRepository.findByName(team.getName());

        if (anotherTeam.isPresent()) {
            LOGGER.error("Team already exists!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team already exists!");
        }

        teamRepository.save(team);

        URI uri = uriBuilder.path("/team/{id}").buildAndExpand(team.getId()).toUri();

        return ResponseEntity.created(uri).body(TeamDTO.fromTeam(team));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "team", allEntries = true)
    public ResponseEntity update(@RequestBody @Valid TeamUpdateForm teamForm, UriComponentsBuilder uriBuilder, @PathVariable Long id) {

        URI uri = uriBuilder.path("/team/{id}").buildAndExpand(id).toUri();

        Optional<Team> updatedTeam = teamRepository.findById(id).map(team -> {
            if ( teamForm.getName() != null ) { team.setName(teamForm.getName()); }
            if ( teamForm.getImageUrl() != null ) { team.setImageUrl(teamForm.getImageUrl()); }

            return teamRepository.save(team);
        });

        if (!updatedTeam.isPresent()) {
            LOGGER.error("Team not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Team not found with id %s", id));
        }

        return ResponseEntity.created(uri).body(TeamDTO.fromTeam(updatedTeam.get()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "team", allEntries = true)
    public ResponseEntity delete(@PathVariable Long id) {
        return teamRepository.findById(id).map(team -> {
            teamRepository.delete(team);
            return ResponseEntity.ok().body("Team deleted.");
        }).orElseGet(() -> {
            LOGGER.error("Team not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Team not found with id %s", id));
        });
    }
}
