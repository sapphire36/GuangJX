package org.kzcw.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.kzcw.common.Iot.youren.YouRenManger;
import org.kzcw.common.utils.SystemData;
import org.kzcw.model.Lockdevice;
import org.kzcw.service.LockdeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system")
public class SystemManager {
    //系统设置管理
	@Autowired
	LockdeviceService lockservice;
	
	
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String indexview(ModelMap map,HttpServletRequest request){
		return "/system/setting";
	}
	
    @RequestMapping(value="/restart",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> restart(ModelMap map,HttpServletRequest request){
		//重启监听
    	
    	Map<String,String> result=new HashMap<String,String>();
        YouRenManger manager=YouRenManger.getInstance();
        SystemData data=SystemData.getInstance();
        List<Lockdevice> list;
        list=lockservice.list();
        if(list!=null) {
        	data.locklist.clear();
        	for(Lockdevice d:list) {
        		data.locklist.add(d.getIMEI());	
        	}
        }
        
        try {
            new Thread("线程1"){
                @Override
                public void run(){
                  	manager.doStart();
                }
            }.start();
        	result.put("data","true");
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data","false");
		}
        return result;
    }
}
