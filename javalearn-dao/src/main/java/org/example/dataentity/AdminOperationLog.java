package org.example.dataentity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.base.BaseEntity;

@Data
@TableName("admin_operation_log_2201")
public class AdminOperationLog extends BaseEntity {
    private int IdUser;

    private String Module;

    private String ModuleName;

    private String UserName;

    private String Name;

    private String Action;

    private String ActionName;

    private String Description;

    private String Result;

    private String Route;

    private DateTime TimeBegin;

    private DateTime TimeEnd;

    private DateTime TimeCreate;
}
