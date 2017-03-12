package main;

import java.awt.EventQueue;

import view.MainWindow;

public class MyChatClient {

	public MyChatClient() {
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					ChatManager.getCM().setWindow(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
