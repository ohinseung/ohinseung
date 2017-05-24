package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientThread implements Runnable {
	
	private ObjectInputStream nois;
	private ObjectOutputStream noos;
	private boolean exit = false;
	
	public ClientThread (ObjectInputStream nois, ObjectOutputStream noos) {
		this.nois = nois;
		this.noos = noos;
	}
	@Override
	public void run() {
		while(!exit) {
			try {
				
				Object[] readOb = (Object[])nois.readObject();
				String ObjectName = (String) readOb[0];
				Object ObjectMain = readOb[1];
				
				switch(ObjectName) {
				
				case "quiz":
				break;
				
				case "score":
				break;
				
				case "winner":
				break;
				}
			} catch (Exception e) {
				exit = true;
				e.printStackTrace();
			}
		}
		
	}
}
