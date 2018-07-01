package org.kzcw.service.Imp;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.ModuleDao;
import org.kzcw.model.Module;
import org.kzcw.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("moduleService")
@Component
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements ModuleService{
	
	private ModuleDao dao;

	@Autowired
	@Qualifier("moduleDao")
	public void setDao(ModuleDao dao) {
		this.dao = dao;
	}
	
	
	@Override
	public boolean deleteAllItem() {
		//清空数据表
		String sql="delete from t_module";
		boolean result=false;
		try {
			result=dao.ExecSQL(sql);
		} catch (Exception e) {
			// TODO: handle exception
			result=false;
		}
		return result;
	}
}
