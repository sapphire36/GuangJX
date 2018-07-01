package org.kzcw.controller.manage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.kzcw.common.Iot.SystemData;
import org.kzcw.common.Iot.youren.YouRenManger;
import org.kzcw.model.Lockdevice;
import org.kzcw.service.LockdeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/system")
public class SystemManager {
    //系统设置管理
	@Autowired
	LockdeviceService lockservice;
	
	
	@RequestMapping(value = "/getview/setting", method = RequestMethod.GET)
	public String indexview(ModelMap map,HttpServletRequest request){
		return "/system/setting";
	}
	
    @RequestMapping(value="/restart",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> restart(ModelMap map,HttpServletRequest request){
		//重启监听
    	
    	Map<String,String> result=new HashMap<String,String>();
        YouRenManger manager=YouRenManger.getInstance();
        String isALL=request.getParameter("ISALL");
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
                	if(isALL.equals("true")) {
                		//订阅该用户下所有设备
                		manager.doStart(true);
                	}else {
                		//只订阅已注册的设备
                		manager.doStart(false);
                	}
                }
            }.start();
        	result.put("data","true");
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data","false");
		}
        return result;
    }
    
    @RequestMapping(value="/stop",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> stop(ModelMap map,HttpServletRequest request){
		//关闭监听
    	
    	Map<String,String> result=new HashMap<String,String>();
        YouRenManger manager=YouRenManger.getInstance();
    	try {
			manager.doDisConnect();
		   	result.put("data","true");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("data","false");
		}
        return result;
    }
}
