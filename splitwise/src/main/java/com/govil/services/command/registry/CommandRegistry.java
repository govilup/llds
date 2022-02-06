package com.govil.services.command.registry;

import com.govil.services.command.Command;

public interface CommandRegistry {

    void registerCommand(Command command);

    void executeCommand(String command);
}
