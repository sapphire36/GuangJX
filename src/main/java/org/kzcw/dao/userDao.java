package org.kzcw.dao;
import org.kzcw.core.BaseDao;
import org.kzcw.model.user;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("userDao")
@Component
public class userDao extends BaseDao<user>{

}

