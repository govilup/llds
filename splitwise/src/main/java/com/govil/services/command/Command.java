package com.govil.services.command;

public interface Command {

    boolean matches(String input);

    void execute(String input);

}
