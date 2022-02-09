package com.govil.controllers;

import com.govil.dto.ResponseDTO;
import com.govil.dto.SettleUpGroupRequestDTO;
import com.govil.dto.SettleUpResponseDTO;
import com.govil.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    public ResponseDTO<SettleUpResponseDTO> settleUpExpense(SettleUpGroupRequestDTO request) {

        return null;
    }
 }
