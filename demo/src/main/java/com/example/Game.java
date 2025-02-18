package com.example;

public class Game {
    private int position;
    private String name;
    private int score;

    // Constructor
    public Game(int position, String name, int score) {
        this.position = position;
        this.name = name;
        this.score = score;
    }
    public Game() {
    }

    // Getters y setters
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // @Override
    // public String toString() {
    //     return "Game{" +
    //             "position=" + position +
    //             ", name='" + name + '\'' +
    //             ", score=" + score +
    //             '}';
    // }
}
