/* �ۼ��� : ���϶�,���ο�,�̽���
 * �ۼ��� : 2017-05-27
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

	setTitle("ID �Է�â");

	nickname_textField = new JTextField();
	nickname_textField.setFont(new Font("����", Font.PLAIN, 25));
	nickname_textField.setBounds(192, 41, 171, 36);
	contentPane.add(nickname_textField);
	nickname_textField.setColumns(10);

	JLabel nickname_label = new JLabel("�г��� : ");
	nickname_label.setFont(new Font("����", Font.PLAIN, 25));
	nickname_label.setBounds(64, 40, 96, 30);
	contentPane.add(nickname_label);

	JButton ok_btn = new JButton("Ȯ��");
	ok_btn.setFont(new Font("����", Font.PLAIN, 25));
	ok_btn.setBounds(55, 123, 138, 50);
	contentPane.add(ok_btn);

	// ����� id �Է� �ް� Ȯ�� ��ư ������ �ܾ������� ����.
	ok_btn.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		String user_id = nickname_textField.getText();

		if (user_id.equals(""))
		{
		    dispose();
		    JOptionPane.showMessageDialog(null, "ID�� �Է��ϼ���");
		    new SetClientUI();
		}

		else if (user_id.length() > 10)
		{
		    dispose();
		    JOptionPane.showMessageDialog(null, "�Է°����� ���� ���� �ʰ��߽��ϴ�.");
		    new SetClientUI();
		}

		else
		{
		    boolean result = cm.setId(user_id);
		    if (result == false)
		    {
			dispose();
			JOptionPane.showMessageDialog(null, "�ߺ��� ID �Դϴ�.");
			new SetClientUI();
		    }

		    else
		    {
			new ClientMainUI();
		    }
		}
	    }
	});

	JButton cancel_btn = new JButton("���");
	cancel_btn.setFont(new Font("����", Font.PLAIN, 25));
	cancel_btn.setBounds(258, 123, 138, 50);
	contentPane.add(cancel_btn);

	// ��� ��ư ������ ���α׷� ����.
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
