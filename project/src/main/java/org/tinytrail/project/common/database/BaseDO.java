package org.tinytrail.project.common.database;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * 数据库持久层对象基础属性
 */
@Data
public class BaseDO {

    /**
     * 创建时间 - 记录创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间 - 记录的最后修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除标志 - 逻辑删除标志(1:删除, 0:未删除)
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;

}
