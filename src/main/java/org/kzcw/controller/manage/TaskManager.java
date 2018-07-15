package org.kzcw.controller.manage;
import java.util.List;
import org.kzcw.common.global.CheckLightBoxList;
import org.kzcw.common.global.CloseLockList;
import org.kzcw.common.global.OpenLockList;
import org.kzcw.common.global.SysLogManager;
import org.kzcw.common.global.SystemData;
import org.kzcw.common.global.WaitPublishQueue;
import org.kzcw.model.Status;
import org.kzcw.model.Systemlogs;
import org.kzcw.service.BreakhistoryService;
import org.kzcw.service.StatusService;
import org.kzcw.service.SystemlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class TaskManager {
	//定时器管理器
	
	// 故障历史表
	@Autowired
	BreakhistoryService breakservice;
	
	// 设备状态历史记录
	@Autowired
	StatusService staservice;
	
	@Autowired
	SystemlogsService logservice;
	
	// 每个5秒执行一次
	@Scheduled(cron = "0/20 * * * * ? ")
	public void statusreport() {
		//状态上报数据
		SystemData systemdata = SystemData.getInstance();
		if (systemdata.statuslist.size() > 0) {
			for (Status s : systemdata.statuslist) {
				staservice.save(s);
			}
			systemdata.statuslist.clear();
		}
		refreshQueue();
	}
	
	// 每个5秒执行一次
	@Scheduled(cron = "0/20 * * * * ? ")
	public void logschedule() {
		//日志数据处理
		SysLogManager logmanager=SysLogManager.getInstance();
		if (logmanager.IsNotEmpty()) {
			 List<Systemlogs> loglist=logmanager.getLogList();
			 for(Systemlogs log:loglist) {
				 //将日志保存到数据库
				 logservice.save(log);
			 }
			 loglist.clear();
		}
	}
	
	private void refreshQueue() {
		//设置刷新
		OpenLockList open=OpenLockList.getInstance();
		open.IsFlush=true;
		
		WaitPublishQueue wait=WaitPublishQueue.getInstance();
		wait.IsFlush=true;
		
		CloseLockList close=CloseLockList.getInstance();
		close.IsFlush=true;
		
	}
}
