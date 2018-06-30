package org.kzcw.dao;

import org.kzcw.core.BaseDao;
import org.kzcw.model.Organization;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("moduleDao")
@Component
public class ModuleDao extends BaseDao<Organization>{

}
