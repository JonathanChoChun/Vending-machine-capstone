package com.techelevator.handler;

import com.techelevator.dao.LogDao;

public class LogHandler implements Logger {
    private LogDao logDao;


    public LogHandler(LogDao logDao) {
        this.logDao = logDao;
    }

    @Override
    public void writeAudit(String auditMessage) {
        logDao.writeAudit(auditMessage);
    }

    @Override
    public void writeError(String errorMessage) {
        logDao.writeError(errorMessage);
    }
}
