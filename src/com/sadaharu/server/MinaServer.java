package com.sadaharu.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {
	public static void main(String[] args) {
		try {
			NioSocketAcceptor acceptor = new NioSocketAcceptor();
			acceptor.setHandler(new MyServerHandler()); // ������Ϣ���뵽Handler��ȥʵ��
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyTextLineFactory())); // ������(Factory��mina�Լ�ʵ�ֵ�)
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 5);// ���ÿ���״̬ʱ��

			acceptor.bind(new InetSocketAddress(9898));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
