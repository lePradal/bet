package com.prads.bet.controllers;

import com.prads.bet.controllers.dto.MatchDTO;
import com.prads.bet.controllers.form.MatchForm;
import com.prads.bet.controllers.form.MatchUpdateForm;
import com.prads.bet.enums.MatchStatus;
import com.prads.bet.models.Match;
import com.prads.bet.models.Team;
import com.prads.bet.repository.MatchRepository;
import com.prads.bet.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/match")
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    private final static Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {

        Optional<Match> optional = matchRepository.findById(id);

        if (!optional.isPresent()) {
            LOGGER.error("Match not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Match not found with id %s", id));
        }

        return ResponseEntity.ok(MatchDTO.fromMatch(optional.get()));
    }

    @GetMapping
    public ResponseEntity list() {

        List<Match> matchList = matchRepository.findAll();

        if (!matchList.isEmpty()) {
            LOGGER.error("No matches found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("No matches found!"));
        }

        List<MatchDTO> matchDTOList = new ArrayList<>();

        matchList.forEach(match -> {
            matchDTOList.add(MatchDTO.fromMatch(match));
        });

        return ResponseEntity.ok().body(matchDTOList);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "match", allEntries = true)
    public ResponseEntity register(@RequestBody @Valid MatchForm matchForm, UriComponentsBuilder uriBuilder) {

        Optional<Team> optionalTeamOne = teamRepository.findById(matchForm.getTeamOneId());
        Optional<Team> optionalTeamTwo = teamRepository.findById(matchForm.getTeamTwoId());

        if (!optionalTeamOne.isPresent() || !optionalTeamTwo.isPresent()) {
            LOGGER.error("Email already registered!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Teams not found!");
        }

        Match match = new Match(matchForm.getTitle(), matchForm.getBeginDate(), optionalTeamOne.get(), optionalTeamTwo.get());

        matchRepository.save(match);

        URI uri = uriBuilder.path("/match/{id}").buildAndExpand(match.getId()).toUri();

        return ResponseEntity.created(uri).body(MatchDTO.fromMatch(match));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "match", allEntries = true)
    public ResponseEntity update(@RequestBody @Valid MatchUpdateForm matchForm, UriComponentsBuilder uriBuilder, @PathVariable Long id) {

        return matchRepository.findById(id).map(match -> {

            if (match.getStatus().equals(MatchStatus.STARTED) || match.getStatus().equals(MatchStatus.FINISHED) || match.getStatus().equals(MatchStatus.CANCELED)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Match cannot be changed, because its status is \"%s\".", match.getStatus().getValue()));
            }

            if ( matchForm.getTitle() != null ) { match.setTitle(matchForm.getTitle()); }
            if ( matchForm.getBeginDate() != null ) { match.setBeginDate(matchForm.getBeginDate()); }
            if ( matchForm.getTeamOne() != null ) {
                Optional<Team> optionalTeam = teamRepository.findById(matchForm.getTeamOne());

                if (optionalTeam.isPresent()) {
                    match.setTeamOne(optionalTeam.get());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Team one not found by id %s", matchForm.getTeamOne()));
                }
            }
            if ( matchForm.getTeamTwo() != null ) {
                Optional<Team> optionalTeam = teamRepository.findById(matchForm.getTeamTwo());

                if (optionalTeam.isPresent()) {
                    match.setTeamTwo(optionalTeam.get());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Team two not found by id %s", matchForm.getTeamTwo()));
                }
            }

            Match updatedMatch = matchRepository.save(match);

            return ResponseEntity.ok().body(MatchDTO.fromMatch(updatedMatch));

        }).orElseGet(() -> {
            LOGGER.error("Match not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Match not found with id %s", id));
        });
    }

    @PostMapping("/{id}/start")
    @Transactional
    @CacheEvict(value = "match", allEntries = true)
    public ResponseEntity startMatch(@PathVariable Long id) {

        return matchRepository.findById(id).map(match -> {

            if (match.getStatus().equals(MatchStatus.STARTED)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Match already started"));
            }

            match.setStatus(MatchStatus.STARTED);

            Match updatedMatch = matchRepository.save(match);

            return ResponseEntity.ok().body(MatchDTO.fromMatch(updatedMatch));

        }).orElseGet(() -> {
            LOGGER.error("Match not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Match not found with id %s", id));
        });
    }


    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "match", allEntries = true)
    public ResponseEntity delete(@PathVariable Long id) {
        return matchRepository.findById(id).map(match -> {
            matchRepository.delete(match);
            return ResponseEntity.ok().body("Match deleted.");
        }).orElseGet(() -> {
            LOGGER.error("Match not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Match not found with id %s", id));
        });
    }
}
