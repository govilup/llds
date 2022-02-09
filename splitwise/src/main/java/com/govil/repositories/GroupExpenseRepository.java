package com.govil.repositories;

import com.govil.models.Group;
import com.govil.models.GroupExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupExpenseRepository extends JpaRepository<GroupExpense, Long> {

    List<GroupExpense> findAllByGroup(Group group);
}
