package com.govil.controllers;

import com.govil.dto.*;
import com.govil.models.User;
import com.govil.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public ResponseDTO<RegisterUserResponseDTO> registerUser(RegisterUserRequestDTO request) {
        User user = userService.registerUser(request.getName(), request.getPhoneNumber(), request.getPassword());
        ResponseDTO<RegisterUserResponseDTO> response = new ResponseDTO<>();
        if(user == null) {
            response.setStatusCode(StatusCode.FAILURE);
            return response;
        }

        response.setStatusCode(StatusCode.SUCCESS);
        RegisterUserResponseDTO responseDTO = new RegisterUserResponseDTO();
        responseDTO.setUserId(user.getId());
        response.setData(responseDTO);
        return response;
     }

     public ResponseDTO<UpdateProfileResponseDTO> updateProfile(UpdateProfileRequestDTO request) {
         boolean saved = userService.updatePassword(request.getUserId(), request.getNewPassword());
         ResponseDTO<UpdateProfileResponseDTO> response = new ResponseDTO<>();
         if(!saved) {
             response.setStatusCode(StatusCode.FAILURE);
             return response;
         }

         response.setStatusCode(StatusCode.SUCCESS);
         return response;
     }
}
