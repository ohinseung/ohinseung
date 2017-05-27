package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import exception.ManagerException;
import vo.Tango;

public class ServerThread implements Runnable
{

	private serverManager		sm		= new serverManager();
	private ObjectInputStream	nois;
	private ObjectOutputStream	noos;
	private boolean				exit	= false;

	public ServerThread(ObjectInputStream nois, ObjectOutputStream noos)
	{
		this.nois = nois;
		this.noos = noos;
	}

	@Override
	public void run()
	{
		while (!exit)
		{
			try
			{
				Object[] readOb = (Object[]) nois.readObject();
				String ObjectName = (String) readOb[0];
				Object ObjectMain = readOb[1];

				switch (ObjectName)
				{
				case "setId":
					boolean idResult = sm.setId((String) ObjectMain);
					noos.writeObject(idResult);
					System.out.println("[System] ID 등록성공");
					break;
				
				case "insert":
					boolean result = sm.insertTango((Tango) ObjectMain);
					noos.writeObject(result);
					System.out.println("[System] 등록성공");
					break;

				case "find_row_id":
					Tango tango_row_id = sm.findTango_row_id((int) ObjectMain);
					noos.writeObject(tango_row_id);
					System.out.println("[System] row_id 검색 성공");
					break;

				case "find_hanja":
					ArrayList<Tango> tango_hanja = sm.findTango_hanja((String) ObjectMain);
					noos.writeObject(tango_hanja);
					System.out.println("[System] 한자 검색 성공");
					break;

				case "find_hiragana":
					ArrayList<Tango> tango_hiragana = sm.findTango_hiragana((String) ObjectMain);
					noos.writeObject(tango_hiragana);
					System.out.println("[System] 히라가나 검색 성공");
					break;

				case "find_meaning":
					ArrayList<Tango> tango_meaning = sm.findTango_meaning((String) ObjectMain);
					noos.writeObject(tango_meaning);
					System.out.println("[System] 뜻 검색 성공");
					break;

				case "delete":
					boolean delete = sm.deleteTango((int) ObjectMain);
					noos.writeObject(delete);
					System.out.println("[System] 삭제 성공");
					break;

				case "getList":
					ArrayList<Tango> list = sm.getTangoList();
					noos.writeObject(list);
					System.out.println("[System] 전체 리스트 호출 성공");
					break;

				case "update":
					noos.writeObject(sm.updateTango((Tango) ObjectMain));
					System.out.println("[System] 수정 성공");
					break;

				case "getQuizList":
					ArrayList<Tango> quizList = sm.getQuizList((int) ObjectMain);
					noos.writeObject(quizList);
					;
					System.out.println("[System] 퀴즈 리스트 호출 성공");
					break;

				case "deleteAll":
					boolean deleteAll = sm.deleteAll();
					noos.writeObject(deleteAll);
					System.out.println("[System] 전체 삭제 성공");
					break;

				case "data_all":
					int data_all = sm.data_all(0);
					noos.writeObject(data_all);
					System.out.println("[System]DB에 몇개의 자료가 있는지 확인 가능");
					break;
				}
			} catch (IOException e)
			{
				exit = true;
				e.printStackTrace();
			} catch (ClassNotFoundException ce)
			{
				exit = true;
				ce.printStackTrace();
			} catch (ManagerException e)
			{
				exit = true;
				e.printStackTrace();
			}
		}
	}

}
