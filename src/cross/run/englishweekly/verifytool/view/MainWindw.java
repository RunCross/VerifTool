package cross.run.englishweekly.verifytool.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * 选择文件夹界面
 * 
 * @author gjyuan
 * 
 */
public class MainWindw extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainWindw() {
		init();
	}

	JTextField label;
	JFileChooser jfc;
	JButton btn;
	File file;
	AnalysisDialog loadingDialog;

	/**
	 * 初始化
	 */
	private void init() {
		setSize(480, 120);
		
		label = new JTextField("文件目录");
		btn = new JButton("选择目录");		
		
		// 文件选择器
		jfc = new JFileChooser();
		//获取运行路径
		String all = System.getProperty("user.dir");
		//解析盘符
		int index = all.indexOf(File.separator);
		String location = all.substring(0,index)+File.separator;
		// 文件选择器的初始目录定为文件所在盘
		jfc.setCurrentDirectory(new File(location));  
		
		//设置字体颜色
		label.setCaretColor(Color.BLACK);
		Font txtFont = new Font("微软雅黑", Font.BOLD, 20);
		label.setFont(txtFont);
		label.setEditable(false);
		
		btn.setFont(txtFont);
		
		add(label,BorderLayout.NORTH);
		add(btn,BorderLayout.CENTER);

		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jfc.setFileSelectionMode(1);// 设定只能选择到文件夹  
				int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句 
				if (state == 1) {  
	                return;  
	            } else {  
	                // f为选择到的目录  
	                file = jfc.getSelectedFile();
	                label.setText(file.getAbsolutePath());
	                annlysis();
	            }  
			}
		});
		/**
		 * 关闭窗口则结束程序
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				System.exit(0);
			}

			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});

		change2Center();
		setVisible(true);
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
//		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
	}

	/**
	 * 开始解析
	 */
	private void annlysis(){
		if(loadingDialog!=null){
			loadingDialog.dispose();
			loadingDialog = null;			
		}
		loadingDialog = new AnalysisDialog(this, true,file.getPath());
		loadingDialog.setVisible(true);		
	}
}
