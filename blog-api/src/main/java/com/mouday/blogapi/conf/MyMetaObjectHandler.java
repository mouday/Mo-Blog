package com.mouday.blogapi.conf;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        // 如果有createTime字段才进行自动填充
        if (metaObject.hasSetter("createTime")) {
            // setFieldValByName("createTime", new Date(), metaObject);
            setFieldValByName("createTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        // 如果updateTime 为空才进行自动填充
        if (updateTime == null) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }
}