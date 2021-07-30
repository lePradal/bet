package com.prads.bet.models;

import com.prads.bet.enums.BetTimeStatus;
import com.prads.bet.enums.MatchStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="tb_match")
public class Match {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private MatchStatus status = MatchStatus.TO_BEGIN;

    private LocalDateTime creationDate = LocalDateTime.now();

    private LocalDateTime beginDate;

    @OneToOne
    private BetPart partOne;

    @OneToOne
    private BetPart partTwo;
    
    private BetTimeStatus betTimeStatus = BetTimeStatus.CLOSED;

    private Match() {}

    public Match(String title, LocalDateTime beginDate, BetPart partOne, BetPart partTwo) {
        this.title = title;
        this.beginDate = beginDate;
        this.partOne = partOne;
        this.partTwo = partTwo;
    }

    public Match(LocalDateTime beginDate, BetPart partOne, BetPart partTwo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        String begin = beginDate.format(formatter);

        this.title = String.format("%s x %s - %s", partOne.getName(), partTwo.getName(), begin);
        this.beginDate = beginDate;
        this.partOne = partOne;
        this.partTwo = partTwo;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public BetPart getPartOne() {
        return partOne;
    }

    public void setPartOne(BetPart partOne) {
        this.partOne = partOne;
    }

    public BetPart getPartTwo() {
        return partTwo;
    }

    public void setPartTwo(BetPart partTwo) {
        this.partTwo = partTwo;
    }

    public BetTimeStatus getBetTimeStatus() {
        return betTimeStatus;
    }

    public void setBetTimeStatus(BetTimeStatus betTimeStatus) {
        this.betTimeStatus = betTimeStatus;
    }
}
