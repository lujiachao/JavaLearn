package org.example;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.SqlSession;
import org.example.base.BaseServiceImpl;
import org.example.dataentity.AdminOperationLog;
import org.example.mapper.AdminOperationLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminOperationLogServiceImpl extends BaseServiceImpl<AdminOperationLogMapper, AdminOperationLog> implements AdminOperationLogService {

    @Override
    public void cleanData(){
        this.baseMapper.cleanData();
    }

    @Override
    public void UpdateActionName(String actionName, Long id){
        this.baseMapper.UpdateActionName(actionName, id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void TestTransational(String actionName, Long id, String userName){
       this.baseMapper.UpdateActionName(actionName, id);

       this.baseMapper.UpdateUserName(userName, id);
    }
}
