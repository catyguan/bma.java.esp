package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FixInt32Coder implements BaseCoder{
	
	/**
	 * @throws IOException 
	 * 
	* @Title: fixInt32Encoder 
	* @Description: 定长 int 32位 编码 BigEndian
	* @param @param i
	* @param @return    
	* @return byte[]   4个字节 
	* @throws
	 */
	public static void fixInt32Encoder(OutputStream buf,int i) throws IOException{
		buf.write((byte) (i >> 24));
		buf.write((byte) (i >> 16));
		buf.write((byte) (i >> 8));
		buf.write((byte) (i >> 0));
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: fixInt32Dncoder 
	* @Description: 定长 int 32位 解码 BigEndian
	* @param @param b
	* @param @return    
	* @return int    
	* @throws
	 */
	public static int fixInt32Dncoder(InputStream buf) throws IOException{
		int s = 0;
		s += (buf.read() & 0xff) << 24;
		s += (buf.read() & 0xff) << 16;
		s += (buf.read() & 0xff) << 8;
		s += (buf.read() & 0xff) ;
		return (int) s;
	}

	@Override
	public Object decoder(InputStream buf) throws IOException {
		return fixInt32Dncoder(buf);
	}

	@Override
	public void encoder(OutputStream buf, Object obj) throws IOException {
		if (obj instanceof Integer) {
			Integer i = (Integer) obj;
			fixInt32Encoder(buf,i);
			return ;
		}
		throw new IllegalArgumentException("not int type");	
	}

}
