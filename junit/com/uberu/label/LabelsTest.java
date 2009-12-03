package com.uberu.label;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.junit.Assert.assertEquals;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.hamcrest.Matcher;
import org.junit.Test;

import com.uberu.SWTBotTestBase;
import com.uberu.matchers.GetTextGetter;
import com.uberu.matchers.TextEndingWith;
import com.uberu.matchers.TextMatching;
import com.uberu.matchers.TextStartingWith;


public class LabelsTest extends SWTBotTestBase {
	
	@Test
	public void testLabelByContent() throws Exception {
		getBot().label("Label1");
	}
	
	@Test
	public void testLabelById() throws Exception {
		org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences.DEFAULT_KEY = TEST_KEY;
		getBot().labelWithId("label3");
	}
	
	@Test
	public void testLabelStartingWith() throws Exception {
		Matcher<Label> matcher = allOf(widgetOfType(Label.class), new TextStartingWith<Label>("A long label", new GetTextGetter()));
		assertEquals(1, getBot().getFinder().findControls(matcher).size());
	}
	
	@Test
	public void testEndingWith() throws Exception {
		Matcher<Label> matcher = allOf(widgetOfType(Label.class), new TextEndingWith<Label>("contents to check from", new GetTextGetter()));
		assertEquals(1, getBot().getFinder().findControls(matcher).size());
	}
	
	@Test
	public void testLabelContaining() throws Exception {
		Matcher<Label> matcher = allOf(widgetOfType(Label.class), new TextContaining<Label>("will have text", new GetTextGetter()));
		assertEquals(1, getBot().getFinder().findControls(matcher).size());
	}
	
	@Test
	public void testLabelMatchingRegex() throws Exception {
		Matcher<Label> matcher = allOf(widgetOfType(Label.class), new TextMatching<Label>("^A.+from$", new GetTextGetter()));
		assertEquals(1, getBot().getFinder().findControls(matcher).size());
	}
//	
//	@Test
//	public void testLabelInGroupByContent() throws Exception {
//		getBot().labelInGroup("Label2 In Group", "Group1");
//	}
	

	@Override
	public void createAndShowTestedGui(Display display) {
		new LabelGui(display).open();
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}

}


class LabelGui extends Dialog {

	public LabelGui(Display display) {
		this(new Shell(display));
	}

	protected LabelGui(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		composite.setLayout(new GridLayout(1, false));

		Label label1 = new Label(composite, SWT.NONE);
		label1.setText("Label1");
		
		Group group = new Group(composite, SWT.NONE);
		group.setText("Group1");
		Label label2 = new Label(composite, SWT.NONE);
		label2.setText("Label2 In Group");
		
		Label label3 = new Label(composite, SWT.NONE);
		label3.setText("LabelById");
		label3.setData(LabelsTest.TEST_KEY, "label3");
		
		Label label4 = new Label(composite, SWT.NONE);
		label4.setText("A long label that will have text contents to check from");
		
		return composite;
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == OK) {
			close();
		} else {
			super.buttonPressed(buttonId);
		}
	}

}

