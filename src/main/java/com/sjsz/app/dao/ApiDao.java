package com.sjsz.app.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/3/1 16:36
 * @Description:
 */
@Mapper  //该注解一定要加，否则无法映射到mybatis的***.xml局部配置文件
public interface ApiDao {

    Map<String, Object> getHumiture();
}
