package org.example;

import org.example.base.BaseServiceImpl;
import org.example.dataentity.AdminOperationLog;
import org.example.mapper.AdminOperationLogMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminOperationLogServiceImpl extends BaseServiceImpl<AdminOperationLogMapper, AdminOperationLog> implements AdminOperationLogService {

    @Override
    public void cleanData(){
        this.baseMapper.cleanData();
    }
}
