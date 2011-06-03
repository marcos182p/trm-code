package trm.view.game.utils;

import java.util.HashMap;
import java.util.Map;

public class ResourceWindow {
	public static final String BG_IMAGE = "bg";
	public static final String PANEL_IMAGE = "panel";
	
	private static Map<String, String> images = new HashMap<String, String>();
	
	private ResourceWindow() {
		
	}
	
	public static void setBG(String bgImage){
		images.put(BG_IMAGE, bgImage);
	}
	public static void setPanelImage(String panelImage) {
		images.put(PANEL_IMAGE, panelImage);
	}
	public static String getResourceName(String field) {
		return images.get(field);
	}
}
