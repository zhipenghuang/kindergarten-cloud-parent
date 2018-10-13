package com.zc.kindergarten.gateway.utils;

import com.zc.kindergarten.common.vo.LogInfo;
import com.zc.kindergarten.gateway.feign.ILogService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ${DESCRIPTION}
 *
 * @author hzp
 * @create 2018-10-01 15:28
 */
@Slf4j
public class DbLog extends Thread {
    private static DbLog dblog = null;
    private static BlockingQueue<LogInfo> logInfoQueue = new LinkedBlockingQueue<>(1024);

    public ILogService getLogService() {
        return logService;
    }

    public DbLog setLogService(ILogService logService) {
        if(this.logService==null) {
            this.logService = logService;
        }
        return this;
    }

    private ILogService logService;
    public static synchronized DbLog getInstance() {
        if (dblog == null) {
            dblog = new DbLog();
        }
        return dblog;
    }

    private DbLog() {
        super("CLogOracleWriterThread");
    }

    public void offerQueue(LogInfo logInfo) {
        try {
            logInfoQueue.offer(logInfo);
        } catch (Exception e) {
            log.error("日志写入失败", e);
        }
    }

    @Override
    public void run() {
        // 缓冲队列
        List<LogInfo> bufferedLogList = new ArrayList<>();
        while (true) {
            try {
                bufferedLogList.add(logInfoQueue.take());
                logInfoQueue.drainTo(bufferedLogList);
                if (bufferedLogList != null && bufferedLogList.size() > 0) {
                    // 写入日志
                    for(LogInfo log:bufferedLogList){
                        logService.saveLog(log);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 防止缓冲队列填充数据出现异常时不断刷屏
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {

                }
            } finally {
                if (bufferedLogList != null && bufferedLogList.size() > 0) {
                    try {
                        bufferedLogList.clear();
                    } catch (Exception e) {

                    }
                }
            }
        }
    }
}