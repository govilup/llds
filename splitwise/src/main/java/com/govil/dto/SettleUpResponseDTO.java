package com.govil.dto;

import com.govil.services.strategies.settleupexpensestrategy.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpResponseDTO {

    private List<Transaction> transactions;

}
