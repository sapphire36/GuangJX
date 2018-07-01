package org.kzcw.service.Imp;
import org.kzcw.core.BaseServiceImpl;
import org.kzcw.model.Status;
import org.kzcw.service.StatusService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service("StatusService")
@Component
public class StatusServiceImpl extends BaseServiceImpl<Status> implements StatusService{

	@Override
	public boolean deleteAllItem() {
		//清空数据表
		String sql="delete from t_status";
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
