package org.example;

import org.example.base.BaseService;
import org.example.dataentity.AdminOperationLog;

public interface AdminOperationLogService extends BaseService<AdminOperationLog> {
    void cleanData();
}
