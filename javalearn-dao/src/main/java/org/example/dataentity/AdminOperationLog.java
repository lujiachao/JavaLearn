package org.example.dataentity;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.base.BaseEntity;

@Data
@TableName("admin_operation_log_2201")
public class AdminOperationLog extends BaseEntity {
    @TableField("IdUser")
    private int IdUser;

    @TableField("Module")
    private String Module;

    @TableField("ModuleName")
    private String ModuleName;

    @TableField("UserName")
    private String UserName;

    @TableField("Name")
    private String Name;

    @TableField("Action")
    private String Action;

    @TableField("ActionName")
    private String ActionName;

    @TableField("Description")
    private String Description;

    @TableField("Result")
    private String Result;

    @TableField("Route")
    private String Route;

    @TableField("TimeBegin")
    private DateTime TimeBegin;

    @TableField("TimeEnd")
    private DateTime TimeEnd;

    @TableField("TimeCreate")
    private DateTime TimeCreate;
}
