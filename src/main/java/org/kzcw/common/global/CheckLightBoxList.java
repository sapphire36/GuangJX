package org.kzcw.common.global;

import java.util.ArrayList;
import java.util.List;

import org.kzcw.model.Lightbox;

public class CheckLightBoxList {

	private static CheckLightBoxList instance=new CheckLightBoxList();
	public List<Lightbox> list=null;
	
	public CheckLightBoxList() {
		// TODO Auto-generated constructor stub
		list=new ArrayList<Lightbox>();
	}
	
	
	
	public static CheckLightBoxList getInstance() {
		return instance;
	}
}
