package org.example.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BaseEntity {

    @TableId(type =  IdType.AUTO)
    private Long id;
}
