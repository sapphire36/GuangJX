package org.kzcw.dao;


import org.kzcw.model.Status;
import org.kzcw.core.BaseDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("StatusDao")
@Component
public class StatusDao extends BaseDao<Status>{

}
