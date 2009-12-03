package com.uberu;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class SWTBotTestBase {

    private SWTBot bot;
    private static Display display;
    
    public static final String TEST_KEY = "test.key";

    public abstract void createAndShowTestedGui(Display display);

    public abstract void setup();

    @BeforeClass
    public static void initDisplayThreadPool() {
        if (display == null) {
            doInitDisplay();
        }
    }

    public void runOnGuiThread(Runnable runnable){
    	display.syncExec(runnable);
    }
    
    private static void doInitDisplay() {
        final String mutex = "";
        new Thread(new Runnable() {
            
            public void run() {
                synchronized (mutex) {
                    display = Display.getDefault();
                    mutex.notify();
                }
                while (!display.isDisposed()) {
                    if (!display.readAndDispatch()) {
                        display.sleep();
                    }
                }
            }
        }).start();
        synchronized (mutex) {
            try {
                mutex.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Before
    public void createSWTBotAndUI() {
        setup();
        bot = new SWTBot();
        display.asyncExec(new Runnable() {
            public void run() {
                createAndShowTestedGui(Display.getDefault());
            }
        });
    }
    
    @After
    public void closeAllShells(){
    	for (SWTBotShell shell : getBot().shells()){
    		if (shell.isOpen()){
    			shell.close();
    		}
    	}
    }

    public SWTBot getBot() {
        return bot;
    }


}