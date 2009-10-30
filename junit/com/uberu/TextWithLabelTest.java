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
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.junit.Test;

public class TextWithLabelTest {

	private TextWithLabelGui gui;
	private Display display;
	private String verify1;
	private String verify2;
	
	@Test
	public void testFillingInFieldByLabel() throws Exception {
		new Thread() {
			public void run() {
				display = new Display();
				gui = new TextWithLabelGui(display);
				gui.open();
			};
		}.start();
		final SWTBot bot = new SWTBot();
		bot.label("Label Text1");
		bot.textWithLabel("Label Text1").setText("WOOT!");
		bot.label("Label Text2");
		bot.textWithLabel("Label Text2").setText("WOOTS!");
		display.asyncExec(new Runnable() {
			public void run() {
				synchronized (bot) {
					try {
						System.out.println("in verify");
						verifyText1();
						verifyText2();
						display.dispose();
					} finally {
						bot.notify();
					}
				}
			}
		});
		synchronized (bot) {
			bot.wait();
		}
		assertEquals("WOOT!", verify1);
		assertEquals("WOOTS!", verify2);
	}

	private void verifyText1() {
		verify1 = gui.getText1Value();
	}

	private void verifyText2() {
		verify2 = gui.getText2Value();
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
		if (buttonId == OK){
			close();
		}
		else{
			super.buttonPressed(buttonId);
		}
	}

}
