package com.govil.exceptions;

public class GroupDoesntExistException extends Exception {

    public GroupDoesntExistException(Long id) {
        super("Group with id:" + id + "doesn't exist");
    }
}
