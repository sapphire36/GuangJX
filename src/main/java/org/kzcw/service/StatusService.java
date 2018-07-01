package org.kzcw.service;


import org.kzcw.core.BaseService;
import org.kzcw.model.Status;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("StatusService")
public interface StatusService extends BaseService<Status>{

	public boolean deleteAllItem();//清空数据表
}
