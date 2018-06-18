package org.kzcw.service;
import org.kzcw.core.BaseService;
import org.kzcw.model.user;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("userService")
public interface userService extends BaseService<user>{

}
