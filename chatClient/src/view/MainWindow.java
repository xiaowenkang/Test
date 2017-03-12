package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.ChatManager;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ip;
	private JTextField msg;
	private JTextArea txt;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setAlwaysOnTop(true);
		setTitle("聊天客户端");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		txt = new JTextArea();
		txt.setEditable(false);
		txt.setText("ready...");

		ip = new JTextField();
		ip.setText("127.0.0.1");
		ip.setColumns(10);

		JButton connBtn = new JButton("连接到服务器");
		connBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatManager.getCM().conn(ip.getText());
			}
		});

		msg = new JTextField();
		msg.setText("你好！");
		msg.setColumns(10);

		JButton sendBtn = new JButton("发送");
		sendBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatManager.getCM().sendMsg(msg.getText());
				appendText("我说：" + msg.getText());
				msg.setText("");
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(0)
						.addComponent(ip, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE).addGap(84)
						.addComponent(connBtn))
				.addComponent(txt, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(msg, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE).addGap(18)
						.addComponent(sendBtn, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE).addGap(5)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(2).addComponent(ip,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(connBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(18).addComponent(txt, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE).addGap(27)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(msg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(sendBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(8)));
		contentPane.setLayout(gl_contentPane);
	}

	public void appendText(String in) {
		txt.append("\n" + in);
	}

}
