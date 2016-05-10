package com.sadaharu.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class SocketServer {

	BufferedWriter writer;
	BufferedReader reader;

	public static void main(String[] args) {
		SocketServer socketServer = new SocketServer();
		socketServer.startServer();
	}

	public void startServer() {
		ServerSocket serverSocket = null;

		// BufferedWriter writer = null;
		Socket socket = null;

		try {
			// 设置服务器端口号
			serverSocket = new ServerSocket(9898);
			System.out.println("server started..");
			while (true) {
				socket = serverSocket.accept(); // 进入阻塞等待连入。
				manageConnection(socket);
			}

			// 测试实时接收消息
			/*
			 * new Timer().schedule(new TimerTask() {
			 * 
			 * @Override public void run() { try { System.out.println("心正在怒跳");
			 * writer.write("heart beat once..\n"); writer.flush(); } catch
			 * (IOException e) { e.printStackTrace(); } } }, 3000, 3000);
			 */

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				serverSocket.close();
				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public void manageConnection(final Socket socket) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					System.out.println("client" + socket.hashCode() + "connedted");
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					String receivedMsg;
					while ((receivedMsg = reader.readLine()) != null) {
						System.out.println("client " + socket.hashCode() + ": " + receivedMsg);
						writer.write("收到： " + receivedMsg + "了" + "\n");
						writer.flush();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						reader.close();
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
