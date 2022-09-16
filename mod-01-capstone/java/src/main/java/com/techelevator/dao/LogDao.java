package com.techelevator.dao;

public interface LogDao {
    void writeAudit(String errorMessage);
    void writeError(String errorMessage);
}
