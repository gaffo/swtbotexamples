package com.uberu.matchers;

import org.eclipse.swtbot.swt.finder.utils.SWTUtils;

public class GetTextGetter implements ITextGetter {

	public GetTextGetter() {
	}

	public String extractText(Object item) {
		String text;
		try {
			text = ((String) SWTUtils.invokeMethod(item, "getText"));
		} catch (Exception e){
			return null;
		}
		return text;
	}

}
