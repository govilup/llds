package com.govil.services.command;

import com.govil.controllers.UserController;
import com.govil.dto.RegisterUserRequestDTO;
import com.govil.dto.RegisterUserResponseDTO;
import com.govil.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegisterUserCommand implements Command {

    @Autowired
    private UserController userController;

    @Override
    public boolean matches(String input) {
        List<String> inputs = Arrays.stream(input.split(" ")).collect(Collectors.toList());

        if(inputs.size() == 4 && inputs.get(0).equals(CommandKeywords.RegisterUserCommand)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inputs = Arrays.stream(input.split(" ")).collect(Collectors.toList());
        String username = inputs.get(1);
        String phoneNumber = inputs.get(2);
        String password = inputs.get(3);

        RegisterUserRequestDTO registerUserRequestDTO = new RegisterUserRequestDTO();
        registerUserRequestDTO.setName(username);
        registerUserRequestDTO.setPassword(password);
        registerUserRequestDTO.setPhoneNumber(phoneNumber);

        ResponseDTO<RegisterUserResponseDTO> response = userController.registerUser(registerUserRequestDTO);
        System.out.println("Registered User "+response.getData());
    }

}
