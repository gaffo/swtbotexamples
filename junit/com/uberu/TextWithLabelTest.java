package com.uberu;

import static org.junit.Assert.assertEquals;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.Test;

public class TextWithLabelTest extends SWTBotTestBase {

	private TextWithLabelGui gui;

	@Test
	public void testFillingInFieldByLabel() throws Exception {
		SWTBotText textField1 = getBot().textWithLabel("Label Text1");
		SWTBotText textField2 = getBot().textWithLabel("Label Text2");
		
		textField1.setText("WOOT!");
		textField2.setText("WOOTS!");

		assertEquals("WOOT!", SWTUtils.invokeMethod(textField1, "getText"));
		assertEquals("WOOTS!", SWTUtils.invokeMethod(textField2, "getText"));
	}

	@Override
	public void createAndShowTestedGui(Display display) {
		gui = new TextWithLabelGui(display);
		gui.open();
	}

	@Override
	public void setup() {
	}

}

class TextWithLabelGui extends Dialog {

	private Text textField1;
	private Text textField2;

	public TextWithLabelGui(Display display) {
		this(new Shell(display));
	}

	public String getText1Value() {
		return textField1.getText();
	}

	public String getText2Value() {
		return textField2.getText();
	}

	protected TextWithLabelGui(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		composite.setLayout(new GridLayout(2, false));

		Label textLabel1 = new Label(composite, SWT.NONE);
		textLabel1.setText("Label Text1");

		textField1 = new Text(composite, SWT.BORDER);

		Label textLabel2 = new Label(composite, SWT.NONE);
		textLabel2.setText("Label Text2");

		textField2 = new Text(composite, SWT.BORDER);

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
