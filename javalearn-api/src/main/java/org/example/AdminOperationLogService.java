package org.example;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.example.base.BaseService;
import org.example.dataentity.AdminOperationLog;

public interface AdminOperationLogService extends BaseService<AdminOperationLog> {
    void cleanData();

    void UpdateActionName(String actionName, Long id);

    void TestTransational(String actionName, Long id, String userName);
}
