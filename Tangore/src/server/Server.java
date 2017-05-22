package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		int port = 9900;
		
		try {
			ServerSocket ssocket = new ServerSocket(port);
			System.out.println("������ Ŭ���̾�Ʈ�� ��ٸ��� ��");
			
			while(true) {
				Socket socket = ssocket.accept();
				
				ObjectInputStream nois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream noos = new ObjectOutputStream(socket.getOutputStream());
				
				ServerThread thread = new ServerThread(nois, noos);
				Thread t = new Thread(thread);
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
