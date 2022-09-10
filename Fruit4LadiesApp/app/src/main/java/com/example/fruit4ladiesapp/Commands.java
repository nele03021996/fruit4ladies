package com.example.fruit4ladiesapp;

public enum Commands {

    MATCH_NEXT("next"),
    MATCH_START ("start");

    private String command;

    Commands(String command){
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
