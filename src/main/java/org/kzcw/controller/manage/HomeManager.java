package org.kzcw.controller.manage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.kzcw.common.global.BreakHistoryManager;
import org.kzcw.common.global.CheckLightBoxList;
import org.kzcw.model.Breakhistory;
import org.kzcw.model.Lightbox;
import org.kzcw.model.Module;
import org.kzcw.service.LightboxService;
import org.kzcw.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/home")
public class HomeManager {
	// 主页管理
	@Autowired
	LightboxService lservice;

	@Autowired
	ModuleService mservice;

	@RequestMapping(value = "/getview/index", method = RequestMethod.GET)
	public String indexview(ModelMap map, HttpServletRequest request) {
		return "/home/index";
	}

	@RequestMapping(value = "/getwainning", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getwainning(ModelMap map, HttpServletRequest request) {
		// 获取报警数据
		Map<String, Object> result = new HashMap<String, Object>();
		BreakHistoryManager list = BreakHistoryManager.getInstance();// 获取开锁队列
		Date d = new Date();
		list.addItem(d.toString(), new Breakhistory(d.toString(), "test"));
		int max = 10;
		// 执行刷新操作
		StringBuffer stringBuffer = new StringBuffer();
		Map<String, Breakhistory> sets = list.getMapEntrySet();
		for (Map.Entry<String, Breakhistory> entry : sets.entrySet()) {
			// <i class="fa fa-envelope fa-fw"></i>
			max--;
			if (max < 0) {
				// 最多显示10条记录
				stringBuffer.append("<li class=\"divider\"></li>");
				stringBuffer.append("<li>");
				stringBuffer.append("<a class=\"text-center\" href=\"#\">");
				stringBuffer.append("<strong>显示所有消息</strong>");
				stringBuffer.append("<i class=\"fa fa-angle-right\"></i>");
				stringBuffer.append("</a>");
				stringBuffer.append("</li>");
				break;
			}
			Breakhistory temp=entry.getValue();
			stringBuffer.append("<li>");
			stringBuffer.append("<a href=\"#\">");
			stringBuffer.append("<div>");
			stringBuffer.append("<i class=\"fa fa-comment fa-fw\"></i>"+temp.getIEME()+"|"+temp.getTYPE());
			stringBuffer.append("<span class=\"pull-right text-muted small\">4 min</span>");
			stringBuffer.append("</div>");
			stringBuffer.append("</a>");
			stringBuffer.append("</li>");
		}
		result.put("data", stringBuffer.toString());
		result.put("message", "true");
		// 不执行
		return result;
	}

	@RequestMapping(value = "/getview/addcheck", method = RequestMethod.GET)
	public String addcheck(ModelMap map, HttpServletRequest request) {
		// 单例模式实例化
		CheckLightBoxList checklist = CheckLightBoxList.getInstance();
		Lightbox box = new Lightbox();

		box.setAREANAME("liuzirui");
		box.setNAME("EEDS_1");
		box.setIEME("5645657543502");
		box.setLOCATION("74,23.5");
		checklist.addItem(box);
		return "/home/index";
	}

	@RequestMapping(value = "/additem", method = RequestMethod.GET)
	public String additem(ModelMap map, HttpServletRequest request) {
		// NAME,int CODE,int ISLEAF,String URL,String IMAGE,int PARRENTCODE
		List<Module> list = new ArrayList<Module>();
		list.add(new Module("首页", 1, 0, "/GuangJX/manage/home/getview/index", "fa-qrcode", 0));

		list.add(new Module("监控中心", 2, 0, "", "fa-qrcode", 2));
		list.add(new Module("地图", 3, 1, "/GuangJX/manage/monitor/getview/getmap", "", 2));
		list.add(new Module("数据", 4, 1, "/GuangJX/manage/control/getview/getcontrolview", "", 2));

		list.add(new Module("设备管理", 5, 0, "", "fa-qrcode", 5));
		list.add(new Module("光交箱信息管理", 6, 1, "/GuangJX/manage/device/getview/lightboxlist", "", 5));
		list.add(new Module("光交箱状态管理", 7, 1, "/GuangJX/manage/device/getview/statuslist", "", 5));
		list.add(new Module("NB-IoT锁管理", 8, 1, "/GuangJX/manage/device/getview/lockdevicelist", "", 5));
		list.add(new Module("告警历史表", 9, 1, "/GuangJX/manage/device/getview/breakhistorylist", "", 5));
		list.add(new Module("安装审核列表", 21, 1, "/GuangJX/manage/device/getview/checklist", "", 5));

		list.add(new Module("施工方管理", 10, 0, "", "fa-qrcode", 10));
		list.add(new Module("施工方信息管理", 11, 1, "/GuangJX/manage/constructor/getview/constructorlist", "", 10));
		list.add(new Module("施工方角色管理", 12, 1, "#", "", 10));
		list.add(new Module("施工操作历史", 13, 1, "/GuangJX/manage/constructor/getview/operahistorylist", "", 10));

		list.add(new Module("光交箱改造方管理", 14, 0, "#", "fa-qrcode", 14));
		list.add(new Module("交箱改造方信息管理", 15, 1, "#", "", 14));
		list.add(new Module("光交箱改造方角色管理", 16, 1, "#", "", 14));

		list.add(new Module("系统设置", 17, 0, "", "fa-qrcode", 17));
		list.add(new Module("系统参数设置", 18, 1, "/GuangJX/manage/system/getview/setting", "", 17));
		list.add(new Module("系统日志", 20, 1, "/GuangJX/manage/system/getview/journallist", "", 17));

		list.add(new Module("修改密码", 19, 0, "#", "fa-qrcode", 0));
		// mservice.save(t);
		mservice.deleteAllItem();// 清空表
		for (Module m : list) {
			mservice.save(m);
		}
		return "/home/index";
	}
}