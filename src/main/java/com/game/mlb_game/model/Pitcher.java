package com.game.mlb_game.model;

import jakarta.persistence.*;

@Entity
@Table(name = "career_pitching")
public class Pitcher {

    @Id
    private String playerID;

    @Column(name = "firstName") // matches DB column exactly
    private String firstName;

    @Column(name = "lastName") // matches DB column exactly
    private String lastName;


    @Column(name = "rookieYear")
    private int rookieYear;

    @Column(name = "wins")
    private int wins;

    @Column(name = "losses")
    private int losses;

    @Column(name = "IP")
    private double IP;

    @Column(name = "K")
    private int K;

    @Column(name = "BB")
    private int BB;

    @Column(name = "ER")
    private int ER;

    @Column(name = "ERA")
    private double ERA;

    @Column(name = "HR")
    private int HR;

    @Column(name = "SV")
    private int SV;

    // Getters and Setters
    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRookieYear() {
        return rookieYear;
    }

    public void setRookieYear(int rookieYear) {
        this.rookieYear = rookieYear;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public double getIP() {
        return IP;
    }

    public void setIP(double IP) {
        this.IP = IP;
    }

    public int getK() {
        return K;
    }

    public void setK(int K) {
        this.K = K;
    }

    public int getBB() {
        return BB;
    }

    public void setBB(int BB) {
        this.BB = BB;
    }

    public int getER() {
        return ER;
    }

    public void setER(int ER) {
        this.ER = ER;
    }

    public double getERA() {
        return ERA;
    }

    public void setERA(double ERA) {
        this.ERA = ERA;
    }

    public int getHR() {
        return HR;
    }

    public void setHR(int HR) {
        this.HR = HR;
    }

    public int getSV() {
        return SV;
    }

    public void setSV(int SV) {
        this.SV = SV;
    }
}
