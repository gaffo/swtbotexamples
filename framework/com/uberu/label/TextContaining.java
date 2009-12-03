package com.uberu.label;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.matchers.AbstractMatcher;
import org.hamcrest.Description;

import com.uberu.matchers.GetTextGetter;

public class TextContaining<T extends Widget> extends AbstractMatcher<T> {

	private final GetTextGetter textGetter;
	private final String containing;

	public TextContaining(String containing, GetTextGetter textGetter) {
		this.containing = containing;
		this.textGetter = textGetter;
	}

	@Override
	protected boolean doMatch(Object item) {
		String text = textGetter.extractText(item);
		if (text == null){
			return false;
		}
		return text.contains(containing);
	}

	@Override
	public void describeTo(Description desc) {
	}

}
