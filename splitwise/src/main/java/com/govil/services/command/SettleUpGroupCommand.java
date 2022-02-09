package com.govil.services.command;

import com.govil.services.command.Command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SettleUpGroupCommand implements Command {


    @Override
    public boolean matches(String input) {
        List<String> command = Arrays.stream(input.split(" ")).collect(Collectors.toList());

        if(command.size() == 3 && command.get(1).equals(CommandKeywords.SettleUpCommand)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {

    }
}
