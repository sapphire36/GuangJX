package org.kzcw.controller.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.kzcw.common.Iot.utils.CloseMessage;
import org.kzcw.common.Iot.utils.OpenMessage;
import org.kzcw.common.global.CheckLightBoxList;
import org.kzcw.common.global.CloseLockList;
import org.kzcw.common.global.OpenLockList;
import org.kzcw.model.Lightbox;
import org.kzcw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/service")
public class ServiceInterface {

	@Autowired
	UserService userservice;

	@RequestMapping(value = "/openLock", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> openLock(ModelMap map, @RequestParam String UNAME, @RequestParam String EMEI,
			HttpServletRequest request) {
		// 开锁申请
		Map<String, Object> result = new HashMap<String, Object>();
		OpenLockList list = OpenLockList.getInstance();
		OpenMessage message = new OpenMessage(UNAME, EMEI);
		// message;
		String emei = getEMEI(EMEI);
		message.USERNAME = UNAME;
		message.time = new Date().toString();
		message.EMEI = emei;
		list.AddItem(message);
		result.put("data", "申请成功,请耐心等待审核开锁..");
		return result;
	}

	@RequestMapping(value = "/closeLock", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> closeLock(ModelMap map, @RequestParam String UNAME, @RequestParam String EMEI,
			HttpServletRequest request) {
		// 开锁申请
		Map<String, Object> result = new HashMap<String, Object>();
		CloseLockList list = CloseLockList.getInstance();
		CloseMessage message = new CloseMessage(UNAME, EMEI);
		// message;
		String emei = getEMEI(EMEI);
		message.USERNAME = UNAME;
		message.time = new Date().toString();
		message.EMEI = emei;
		list.AddItem(message);
		result.put("data", "关锁成功..");
		return result;
	}

	// 上传图片
	@RequestMapping(value = "/Uploadphoto")
	@ResponseBody
	public Map<String, Object> Uploadphoto(HttpServletRequest request, ModelMap map) {
		// 开锁申请
		Map<String, Object> result = new HashMap<String, Object>();

		String path = request.getRealPath("/upload") + "/";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} // 设置编码
			// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 如果没以下两行设置的话,上传大的文件会占用很多内存，
		// 设置暂时存放的存储室,这个存储室可以和最终存储文件的目录不同
		/**
		 * 原理: 它是先存到暂时存储室，然后再真正写到对应目录的硬盘上， 按理来说当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
		 * 然后再将其真正写到对应目录的硬盘上
		 */
		factory.setRepository(dir);
		// 设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
		factory.setSizeThreshold(1024 * 1024);
		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> list = upload.parseRequest((RequestContext) request);
			FileItem picture = null;
			for (FileItem item : list) {
				// 获取表单的属性名字
				String name = item.getFieldName();
				// 如果获取的表单信息是普通的 文本 信息
				if (item.isFormField()) {
					// 获取用户具体输入的字符串
					String value = item.getString();
					request.setAttribute(name, value);
				} else {
					picture = item;
				}
			}

			// 自定义上传图片的名字为userId.jpg
			String fileName = request.getAttribute("userId") + ".jpg";
			String destPath = path + fileName;

			// 真正写到磁盘上
			File file = new File(destPath);
			OutputStream out = new FileOutputStream(file);
			InputStream in = picture.getInputStream();
			int length = 0;
			byte[] buf = new byte[1024];
			// in.read(buf) 每次读到的数据存放在buf 数组中
			while ((length = in.read(buf)) != -1) {
				// 在buf数组中取出数据写到（输出流）磁盘上
				out.write(buf, 0, length);
			}
			in.close();
			out.close();
		} catch (FileUploadException e1) {
		} catch (Exception e) {
		}

		return result;
	}

	@RequestMapping(value = "/loginin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginin(ModelMap map, HttpServletRequest request) {
		// 登陆
		Map<String, Object> result = new HashMap<String, Object>();
		String NAME = request.getParameter("username");
		String PASSWD = request.getParameter("passwd");
		String UTYPE = request.getParameter("utype");
		// 返回值 0:用户名或密码错误,1:登录成功 2:用户被禁用
		int flag = userservice.doLogin(NAME, PASSWD, Long.parseLong(UTYPE));

		result.put("data", flag);
		return result;
	}
	
	@RequestMapping(value = "/installsubmit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> installsubmit(ModelMap map, HttpServletRequest request) {
		// 登陆
		Map<String, Object> result = new HashMap<String, Object>();
		String coderesult = request.getParameter("coderesult");
		String locationresult = request.getParameter("locationresult");
		String name = request.getParameter("name");
		
		Lightbox box=new Lightbox();
		box.setIEME(coderesult);
		box.setIEME(coderesult);
		box.setLOCATION(locationresult);
		
		CheckLightBoxList lightboxlist=CheckLightBoxList.getInstance();
		lightboxlist.list.add(box);
		
		// 返回值 0:用户名或密码错误,1:登录成功 2:用户被禁用
		boolean codestart=coderesult.startsWith("完成扫码");
		boolean locationstart=locationresult.startsWith("纬度");
		if(codestart==true&&locationstart==true&&name.isEmpty()==false)
		result.put("data", 1);
		else result.put("data", 0);
			
		return result;
	}

	public String getEMEI(String tg) {
		tg = tg.replaceAll(" ", "");
		int i = tg.indexOf("I:");
		int j = tg.indexOf("SN");
		return tg.substring(i + 2, j);
	}
}