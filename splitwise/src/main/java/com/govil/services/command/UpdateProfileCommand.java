package com.govil.services.command;

import com.govil.controllers.UserController;
import com.govil.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UpdateProfileCommand implements Command {

    @Autowired
    private UserController userController;

    @Override
    public boolean matches(String input) {
        List<String> inputs = Arrays.stream(input.split(" ")).collect(Collectors.toList());
        if(inputs.size() == 3 && inputs.get(1).equals(CommandKeywords.UpdateProfileCommand)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inputs = Arrays.stream(input.split(" ")).collect(Collectors.toList());
        String password = inputs.get(2);

        UpdateProfileRequestDTO updateProfileRequestDTO = UpdateProfileRequestDTO.builder()
                .newPassword(password)
                .build();

        ResponseDTO<UpdateProfileResponseDTO> response = userController.updateProfile(updateProfileRequestDTO);
        System.out.println("Updated: "+response.getData());
    }

}
