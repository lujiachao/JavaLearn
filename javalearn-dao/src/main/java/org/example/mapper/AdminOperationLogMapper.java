package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.example.dataentity.AdminOperationLog;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface AdminOperationLogMapper extends BaseMapper<AdminOperationLog> {
    @Update("admin_operation_log_2201")
    void cleanData();

    @Update("UPDATE admin_operation_log_2201 SET actionname = #{actionName} WHERE id = #{id}")
    void UpdateActionName(@Param("actionName") String actionName, @Param("id") Long id);

    @Update("UPDATE admin_operation_log_2201 SET username = #{userName} WHERE id = #{id}")
    void UpdateUserName(@Param("userName") String userName, @Param("id") Long id);
}
