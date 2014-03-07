package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StringCoder extends BaseCoder{
	
	/**
	 * @throws IOException 
	 * 
	* @Title: stringEncoder 
	* @Description: 不带长度 string 编码
	* @param @param s
	* @param @return    
	* @return byte[]    
	* @throws
	 */
	public static void stringEncoder(OutputStream buf,String s) throws IOException{
		byte[] midbytes=s.getBytes(UTF_CODER_TYPE);
		buf.write(midbytes);
	}
	
	/**
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * 
	* @Title: stringDncoder 
	* @Description: 不带长度 string 解码
	* @param @param b
	* @param @return    
	* @return String    
	* @throws
	 */
	public static String stringDncoder(InputStream buf) throws IOException{
		byte[] b = new byte[buf.available()];
		buf.read(b, 0, buf.available());		
		return new String(b);
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
