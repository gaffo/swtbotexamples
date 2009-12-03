package com.uberu.matchers;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.matchers.AbstractMatcher;
import org.hamcrest.Description;

public class TextStartingWith<T extends Widget> extends AbstractMatcher<T> {

	private final GetTextGetter textGetter;
	private final String startingWith;

	public TextStartingWith(String startingWith, GetTextGetter textGetter) {
		this.startingWith = startingWith;
		this.textGetter = textGetter;
	}

	@Override
	protected boolean doMatch(Object item) {
		String text = textGetter.extractText(item);
		if (text == null){
			return false;
		}
		return text.startsWith(startingWith);
	}

	@Override
	public void describeTo(Description desc) {
	}

}
