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
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.junit.Test;

public class TextWithIdTest {

	private TextWithIdGui gui;
	private Display display;
	private String verify1;
	private String verify2;

	@Test
	public void testFillingInFieldByLabel() throws Exception {
		new Thread() {
			public void run() {
				display = new Display();
				gui = new TextWithIdGui(display);
				gui.open();
			};
		}.start();
		final SWTBot bot = new SWTBot();
		bot.textWithId("textField1").setText("WOOT!");
		bot.textWithId("textField2").setText("WOOTS!");
//		display.asyncExec(new Runnable() {
//			public void run() {
//				synchronized (bot) {
//					try {
//						System.out.println("in verify");
//						verifyText1();
//						verifyText2();
//						display.dispose();
//					} finally {
//						bot.notify();
//					}
//				}
//			}
//		});
//		synchronized (bot) {
//			bot.wait();
//		}
//		assertEquals("WOOT!", verify1);
//		assertEquals("WOOTS!", verify2);
	}

	private void verifyText1() {
		verify1 = gui.getText1Value();
	}

	private void verifyText2() {
		verify2 = gui.getText2Value();
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
		textField1.setData(org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences.DEFAULT_KEY, "textField1");
		textField2 = new Text(parent, SWT.BORDER);
		textField1.setData(org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences.DEFAULT_KEY, "textField2");

		return composite;
	}

}
