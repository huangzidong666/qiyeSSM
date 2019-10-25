package com.huangjindong.service;

import com.huangjindong.domian.SysLog;

import java.util.List;


public interface ISysLogService {
    void addSysLog(SysLog sysLog);

    List<SysLog> findAll();
}
