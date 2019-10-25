package com.huangjindong.service.impl;

import com.huangjindong.dao.ISysLogDao;
import com.huangjindong.domian.SysLog;
import com.huangjindong.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogService implements ISysLogService {
    @Autowired
    private ISysLogDao iSysLogDao;
    @Override
    public void addSysLog(SysLog sysLog) {
        iSysLogDao.addSysLog(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return iSysLogDao.findAll();
    }
}
