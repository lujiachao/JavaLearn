package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.example.dataentity.AdminOperationLog;

@Mapper
public interface AdminOperationLogMapper extends BaseMapper<AdminOperationLog> {
    @Update("admin_operation_log_2201")
    void cleanData();
}
