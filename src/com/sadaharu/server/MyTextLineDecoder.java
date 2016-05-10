package com.sadaharu.server;

import java.awt.Button;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MyTextLineDecoder implements ProtocolDecoder {

	@Override
	public void decode(IoSession seesion, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		int startPosition = in.position();
		while (in.hasRemaining()) {
			byte b = in.get();
			if (b == '\n') {
				int currentPosition = in.position();
				int limit = in.limit();
				in.position(startPosition);
				in.limit(currentPosition);
				IoBuffer buf = in.slice();
				byte [] dest = new byte[buf.limit()];
				buf.get(dest);
				String string = new String(dest);
				out.write(string);
				in.position(currentPosition); //还原操作，防止死循环
				in.limit(limit); //还原操作，防止死循环
			}
		}
	}

	@Override
	public void dispose(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1) throws Exception {
		// TODO Auto-generated method stub

	}

}
