package com.yujie.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jdbc.dao.RecordDao;
import com.jdbc.dao.UserDao;
import com.jdbc.impl.RecordDaoImpl;
import com.jdbc.impl.UserDaoImpl;
import com.yujie.bean.Request;
import com.yujie.bean.Response;
import com.yujie.bean.UserTemp;

public class SingleServerThread extends Thread {

	private Socket socket;
	private ServerFunction function = new ServerFunction();
	public static List<UserTemp> userList = new ArrayList<>();
	public UserDao userDao = new UserDaoImpl();
	public RecordDao recordDao = new RecordDaoImpl();
	static String name = "客户端";
	
	ObjectInputStream ois;
	ObjectOutputStream oos;

	public SingleServerThread(Socket socket) throws IOException {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			Request request = null;
			
			while ((request = (Request) ois.readObject()) != null) {
				// 打印以下收到的请求

				
				Response response = new Response();
				switch (request.getType()) {
				case Request.TYPE_REGIST:
					boolean success = function.doRegist(request.getUname(), request.getUpwd());
					String note;
					if (success) {
						note = "注册成功";
						ServerFunction.Notes(request.getUname(), request.getUpwd(),
								socket.getInetAddress().getHostAddress(), note);
					}
					response.setType(Request.TYPE_REGIST);
					response.setSuccess(success);
					oos.writeObject(response);
					oos.flush();
					break;

				case Request.TYPE_LOGIN:
					boolean success1 = function.doLogin(request.getUname(), request.getUpwd(), oos);
					String note1;
					if (success1) {
						note1 = "登录成功：";
						ServerFunction.Notes(request.getUname(), request.getUpwd(),
								socket.getInetAddress().getHostAddress(), note1);
					} else {
						note1 = "账户：" + request.getUname() + "登录失败";
						ServerFunction.Notes(request.getUname(), request.getUpwd(),
								socket.getInetAddress().getHostAddress(), note1);
					}
					name = request.getUname();
					response.setUname(request.getUname());
					response.setType(Request.TYPE_LOGIN);
					response.setSuccess(success1);
					oos.writeObject(response);
					oos.flush();

					Response responses = new Response();
					for (UserTemp ut : userList) {

						ObjectOutputStream os = ut.getOos();
						responses.setType(Request.TYPE_ALLMSG);
						responses.setNums(userList.size());
						String s = "";
						for (UserTemp u : userList) {
							s = s.concat(u.getUname() + "split");
						}
						responses.setNameList(s);
						os.writeObject(responses);
						os.flush();
					}

					break;
				case Request.TYPE_SENDMSG:
					for (UserTemp ut : userList) {
						ObjectOutputStream os = ut.getOos();
						response.setType(Request.TYPE_SENDMSG);
						response.setChatmsg(request.getChatmsg());
						os.writeObject(response);
						os.flush();
					}
					break;
				case Request.TYPE_SINGLELINK:
					for (UserTemp ut : userList) {
						if (request.getUname().equals(ut.getUname())) {
							ObjectOutputStream os = ut.getOos();
							response.setUname(ut.getUname());
							response.setOtherName(request.getOtherName());
							response.setType(Request.TYPE_SINGLELINK);
							os.writeObject(response);
							os.flush();
						}
						if (request.getOtherName().equals(ut.getUname())) {
							ObjectOutputStream os = ut.getOos();
							response.setUname(ut.getUname());
							response.setOtherName(request.getUname());
							response.setType(Request.TYPE_SINGLELINK);
							os.writeObject(response);
							os.flush();
						}

					}
					break;
				case Request.TYPE_SINGLESENDMSG:
					for (UserTemp ut : userList) {
						if (request.getUname().equals(ut.getUname())) {
							ObjectOutputStream os = ut.getOos();
							response.setType(Request.TYPE_SINGLESENDMSG);
							response.setChatmsg(request.getChatmsg());
							os.writeObject(response);
							os.flush();
						}
						if (request.getOtherName().equals(ut.getUname())) {
							ObjectOutputStream os = ut.getOos();
							response.setType(Request.TYPE_SINGLESENDMSG);
							response.setChatmsg(request.getChatmsg());
							os.writeObject(response);
							os.flush();
						}
					}
					break;
				default:
					break;
				}

			}
		} catch (IOException e) {
			System.out.println(name + "掉线了");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
