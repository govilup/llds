package com.govil.services;

import com.govil.exceptions.GroupDoesntExistException;
import com.govil.models.Expense;
import com.govil.models.Group;
import com.govil.models.GroupExpense;
import com.govil.models.User;
import com.govil.repositories.GroupExpenseRepository;
import com.govil.repositories.GroupRepository;
import com.govil.services.strategies.settleupexpensestrategy.SettleUpExpensesStrategy;
import com.govil.services.strategies.settleupexpensestrategy.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupExpenseRepository groupExpenseRepository;

    @Autowired
    private SettleUpExpensesStrategy settleUpExpensesStrategy;

    public List<Transaction> settleUp(Long userId, Long groupId) throws Exception {
        Optional<Group> group = groupRepository.findById(groupId);
        if(group.isEmpty()) {
            throw new GroupDoesntExistException(groupId);
        }
        boolean userExist = false;
        for (User user : group.get().getMembers()) {
            if(user.getId().equals(userId)) {
                userExist = true;
                break;
            }
        }

        if(!userExist) {
            //TODO: throw some exception
        }

        List<GroupExpense> groupExpenses = groupExpenseRepository.findAllByGroup(group.get());
        List<Expense> expenses = groupExpenses.stream().map(GroupExpense::getExpense).collect(Collectors.toList());

        return settleUpExpensesStrategy.settleUp(expenses);
    }
}
