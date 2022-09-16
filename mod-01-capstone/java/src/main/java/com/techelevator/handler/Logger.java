package com.techelevator.handler;

import java.util.List;

public interface Logger {
    void writeAudit(String auditMessage);

    void writeError(String errorMessage);


}
