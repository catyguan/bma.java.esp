package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.hamcrest.core.IsInstanceOf;

public class FixInt16Coder extends BaseCoder {
	
	/**
	 * @throws IOException 
	 * 
	* @Title: fixInt16Encoder 
	* @Description: 定长 int 16位 编码 BigEndian
	* @param @param s
	* @param @return    
	* @return byte[]  2个字节
	* @throws
	 */
	public static void fixInt16Encoder(OutputStream buf, short s) throws IOException{
		buf.write((byte) (s >> 8));
		buf.write((byte) (s >> 0));
	}
	
	
	/**
	 * @throws IOException 
	 * 
	* @Title: fixInt16Dncoder 
	* @Description: 定长 int 16位 解码 BigEndian
	* @param @param b
	* @param @return    
	* @return short    
	* @throws
	 */
	public static short fixInt16Dncoder(InputStream buf) throws IOException{
		int s = 0;
	    s += (buf.read() & 0xff) << 8;
	    s += (buf.read() & 0xff);
		return (short) s;
	}


	@Override
	public Object decoder(InputStream buf) throws IOException{
		return fixInt16Dncoder(buf);
	}


	@Override
	public void encoder(OutputStream buf, Object obj) throws IOException{
		if (obj instanceof Short) {
			Short s = (Short) obj;
			fixInt16Encoder(buf,s);
			return ;
		}
		throw new IllegalArgumentException("not short type");
	}
}
