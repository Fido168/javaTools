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
 * @since 2020��11��11��
 * @see this tool is developed for my wife
 * she got a folder which contains more than 4600 photos,
 * and she wants to create  folders for each photo and folder named by photo name
 *so this tool works like this: create folders for each photo,then move photo to matching folder,
 *and folder named by photo name
 */
public class MainFrame implements ActionListener {

	private JFrame frame;
	JTabbedPane tabPane = new JTabbedPane();// ѡ�����  
    Container con = new Container();//  
    JLabel label1 = new JLabel("Directory");  
    JLabel label2 = new JLabel("ѡ���ļ�");  
    JTextField text1 = new JTextField();// TextField Ŀ¼��·��  
    JTextField text2 = new JTextField();// �ļ���·��  
    JButton button1 = new JButton("...");// ѡ��  
    JFileChooser jfc = new JFileChooser();// �ļ�ѡ����  
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
		 jfc.setCurrentDirectory(new File("d://"));// �ļ�ѡ�����ĳ�ʼĿ¼��Ϊd��  
         
	        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
	          
	        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();  
	          
	        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// �趨���ڳ���λ��  
	        frame.setSize(280, 200);// �趨���ڴ�С  
	        frame.setContentPane(tabPane);// ���ò���  
	        label1.setBounds(10, 10, 70, 20);  
	        text1.setBounds(75, 10, 120, 20);  
	        button1.setBounds(210, 10, 50, 20);   
	        button3.setBounds(94, 65, 81, 20);  
	        button1.addActionListener(this); // ����¼�����  
	        button3.addActionListener(this); // ����¼�����  
	        con.add(label1);  
	        con.add(text1);  
	        con.add(button1);   
	        con.add(button3);  
	        frame.setVisible(true);// ���ڿɼ�  
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ʹ�ܹرմ��ڣ���������  
	        tabPane.add("File tool", con);// ��Ӳ���1  
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
     * ʱ������ķ��� 
     */  
    public void actionPerformed(ActionEvent e) {  
        // TODO Auto-generated method stub  
        if (e.getSource().equals(button1)) {// �жϴ��������İ�ť���ĸ�  
            jfc.setFileSelectionMode(1);// �趨ֻ��ѡ���ļ���  
            int state = jfc.showOpenDialog(null);// �˾��Ǵ��ļ�ѡ��������Ĵ������  
            if (state == 1) {  
                return;  
            } else {  
                File f = jfc.getSelectedFile();// fΪѡ�񵽵�Ŀ¼  
                text1.setText(f.getAbsolutePath());  
            }  
        }  
        if (e.getSource().equals(button3)) {  
            // �����Ի�����Ըı�����Ĳ�������ÿ�����Լ�ȥ����ʱ��ܶ�  
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
                //�����ļ�Ŀ¼  
                String string = fileList[i];  
                //File("documentName","fileName")��File����һ��������  
                File file = new File(dirFile.getPath(),string);  
                String name = file.getName();                  
                String diName=name.split("\\.")[0];
                System.out.println(diName);
        		File newFile=new File(pathName+diName);
        		if(!newFile.exists()){//����ļ��в�����
        			newFile.mkdir();//�����ļ���
        		}  
        		File endFile=new File(newFile+"\\"+name);
        		file.renameTo(endFile);     				
                }  
        	return fileNumbers;
        }
    	
    }

}
