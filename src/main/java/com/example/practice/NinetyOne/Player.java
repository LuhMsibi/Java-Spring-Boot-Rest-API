package com.example.practice.NinetyOne;


import jakarta.persistence.*;

@Entity
@Table
public class Player {
    @Id
    @SequenceGenerator(
            name = "player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    private Long Id;
    private String firstName;
    private String secondName;
    private int score;

    public Player() {
    }

    public Player(String firstName, String secondName, int score) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.score = score;
    }

    public Player(Long id, String firstName, String secondName, int score) {
        Id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.score = score;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", score=" + score +
                '}';
    }
}
