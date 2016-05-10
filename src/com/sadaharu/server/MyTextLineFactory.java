package com.sadaharu.server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MyTextLineFactory implements ProtocolCodecFactory {
	private MyTextLineDecoder mDecoder;
	private MyTextLineEncoder mEncoder;
	
	private MyTextLineCumulativeDecoder mCumulativeDecoder;//防止数据丢失的新的decoder
	
	public MyTextLineFactory() {
		mDecoder = new MyTextLineDecoder();
		mEncoder = new MyTextLineEncoder();
		mCumulativeDecoder = new MyTextLineCumulativeDecoder();
	}
	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return mCumulativeDecoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return mEncoder;
	}

}
