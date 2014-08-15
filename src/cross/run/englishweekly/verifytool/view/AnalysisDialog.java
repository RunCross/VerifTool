package cross.run.englishweekly.verifytool.view;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import cross.run.englishweekly.verifytool.analysis.Analysis;
import cross.run.englishweekly.verifytool.analysis.Analysis.AnalysisListener;
import cross.run.englishweekly.verifytool.analysis.Warning;

public class AnalysisDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 允许关闭
	 */
	private boolean canClose = false;
	/**
	 * 文件夹路径
	 */
	private String path;

	private JTextArea showText;

	public AnalysisDialog(Frame owner, boolean modal, String path) {
		super(owner, modal);
		this.path = path;
		init();
	}

	private void init() {
		showText = new JTextArea();
		Font txtFont = new Font("微软雅黑", Font.PLAIN, 15);
		showText.setFont(txtFont);
		showText.setEditable(false);
		// add(showText);
		setContentPane(showText);
		// 激活窗口事件
		this.enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		// setUndecorated(true);
		setSize(720, 280);
		// pack();
		change2Center();

		new Analysis(path, new AnalysisListener() {

			@Override
			public void outError(String error) {
				appendText(error);
			}

			@Override
			public void onStrart() {
				appendText(Warning.ANALYSIS_START);
			}

			@Override
			public void onProgress(int progress, String str) {
				appendText(str);
			}

			@Override
			public void onEnd() {
				appendText(Warning.ANALYSIS_END);
				canClose = true;
			}
		}).start();

	}

	/**
	 * 置于屏幕中间
	 */
	private void change2Center() {
		// 取屏幕大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		// 置于屏幕中央
		setLocation(screenWidth / 4, screenHeight / 4);
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING && !canClose) {
			return; // 直接返回，阻止默认动作，阻止窗口关闭
		}
		super.processWindowEvent(e); // 该语句会执行窗口事件的默认动作(如：隐藏)
	}

	public void appendText(String str) {
		showText.append(str);
	}
}
