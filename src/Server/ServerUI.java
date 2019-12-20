package Server;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/*������Ƿ������˵�UI*/
public class ServerUI extends JFrame {
    public static void main(String[] args) {
        ServerUI serverUI = new ServerUI();
    }

    public JButton btStart;//����������
    public JButton btSend;//������Ϣ��ť
    public JTextField tfSend;//��Ҫ���͵��ı���Ϣ
    public JTextArea taShow;//��Ϣչʾ
    public Server server;//���������ͻ�������
    static List<Socket> clients;//�������ӵ��������Ŀͻ���

    public ServerUI() {
        super("�����ҷ�����");
        btStart = new JButton("���������ҷ���");
        btSend = new JButton("���͹㲥��Ϣ");
        tfSend = new JTextField(10);
        taShow = new JTextArea();

        btStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                server = new Server(ServerUI.this);
            }
        });
        btSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                server.sendMsg(tfSend.getText());
                tfSend.setText("");
            }
        });
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "ȷ���رգ�", "��ʾ",
                        JOptionPane.YES_NO_OPTION);
                if (a == 1) {
                    server.closeServer();
                    System.exit(0); // �ر�
                }
            }
        });
        JPanel top = new JPanel(new FlowLayout());
        top.add(btStart);
        top.add(tfSend);
        top.add(btSend);
        this.add(top, BorderLayout.SOUTH);
        final JScrollPane sp = new JScrollPane();
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setViewportView(this.taShow);
        this.taShow.setEditable(false);
        this.add(sp, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setLocation(100, 200);
        this.setVisible(true);
    }
}