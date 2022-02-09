package com.govil.services.strategies.settleupexpensestrategy;

import com.govil.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

    private User from;
    private User to;
    private Long amount;
}
