package com.govil.services.strategies.settleupexpensestrategy;

import com.govil.models.Expense;
import com.govil.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LinearGreedySettleUpStrategy implements SettleUpExpensesStrategy {

    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        Map<User, Long> totalMoneyOwed = new HashMap<>();

        for(Expense expense : expenses) {
            for(User user : expense.getOwedBy().keySet()) {
                if(!totalMoneyOwed.containsKey(user)) {
                    totalMoneyOwed.put(user, 0L);
                }

                totalMoneyOwed.put(user, totalMoneyOwed.get(user) + expense.getPaidBy().get(user));
            }

            for(User user : expense.getOwedBy().keySet()) {
                if(!totalMoneyOwed.containsKey(user)) {
                    totalMoneyOwed.put(user, 0L);
                }

                totalMoneyOwed.put(user, totalMoneyOwed.get(user) - expense.getPaidBy().get(user));
            }
        }
        List<User> users = new ArrayList<>(totalMoneyOwed.keySet());
        List<Transaction> transactions = new ArrayList<>();

        for(int i = 0; i < users.size() - 1; i++) {
            User currentUser = users.get(i);
            User nextUser = users.get(i + 1);
            Long extraPaid = totalMoneyOwed.get(currentUser);
            if(extraPaid > 0) {
                transactions.add(createTransaction(nextUser, currentUser, extraPaid));
                totalMoneyOwed.put(nextUser, totalMoneyOwed.get(nextUser) + extraPaid);
                totalMoneyOwed.put(currentUser, 0L);
            } else {
                transactions.add(
                        createTransaction(currentUser, nextUser, -extraPaid)
                );
                totalMoneyOwed.put(nextUser, totalMoneyOwed.get(nextUser) - (-extraPaid));
                totalMoneyOwed.put(currentUser, 0L);
            }
        }
        return transactions;
    }

    private Transaction createTransaction(User from, User to, Long amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setFrom(from);
        transaction.setTo(to);
        return transaction;
    }
}
