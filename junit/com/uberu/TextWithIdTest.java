package com.uberu;

import static org.junit.Assert.assertEquals;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.Test;

public class TextWithIdTest extends SWTBotTestBase {

	private TextWithIdGui gui;

	@Test
	public void testFillingInFieldByLabel() throws Exception {
		SWTBotText textField1 = getBot().textWithId("textField1");
		SWTBotText textField2 = getBot().textWithId("textField2");
		
		textField1.setText("WOOT!");
		textField2.setText("WOOTS!");

		assertEquals("WOOT!", SWTUtils.invokeMethod(textField1, "getText"));
		assertEquals("WOOTS!", SWTUtils.invokeMethod(textField2, "getText"));
	}

	@Override
	public void createAndShowTestedGui(Display display) {
		gui = new TextWithIdGui(display);
		gui.open();
	}

	@Override
	public void setup() {
	}

}

class TextWithIdGui extends Dialog {

	private Text textField1;
	private Text textField2;

	public TextWithIdGui(Display display) {
		this(new Shell(display));
	}

	public String getText1Value() {
		return textField1.getText();
	}

	public String getText2Value() {
		return textField2.getText();
	}

	protected TextWithIdGui(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		composite.setLayout(new GridLayout(3, false));

		textField1 = new Text(parent, SWT.BORDER);
		textField1
				.setData(
						org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences.DEFAULT_KEY,
						"textField1");
		textField2 = new Text(parent, SWT.BORDER);
		textField2
				.setData(
						org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences.DEFAULT_KEY,
						"textField2");

		return composite;
	}

}
