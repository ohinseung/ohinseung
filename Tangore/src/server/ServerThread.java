package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import exception.ManagerException;
import vo.Tango;

public class ServerThread implements Runnable {
	
	private serverManager sm = new serverManager();
	private ObjectInputStream nois;
	private ObjectOutputStream noos;
	private boolean exit = false;
	
	public ServerThread(ObjectInputStream nois, ObjectOutputStream noos) {
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
					case "insert":
					System.out.println("어디까지왔나");
					boolean result = sm.insertTango((Tango)ObjectMain);
					noos.writeObject(result);
					break;
					
					case "find_row_id":
					Tango tango_row_id = sm.findTango_row_id((int)ObjectMain);
					noos.writeObject(tango_row_id);
					break;
					
					case "find_hanja":
					Tango tango_hanja = sm.findTango_hanja((String)ObjectMain);
					noos.writeObject(tango_hanja);
					break;
					
					case "find_hiragana":
					Tango tango_hiragana = sm.findTango_hiragana((String)ObjectMain);
					noos.writeObject(tango_hiragana);
					break;
					
					case "delete":
					boolean delete = sm.deleteTango((int)ObjectMain);
					noos.writeObject(delete);
					break;
					
					case "getList":
					ArrayList<Tango> list = sm.getTangoList();
					noos.writeObject(list);
					break;
					
					case "update":
					noos.writeObject(sm.updateTango((Tango)ObjectMain));
					break;
					
					case "deleteAll":
					boolean deleteAll = sm.deleteAll();
					noos.writeObject(deleteAll);
					break;
				}
			} catch (IOException e) {
				exit = true;
				e.printStackTrace();
			} catch (ClassNotFoundException ce) {
				exit = true;
				ce.printStackTrace();
			} catch (ManagerException e) {
				exit = true;
				e.printStackTrace();
			}
		}
	}

}
