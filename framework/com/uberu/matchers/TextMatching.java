package com.uberu.matchers;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.matchers.AbstractMatcher;
import org.hamcrest.Description;

public class TextMatching<T extends Widget> extends AbstractMatcher<T> {

	private final GetTextGetter textGetter;
	private final String pattern;

	public TextMatching(String pattern, GetTextGetter textGetter) {
		this.pattern = pattern;
		this.textGetter = textGetter;
	}

	@Override
	protected boolean doMatch(Object item) {
		String text = textGetter.extractText(item);
		if (text == null){
			return false;
		}
		return text.matches(pattern);
	}

	@Override
	public void describeTo(Description desc) {
	}

}
