package com.mouday.blogapi.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 公共的基类
 * <p>
 * MybatisPlus自动维护数据库时间戳
 * https://www.misiai.com/tutorials/88.html
 */
@Getter
@Setter
public class BaseEntity {
    protected Integer id;

    @TableField(fill = FieldFill.INSERT) //INSERT代表只在插入时填充
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //set
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") //get
    protected Date createTime;

    @TableField(fill = FieldFill.UPDATE)// INSERT_UPDATE 首次插入、其次更新时填充(或修改)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//set
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//get
    protected Date updateTime;

}