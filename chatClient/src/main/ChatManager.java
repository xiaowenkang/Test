package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

import view.MainWindow;

public class ChatManager {

	private static final ChatManager cm = new ChatManager();
	MainWindow window;
	private BufferedReader reader;
	private PrintWriter writer;
	private String IP;

	private ChatManager() {
	}

	public static ChatManager getCM() {
		return cm;
	}

	public void setWindow(MainWindow w) {
		this.window = w;
	}

	public void conn(String ip) {
		this.IP = ip;
		new Thread() {
			public void run() {

				try {
					Socket socket = new Socket(IP, 12345);
					window.appendText("已经与服务端建立连接...");
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
					writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

					String line = null;
					while ((line = reader.readLine()) != null) {
						window.appendText("收到：" + line);
					}

					writer.close();
					reader.close();
					writer = null;
					reader = null;

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}.start();
	}

	public void sendMsg(String out) {
		if (writer != null) {
			writer.println(out);
		} else {
			window.appendText("连接中断");
		}
	}

}
