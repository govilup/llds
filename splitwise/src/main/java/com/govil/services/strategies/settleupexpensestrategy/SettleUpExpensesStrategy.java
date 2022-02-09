package com.govil.services.strategies.settleupexpensestrategy;

import com.govil.models.Expense;

import java.util.List;

public interface SettleUpExpensesStrategy {

    List<Transaction> settleUp(List<Expense> expenses);
}
