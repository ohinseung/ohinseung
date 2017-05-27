/* 작성자 : 오하라,김인우,이승현
 * 작성일 : 2017-05-27
 * */
package client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SetClientUI extends JFrame
{
    private JPanel contentPane;
    private JTextField nickname_textField;
    ClientManager cm = new ClientManager();

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
	EventQueue.invokeLater(new Runnable()
	{
	    @Override
	    public void run()
	    {
		try
		{
		    SetClientUI frame = new SetClientUI();
		    frame.setVisible(true);
		} catch (Exception e)
		{
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public SetClientUI()
    {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	setTitle("ID 입력창");

	nickname_textField = new JTextField();
	nickname_textField.setFont(new Font("굴림", Font.PLAIN, 25));
	nickname_textField.setBounds(192, 41, 171, 36);
	contentPane.add(nickname_textField);
	nickname_textField.setColumns(10);

	JLabel nickname_label = new JLabel("닉네임 : ");
	nickname_label.setFont(new Font("굴림", Font.PLAIN, 25));
	nickname_label.setBounds(64, 40, 96, 30);
	contentPane.add(nickname_label);

	JButton ok_btn = new JButton("확인");
	ok_btn.setFont(new Font("굴림", Font.PLAIN, 25));
	ok_btn.setBounds(55, 123, 138, 50);
	contentPane.add(ok_btn);

	// 사용자 id 입력 받고 확인 버튼 누르면 단어장으로 들어간다.
	ok_btn.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		String user_id = nickname_textField.getText();

		if (user_id.equals(""))
		{
		    dispose();
		    JOptionPane.showMessageDialog(null, "ID를 입력하세요");
		    new SetClientUI();
		}

		else if (user_id.length() > 10)
		{
		    dispose();
		    JOptionPane.showMessageDialog(null, "입력가능한 글자 수를 초과했습니다.");
		    new SetClientUI();
		}

		else
		{
		    boolean result = cm.setId(user_id);
		    if (result == false)
		    {
			dispose();
			JOptionPane.showMessageDialog(null, "중복된 ID 입니다.");
			new SetClientUI();
		    }

		    else
		    {
			new ClientMainUI();
		    }
		}
	    }
	});

	JButton cancel_btn = new JButton("취소");
	cancel_btn.setFont(new Font("굴림", Font.PLAIN, 25));
	cancel_btn.setBounds(258, 123, 138, 50);
	contentPane.add(cancel_btn);

	// 취소 버튼 누르면 프로그램 종료.
	cancel_btn.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		dispose();
	    }
	});

	setVisible(true);

	addWindowListener(new WindowAdapter()
	{
	    @Override
	    public void windowClosing(WindowEvent e)
	    {
		ClientManager cm = new ClientManager();
		cm.closeStreams();
	    }
	});
    }
}
