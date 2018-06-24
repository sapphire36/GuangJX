package org.kzcw.service.Imp;

import java.util.List;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.StatusDao;
import org.kzcw.model.Status;
import org.kzcw.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service("StatusService")
@Component
public class StatusServiceImpl extends BaseServiceImpl<Status> implements StatusService{

}
