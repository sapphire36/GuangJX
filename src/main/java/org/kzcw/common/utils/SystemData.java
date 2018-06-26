package org.kzcw.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.kzcw.model.Status;

public class SystemData {
	//系统数据

	private static SystemData instance=new SystemData();
	public List<String> locklist;
	public List<Status> statuslist;
	
	public SystemData() {
		// TODO Auto-generated constructor stub
	    locklist=new ArrayList<String>();
	    statuslist=new ArrayList<Status>();
	}
	public static SystemData getInstance() {
		return instance;
	}
}
