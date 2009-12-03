package com.uberu.matchers;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.matchers.AbstractMatcher;
import org.hamcrest.Description;

public class TextEndingWith<T extends Widget> extends AbstractMatcher<T> {

	private final String endingWith;
	private final GetTextGetter textGetter;

	public TextEndingWith(String endingWith, GetTextGetter getTextGetter) {
		this.endingWith = endingWith;
		this.textGetter = getTextGetter;
	}

	@Override
	protected boolean doMatch(Object item) {
		String text = textGetter.extractText(item);
		if (text == null){
			return false;
		}
		return text.endsWith(endingWith);
	}

	@Override
	public void describeTo(Description desc) {
	}

}
