package com.example.fruit4ladiesapp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static List<String> getAllCommands() {
        return Stream.of(Commands.values())
                .map(item -> item.getCommand())
                .collect(Collectors.toList());
    }
}
