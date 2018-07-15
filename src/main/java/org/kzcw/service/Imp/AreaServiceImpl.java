package org.kzcw.service.Imp;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.model.Area;
import org.kzcw.service.AreaService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("AreaService")
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService{

}
