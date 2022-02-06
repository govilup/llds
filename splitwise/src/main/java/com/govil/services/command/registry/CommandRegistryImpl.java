package com.govil.services.command.registry;

import com.govil.services.command.Command;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistryImpl implements CommandRegistry {

    List<Command> registerCommands = new ArrayList<>();

    @Override
    public void registerCommand(Command command) {
        registerCommands.add(command);
    }

    @Override
    public void executeCommand(String input) {
        for (Command c : registerCommands ) {
            if(c.matches(input)) {
                c.execute(input);
                return;
            }
        }
    }
}
