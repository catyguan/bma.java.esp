package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FixInt64Coder extends BaseCoder{
	/**
	 * @throws IOException 
	 * 
	* @Title: fixInt64Encoder 
	* @Description: 定长 int 64位 编码 BigEndian
	* @param @param l
	* @param @return    
	* @return byte[] 8个字节   
	* @throws
	 */
	public static void fixInt64Encoder(OutputStream buf,long l) throws IOException{
		buf.write((byte) (l >> 56));
		buf.write((byte) (l >> 48));
		buf.write((byte) (l >> 40));
		buf.write((byte) (l >> 32));
		buf.write((byte) (l >> 24));
		buf.write((byte) (l >> 16));
		buf.write((byte) (l >> 8));
		buf.write((byte) (l >> 0));
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: fixInt64Dncoder 
	* @Description: 定长 int 64位 解码 BigEndian
	* @param @param b
	* @param @return    
	* @return long    
	* @throws
	 */
	public static long fixInt64Dncoder(InputStream buf) throws IOException{
		long s = 0;
		s += (long)(buf.read() & 0xff) << 56;
		s += (long)(buf.read() & 0xff) << 48;
		s += (long)(buf.read() & 0xff) << 40;
		s += (long)(buf.read() & 0xff) << 32;
		s += (long)(buf.read() & 0xff) << 24;
		s += (long)(buf.read() & 0xff) << 16;
		s += (long)(buf.read() & 0xff) << 8;
		s += (long)(buf.read() & 0xff) << 0;
		return s;
	}

	@Override
	public Object decoder(InputStream buf) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void encoder(OutputStream buf, Object obj) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
