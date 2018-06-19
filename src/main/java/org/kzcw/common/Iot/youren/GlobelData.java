package org.kzcw.common.Iot.youren;

public class GlobelData {

	public static GlobelData instance=null;
	
	public String newstring="";

	public static GlobelData getInstance() {
		if (instance == null) {
			instance=new GlobelData();
		}
		return instance;
	}
}
