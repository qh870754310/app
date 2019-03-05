package com.sjsz.app.service.impl;

import com.sjsz.app.dao.ApiDao;
import com.sjsz.app.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/3/1 16:34
 * @Description:
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDao apiDao;

    @Override
    public Map<String, Object> getHumiture() {
        Map<String, Object> map = apiDao.getHumiture();
        return map;
    }
}
