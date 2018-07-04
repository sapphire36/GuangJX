package org.kzcw.controller.manage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.kzcw.common.global.SystemData;
import org.kzcw.common.global.YouRenManger;
import org.kzcw.model.Lockdevice;
import org.kzcw.service.LockdeviceService;
import org.kzcw.service.SystemlogsService;
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
	
	@Autowired
	SystemlogsService systemservice;

	// ************************************系统参数设置**************************
	// ************************************系统参数设置**************************
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
        		data.locklist.add(d.getIEME());	
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


	//************************************系统日志**************************
	// ************************************系统日志**************************
	@RequestMapping(value = "/getview/journallist", method = RequestMethod.GET)
	public String onstructlist(ModelMap map,HttpServletRequest request){
		//获取系统日志
		map.addAttribute("journallist",systemservice.list());
		return "system/journallist";
	}
	
	@RequestMapping(value = "/deletealljournal", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deletealljournal(ModelMap map, HttpServletRequest request) {
		// 清空数据表
		Map<String, String> result = new HashMap<String, String>();		
		try {
			boolean flag = systemservice.deleteAll();
			if(flag) {
				result.put("ret", "true"); // 执行成功
			}else {
				result.put("ret", "false"); // 执行失败
			}

		} catch (Exception e) {
			// TODO: handle exception
			result.put("ret", "false");
		}
		return result;
	}
}
