package org.kzcw.dao;

import org.kzcw.core.BaseDao;
import org.kzcw.model.Organization;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("organizationDao")
@Component
public class OrganizationDao extends BaseDao<Organization>{

}
