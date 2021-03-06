package org.kzcw.controller.manage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

import org.hibernate.Hibernate;
import org.kzcw.common.global.CheckLightBoxList;
import org.kzcw.common.global.Picdeliver;
import org.kzcw.common.global.SystemData;
import org.kzcw.model.Lightbox;
import org.kzcw.model.Lockdevice;
import org.kzcw.model.Status;
import org.kzcw.service.BreakhistoryService;
import org.kzcw.service.LightboxService;
import org.kzcw.service.LockdeviceService;
import org.kzcw.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.Blob;
@Controller
@RequestMapping("/manage/device")
public class DeviceManager {
	// 箱体信息管理
	@Autowired
	LightboxService lservice;
	// 设备状态历史记录
	@Autowired
	StatusService staservice;
	// 设备锁数据库服务
	@Autowired
	LockdeviceService lockservice;

	// 故障历史表
	@Autowired
	BreakhistoryService breakservice;

	@RequestMapping("/showImage")
	public void showImage(HttpServletResponse response) {
		try {
			Picdeliver deliverpic=Picdeliver.getInstance();
			byte data[]=deliverpic.pic;
			response.setContentType("image/jpeg");
			OutputStream toClient=response.getOutputStream();
			toClient.write(data);
			toClient.flush();
			toClient.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("图片不存在");
		}
	}
	// ************************************箱体信息管理**************************
	// ************************************箱体信息管理**************************
	@RequestMapping(value = "/getview/lightboxlist", method = RequestMethod.GET)
	public String lightboxlist(ModelMap model, HttpServletRequest request) {
		// 获取箱体信息列表
		List<Map> result = new ArrayList<Map>();
		SystemData sysdata=SystemData.getInstance();
		List<Lightbox> lightboxslist = lservice.findByAreaName(sysdata.status);
		if (lightboxslist != null) {
			for (Lightbox box : lightboxslist) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", box.getID());
				map.put("NAME", box.getNAME());
				map.put("IEME", box.getIEME());
				map.put("ADDTIME", box.getADDTIME());
				map.put("ISREGIST", box.getISREGIST());
				map.put("USERNAME", box.getAREANAME());
				// 找到最近的一条上报记录
				Status status = staservice.getRecentRecord(box.getIEME());
				if (status != null) {

					if (status.getDOORSTATUS() == 1) {
						map.put("DOORSTATUS", "开");
					} else {
						map.put("DOORSTATUS", "关");
					}

					if (status.getLOCKSTATUS() == 1) {
						map.put("LOCKSTATUS", "开");
					} else {
						map.put("LOCKSTATUS", "关");
					}
				} else {
					map.put("DOORSTATUS", "未找到上报数据");
					map.put("LOCKSTATUS", "未找到上报数据");
				}
				map.put("ISONLINE", "在线");
				result.add(map);// 添加到结果集中
			}
			model.addAttribute("lightlist", result);
		}
		return "/device/lightboxlist";
		/*
		 * Lightbox inter = lservice.findUniqueByProperty("USERNAME",
		 * SystemData.getInstance().loginname); if
		 * (SystemData.getInstance().status.equals("3")) { for (Lightbox box :
		 * lightboxslist) { Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("ID", box.getID()); map.put("NAME", box.getNAME()); map.put("IEME",
		 * box.getIEME()); map.put("ADDTIME", box.getADDTIME()); map.put("ISREGIST",
		 * box.getISREGIST()); map.put("USERNAME", box.getUSERNAME());
		 * 
		 * // 找到最近的一条上报记录 Status status = staservice.getRecentRecord(box.getIEME()); if
		 * (status != null) {
		 * 
		 * if (status.getDOORSTATUS() == 1) { map.put("DOORSTATUS", "开"); } else {
		 * map.put("DOORSTATUS", "关"); }
		 * 
		 * if (status.getLOCKSTATUS() == 1) { map.put("LOCKSTATUS", "开"); } else {
		 * map.put("LOCKSTATUS", "关"); } } else { map.put("DOORSTATUS", "未找到上报数据");
		 * map.put("LOCKSTATUS", "未找到上报数据"); } map.put("ISONLINE", "在线");
		 * result.add(map);// 添加到结果集中 } } else { Map<String, Object> map = new
		 * HashMap<String, Object>(); map.put("ID", inter.getID()); map.put("NAME",
		 * inter.getNAME()); map.put("IEME", inter.getIEME()); map.put("ADDTIME",
		 * inter.getADDTIME()); map.put("ISREGIST", inter.getISREGIST());
		 * map.put("USERNAME", inter.getUSERNAME()); // 找到最近的一条上报记录 Status status =
		 * staservice.getRecentRecord(inter.getIEME()); if (status != null) {
		 * 
		 * if (status.getDOORSTATUS() == 1) { map.put("DOORSTATUS", "开"); } else {
		 * map.put("DOORSTATUS", "关"); }
		 * 
		 * if (status.getLOCKSTATUS() == 1) { map.put("LOCKSTATUS", "开"); } else {
		 * map.put("LOCKSTATUS", "关"); } } else { map.put("DOORSTATUS", "未找到上报数据");
		 * map.put("LOCKSTATUS", "未找到上报数据"); } map.put("ISONLINE", "在线");
		 * result.add(map);// 添加到结果集中 } model.addAttribute("lightlist", result); return
		 * "/device/lightboxlist";
		 */
	}

	@RequestMapping(value = "/addlightbox", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addlightbox(ModelMap map, HttpServletRequest request) {
		// 添加箱体信息
		Map<String, String> result = new HashMap<String, String>();
		String name = request.getParameter("NAME"); // 获取参数NAME
		String lid = request.getParameter("LOCKID"); // 获取参数LOCKID
		String spe = request.getParameter("SPEC"); // 获取参数SPEC
		String type = request.getParameter("MADETYPE"); // 获取参数MADETYPE
		String loca = request.getParameter("LOCATION"); // 获取参数LOCATION
		String people = request.getParameter("PEOPLE"); // 获取参数PEOPLE
		try {
			Lightbox ld = new Lightbox();
			ld.setNAME(name);
			ld.setIEME(lid);
			ld.setSPEC(spe);
			ld.setMADETYPE(type);
			ld.setLOCATION(loca);
			ld.setPEOPLE(people);
			lservice.save(ld);
			result.put("data", "true"); // 执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", "false");
		}
		return result;
	}

	@RequestMapping(value = "/getlightbox", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getlightbox(ModelMap map, HttpServletRequest request) {
		// 获取箱体信息
		Map<String, String> result = new HashMap<String, String>();
		String ID = request.getParameter("ID"); // 获取参数ID
		try {
			Lightbox ld = lservice.findUniqueByProperty("ID", Long.parseLong(ID));
			if (ld != null) {
				result.put("NAME", ld.getNAME());
				result.put("LOCKID", ld.getIEME());
				result.put("SPEC", ld.getSPEC());
				result.put("MADETYPE", ld.getMADETYPE());
				result.put("LOCATION", ld.getLOCATION());
				result.put("PEOPLE", ld.getNAME());
				result.put("data", "true");
				System.out.println("yes");
			} else {
				System.out.println("not found");
			}

		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", "false");
		}
		return result;
	}

	@RequestMapping(value = "/editlightbox", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> editlightbox(ModelMap map, HttpServletRequest request) {
		// 编辑箱体信息
		Map<String, String> result = new HashMap<String, String>();
		String name = request.getParameter("NAME"); // 获取参数NAME
		String lid = request.getParameter("LOCKID"); // 获取参数EMEI
		String spe = request.getParameter("SPEC"); // 获取参数SPEC
		String type = request.getParameter("MADETYPE"); // 获取参数MADETYPE
		String loca = request.getParameter("LOCATION"); // 获取参数NAME
		String peo = request.getParameter("PEOPLE"); // 获取参数EMEI
		String ID = request.getParameter("ID"); // 获取参数ID
		try {
			Lightbox ld = lservice.findUniqueByProperty("ID", Long.parseLong(ID));
			ld.setNAME(name);
			ld.setIEME(lid);
			ld.setSPEC(spe);
			ld.setMADETYPE(type);
			ld.setLOCATION(loca);
			ld.setPEOPLE(peo);
			lservice.update(ld);
			result.put("data", "true"); // 执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", "false");
		}
		return result;
	}

	@RequestMapping(value = "/deletelightbox", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deletelightbox(ModelMap map, HttpServletRequest request) {
		// 删除箱体信息
		Map<String, String> result = new HashMap<String, String>();
		String ID = request.getParameter("ID"); // 获取参数ID
		try {
			Lightbox ld = lservice.findUniqueByProperty("ID", Long.parseLong(ID));
			lservice.delete(ld);
			result.put("data", "true"); // 执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", "false");
		}
		return result;
	}

	@RequestMapping(value = "/getrephislist", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getrephislist(ModelMap map, HttpServletRequest request) {
		// 获取设备数据上报

		Map<String, String> result = new HashMap<String, String>();
		String IEME = request.getParameter("IEME");
		try {
			List<Status> list = lockservice.getStatusByIEME(IEME);
			list.sort(Comparator.reverseOrder());
			StringBuffer stringBuffer = new StringBuffer();
			for (Status status : list) {
				stringBuffer.append("<tr>");
				stringBuffer.append("<td align=\"center\">" + status.getID() + "</td>");
				stringBuffer.append("<td align=\"center\">" + status.getIEME() + "</td>");
				stringBuffer.append("<td align=\"center\">" + status.getVOLTAGE() + "</td>");
				stringBuffer.append("<td align=\"center\">" + status.getTEMPERATURE() + "</td>");
				if (status.getDOORSTATUS() == 1) {
					stringBuffer.append("<td align=\"center\">开</td>");
				} else {
					stringBuffer.append("<td align=\"center\">关</td>");
				}
				if (status.getUNLOCKSTATUS() == 1) {
					stringBuffer.append("<td align=\"center\">关</td>");
				} else {
					stringBuffer.append("<td align=\"center\">开</td>");
				}
				stringBuffer.append("<td align=\"center\">" + status.getADDTIME() + "</td>");
				stringBuffer.append("</tr>");
			}
			result.put("data", "true"); // 执行成功
			result.put("hiscontent", stringBuffer.toString()); // 执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", "false");
		}
		return result;
	}

	// ************************************光交箱状态管理**************************
	// ************************************光交箱状态管理**************************
	@RequestMapping(value = "/getview/statuslist", method = RequestMethod.GET)
	public String statuslist(ModelMap model, HttpServletRequest request) {
		// 获取设备状态历史记录列表
		model.addAttribute("statuslist", staservice.list());
		return "/device/statuslist";
	}

	@RequestMapping(value = "/getrepstatuslist", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getrepstatuslist(ModelMap map, HttpServletRequest request) {
		// 获取设备数据上报数据

		Map<String, String> result = new HashMap<String, String>();
		String IEME = request.getParameter("IEME");
		try {
			List<Status> list = lockservice.getStatusByIEME(IEME);
			StringBuffer stringBuffer = new StringBuffer();
			for (Status sta : list) {
				stringBuffer.append("<tr>");
				stringBuffer.append("<td align=\"center\">" + sta.getID() + "</td>");
				stringBuffer.append("<td align=\"center\">" + sta.getIEME() + "</td>");
				stringBuffer.append("<td align=\"center\">" + sta.getVOLTAGE() + "</td>");
				stringBuffer.append("<td align=\"center\">" + sta.getTEMPERATURE() + "</td>");
				if (sta.getDOORSTATUS() == 1) {
					stringBuffer.append("<td align=\"center\">开</td>");
				} else {
					stringBuffer.append("<td align=\"center\">关</td>");
				}
				if (sta.getUNLOCKSTATUS() == 1) {
					stringBuffer.append("<td align=\"center\">关</td>");
				} else {
					stringBuffer.append("<td align=\"center\">开</td>");
				}
				stringBuffer.append("<td align=\"center\">" + sta.getADDTIME() + "</td>");
				stringBuffer.append("</tr>");
			}
			result.put("data", "true"); // 执行成功
			result.put("hiscontent", stringBuffer.toString()); // 执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", "false");
		}
		return result;
	}

	// **********************************设备锁管理*******************************
	// **********************************设备锁管理*******************************
	@RequestMapping(value = "/getview/lockdevicelist", method = RequestMethod.GET)
	public String lockdevicelist(ModelMap map, HttpServletRequest request) {
		// 获取锁设备列表
		map.addAttribute("locklist", lockservice.list());
		return "/device/lockdevicelist";
	}

	@RequestMapping(value = "/addlockdevice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addlockdevice(ModelMap map, HttpServletRequest request) {
		// 添加NB-IoT设备
		Map<String, String> result = new HashMap<String, String>();
		String name = request.getParameter("NAME"); // 获取参数NAME
		String emei = request.getParameter("IEME"); // 获取参数IEME
		try {
			Lockdevice ld = new Lockdevice();
			ld.setIEME(emei);
			ld.setNAME(name);
			lockservice.save(ld);
			result.put("data", "true"); // 执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", "false");
		}
		return result;
	}

	@RequestMapping(value = "/getlockdevice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getlockdevice(ModelMap map, HttpServletRequest request) {
		// 获取NB-IoT设备
		Map<String, String> result = new HashMap<String, String>();
		String ID = request.getParameter("ID"); // 获取参数ID
		try {
			Lockdevice ld = lockservice.findUniqueByProperty("ID", Long.parseLong(ID));
			if (ld != null) {
				result.put("NAME", ld.getNAME());
				result.put("IEME", ld.getIEME());
				result.put("data", "true");
				System.out.println("yes");
			} else {
				System.out.println("not found");
			}

		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", "false");
		}
		return result;
	}

	@RequestMapping(value = "/editlockdevice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> editlockdevice(ModelMap map, HttpServletRequest request) {
		// 编辑NB-IoT设备
		Map<String, String> result = new HashMap<String, String>();
		String name = request.getParameter("NAME"); // 获取参数NAME
		String emei = request.getParameter("IEME"); // 获取参数EMEI
		String ID = request.getParameter("ID"); // 获取参数ID
		try {
			Lockdevice ld = lockservice.findUniqueByProperty("ID", Long.parseLong(ID));
			ld.setIEME(emei);
			ld.setNAME(name);
			lockservice.update(ld);
			result.put("data", "true"); // 执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", "false");
		}
		return result;
	}

	@RequestMapping(value = "/deletelockdevice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deletelockdevice(ModelMap map, HttpServletRequest request) {
		// 删除NB-IoT设备
		Map<String, String> result = new HashMap<String, String>();
		String ID = request.getParameter("ID"); // 获取参数ID
		try {
			Lockdevice ld = lockservice.findUniqueByProperty("ID", Long.parseLong(ID));
			lockservice.delete(ld);
			result.put("data", "true"); // 执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", "false");
		}
		return result;
	}

	@RequestMapping(value = "/getstatuslist", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getstatuslist(ModelMap map, HttpServletRequest request) {
		// 获取设备数据上报数据

		Map<String, String> result = new HashMap<String, String>();
		String IEME = request.getParameter("IEME");
		try {
			List<Status> list = lockservice.getStatusByIEME(IEME);
			list.sort(Comparator.reverseOrder());
			StringBuffer stringBuffer = new StringBuffer();
			for (Status sta : list) {
				stringBuffer.append("<tr>");
				stringBuffer.append("<td align=\"center\">" + sta.getID() + "</td>");
				stringBuffer.append("<td align=\"center\">" + sta.getIEME() + "</td>");
				stringBuffer.append("<td align=\"center\">" + sta.getVOLTAGE() + "</td>");
				stringBuffer.append("<td align=\"center\">" + sta.getTEMPERATURE() + "</td>");
				if (sta.getDOORSTATUS() == 1) {
					stringBuffer.append("<td align=\"center\">开</td>");
				} else {
					stringBuffer.append("<td align=\"center\">关</td>");
				}
				if (sta.getUNLOCKSTATUS() == 1) {
					stringBuffer.append("<td align=\"center\">关</td>");
				} else {
					stringBuffer.append("<td align=\"center\">开</td>");
				}
				stringBuffer.append("<td align=\"center\">" + sta.getADDTIME() + "</td>");
				stringBuffer.append("</tr>");
			}
			result.put("data", "true"); // 执行成功
			result.put("content", stringBuffer.toString()); // 执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", "false");
		}
		return result;
	}

	// ************************************故障历史表**************************
	// ************************************故障历史表**************************
	@RequestMapping(value = "/getview/breakhistorylist", method = RequestMethod.GET)
	public String breakhistorylist(ModelMap model, HttpServletRequest request) {
		// 获取设备状态历史记录列表
		model.addAttribute("breaklist", breakservice.list());
		return "/device/breakhistorylist";
	}

	// ************************************安装审核队列管理**************************
	// ************************************安装审核队列管理**************************
	@RequestMapping(value = "/getview/checklist", method = RequestMethod.GET)
	public String getcontrolview(ModelMap model, HttpServletRequest request) {
		// 控制界面
		return "/device/checklist";
	}

	@RequestMapping(value = "/getchecklist", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getchecklist(ModelMap map, HttpServletRequest request) {
		// 获取审核队列
		Map<String, Object> result = new HashMap<String, Object>();
		// 单例模式实例化
		CheckLightBoxList list = CheckLightBoxList.getInstance();// 获取开锁队列
		// 执行刷新操作
		StringBuffer stringBuffer = new StringBuffer();
		int index = 0;
		for (Lightbox x : list.checklist) {
			stringBuffer.append(
					"<a href=\"#\" class=\"list-group-item\" data-toggle=\"modal\" data-backdrop=\"static\" data-target=\"#docheck\"> ");
			stringBuffer.append("<span class=\"badge\">");
			stringBuffer.append(new Date().toString());
			stringBuffer.append("</span><i class=\"fa fa-fw fa-comment\">");
			stringBuffer.append(x.getNAME() + ":" + x.getIEME() + ":" + x.getLOCATION());
			stringBuffer.append("</i></a>");
			index++;
		}
		result.put("data", stringBuffer.toString());
		// 不执行
		return result;
	}

	@RequestMapping(value = "/doPassCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doPassCheck(ModelMap map, HttpServletRequest request) {
		// 通过审核
		Map<String,Object> result=new HashMap<String,Object>();
		// 单例模式实例化
		//editlightboxname editlockid editspec editmadetype editlocation editpeople
		try {
			String editlightboxname=request.getParameter("editlightboxname");
			String editlockid=request.getParameter("editlockid");
			String editspec=request.getParameter("editspec");
			String editmadetype=request.getParameter("editmadetype");
			String editlocation=request.getParameter("editlocation");
			String editpeople=request.getParameter("editpeople");
			CheckLightBoxList list=CheckLightBoxList.getInstance();//获取开锁队列
			Lightbox box=list.delItemByEMEI(editlockid);
			Picdeliver deliverpic=Picdeliver.getInstance();
			byte data[]=deliverpic.pic;
			//Blob photoblob=SerialBlob(data);
			String belief=data.toString();
			if(box!=null) {			
				SystemData sysdata=SystemData.getInstance();
				box.setIEME(editlockid);
				box.setNAME(editlightboxname); 
				box.setSPEC(belief); 
				box.setMADETYPE(editmadetype); 
				box.setLOCATION(editlocation);
				box.setPEOPLE(editpeople); 
				box.setAREANAME(sysdata.status);
				lservice.save(box);
				result.put("data", "true");
			}else {
				result.put("data", "异常");
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/doRejectCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doRejectCheck(ModelMap map, HttpServletRequest request) {
		// 拒绝审核
		Map<String,Object> result=new HashMap<String,Object>();
		// 单例模式实例化
		//editlightboxname editlockid editspec editmadetype editlocation editpeople
		try {
			String editlockid=request.getParameter("editlockid");
 
			CheckLightBoxList list=CheckLightBoxList.getInstance();//获取开锁队列
			Lightbox box=list.delItemByEMEI(editlockid);
			if(box!=null) {
				result.put("data", "true");
			}else {
				result.put("data", "异常");
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data", e.getMessage());
		}
		return result;
	}

	// if(checklist.IsFlush) {
	// 执行刷新操作
	// StringBuffer stringBuffer = new StringBuffer();
	// int index=0;
	// for (CheckMessage x : checklist.checklist) {
	// stringBuffer.append("<a href=\"#\" class=\"list-group-item\"
	// data-toggle=\"modal\" data-backdrop=\"static\" data-target=\"#apply\"> ");
	// stringBuffer.append("<span class=\"badge\">");
	// stringBuffer.append(x.NAME+x.time);
	// stringBuffer.append("</span><i class=\"fa fa-fw fa-comment\"></i>");
	// if(x.IsReady)
	// stringBuffer.append("正在审核:"+x.IEME);
	// else
	// stringBuffer.append("审核通过:"+x.IEME);
	// stringBuffer.append("</a>");
	// index++;
	// }
	// result.put("data", stringBuffer.toString());
	// result.put("IsFlush","true");
	// checklist.IsFlush=false;//设置不刷新

	// CheckMessage message = new CheckMessage();
	// message;
	// String ieme = getEMEI(IEME);
	// message.NAME = "tttt";
	// message.IEME = "tttt";
	// message.LOCATION = "tttt";
	// checklist.AddItem(message);
	// }else {
	// //不执行
	// result.put("data","");
	// result.put("IsFlush","false");
	// }
	// return "/device/checklist";
}

// @RequestMapping(value = "/getchecklist", method = RequestMethod.POST)
// @ResponseBody
// public Map<String,Object> getchecklist(ModelMap model,HttpServletRequest
// request){
// //获取审核列表
// Map<String,Object> result=new HashMap<String,Object>();
// CheckLightBoxList list=CheckLightBoxList.getInstance();//获取审核队列
// if(list.IsFlush) {
// //执行刷新操作
// StringBuffer stringBuffer = new StringBuffer();
// int index=0;
// for (CheckMessage x : list.checklist) {
// stringBuffer.append("<a href=\"#\" class=\"list-group-item\"
// data-toggle=\"modal\" data-backdrop=\"static\" data-target=\"#apply\"> ");
// stringBuffer.append("<span class=\"badge\">");
// stringBuffer.append(x.NAME+x.time);
// stringBuffer.append("</span><i class=\"fa fa-fw fa-comment\"></i>");
// if(x.IsReady)
// stringBuffer.append("正在审核:"+x.IEME);
// else
// stringBuffer.append("审核通过:"+x.IEME);
// stringBuffer.append("</a>");
// index++;
// }
// result.put("data", stringBuffer.toString());
// result.put("IsFlush","true");
// list.IsFlush=false;//设置不刷新
// }else {
// //不执行
// result.put("data","");
// result.put("IsFlush","false");
// }
// return result;
// }
//
/*
 * 报警: 1.jquery 控制模态框 2.浮动div
 */
