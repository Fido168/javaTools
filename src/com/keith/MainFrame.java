package com.keith;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener; 


/**
 * 
 * @author Keith
 * @since 2020年11月11日
 * @see this tool is developed for my wife
 * she got a folder which contains more than 4600 photos,
 * and she wants to create  folders for each photo and folder named by photo name
 *so this tool works like this: create folders for each photo,then move photo to matching folder,
 *and folder named by photo name
 */
public class MainFrame implements ActionListener {

	private JFrame frame;
	JTabbedPane tabPane = new JTabbedPane();// 选项卡布局  
    Container con = new Container();//  
    JLabel label1 = new JLabel("Directory");  
    JLabel label2 = new JLabel("选择文件");  
    JTextField text1 = new JTextField();// TextField 目录的路径  
    JTextField text2 = new JTextField();// 文件的路径  
    JButton button1 = new JButton("...");// 选择  
    JFileChooser jfc = new JFileChooser();// 文件选择器  
    JButton button3 = new JButton("Execute");// 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		 jfc.setCurrentDirectory(new File("d://"));// 文件选择器的初始目录定为d盘  
         
	        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
	          
	        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();  
	          
	        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// 设定窗口出现位置  
	        frame.setSize(280, 200);// 设定窗口大小  
	        frame.setContentPane(tabPane);// 设置布局  
	        label1.setBounds(10, 10, 70, 20);  
	        text1.setBounds(75, 10, 120, 20);  
	        button1.setBounds(210, 10, 50, 20);   
	        button3.setBounds(94, 65, 81, 20);  
	        button1.addActionListener(this); // 添加事件处理  
	        button3.addActionListener(this); // 添加事件处理  
	        con.add(label1);  
	        con.add(text1);  
	        con.add(button1);   
	        con.add(button3);  
	        frame.setVisible(true);// 窗口可见  
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 使能关闭窗口，结束程序  
	        tabPane.add("File tool", con);// 添加布局1  
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
		
	}
	
	/** 
     * 时间监听的方法 
     */  
    public void actionPerformed(ActionEvent e) {  
        // TODO Auto-generated method stub  
        if (e.getSource().equals(button1)) {// 判断触发方法的按钮是哪个  
            jfc.setFileSelectionMode(1);// 设定只能选择到文件夹  
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
            if (state == 1) {  
                return;  
            } else {  
                File f = jfc.getSelectedFile();// f为选择到的目录  
                text1.setText(f.getAbsolutePath());  
            }  
        }  
        if (e.getSource().equals(button3)) {  
            // 弹出对话框可以改变里面的参数具体得靠大家自己去看，时间很短  
        	String foderPath=text1.getText()+ "\\" ;
        	int result = copyToFolder(foderPath);
            JOptionPane.showMessageDialog(null, result + "files were moved", "Info", 2);  
        }  
    }  
	
    int copyToFolder(String pathName){
		File dirFile = new File(pathName); 
		int fileNumbers=0;
		if (!dirFile.exists()) {  
            System.out.println("do not exit");  
            return fileNumbers;  
        } else{
        	System.out.println("Directory exit"); 
        	String[] fileList = dirFile.list();  
        	 fileNumbers= fileList.length;
        	for (int i = 0; i < fileNumbers; i++) {  
                //遍历文件目录  
                String string = fileList[i];  
                //File("documentName","fileName")是File的另一个构造器  
                File file = new File(dirFile.getPath(),string);  
                String name = file.getName();                  
                String diName=name.split("\\.")[0];
                System.out.println(diName);
        		File newFile=new File(pathName+diName);
        		if(!newFile.exists()){//如果文件夹不存在
        			newFile.mkdir();//创建文件夹
        		}  
        		File endFile=new File(newFile+"\\"+name);
        		file.renameTo(endFile);     				
                }  
        	return fileNumbers;
        }
    	
    }

}
