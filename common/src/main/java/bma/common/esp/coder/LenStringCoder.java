package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bma.common.esp.utils.BaseTypeDncoderTool;
import bma.common.esp.utils.BaseTypeEncoderTool;

public class LenStringCoder implements BaseCoder{
	
	/**
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * 
	* @Title: lenStringEncoder 
	* @Description: 带长度 string 编码
	* @param @param s
	* @param @return    
	* @return byte[]    
	* @throws
	 */
	public static void lenStringEncoder(OutputStream buf,String s) throws IOException {
		byte[] midbytes=s.getBytes(UTF_CODER_TYPE);
		int i = midbytes.length;
		Int32Coder.int32Encoder(buf, i);
		StringCoder.stringEncoder(buf, s);
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: lenStringDncoder 
	* @Description: 带长度 string 解码
	* @param @param b
	* @param @return    
	* @return String    
	* @throws
	 */
	public static String lenStringDncoder(InputStream buf) throws IOException{
		int l = BaseTypeDncoderTool.int32Dncoder(buf);
		byte[] b = new byte[l];
		buf.read(b, 0, l);		
		return new String(b);
	}

	@Override
	public Object decoder(InputStream buf) throws IOException {
		return lenStringDncoder(buf);
	}

	@Override
	public void encoder(OutputStream buf, Object obj) throws IOException {
		if (obj instanceof String) {
			String s = (String) obj;
			lenStringEncoder(buf,s);
			return ;
		}
		throw new IllegalArgumentException("not string type");		
	}

}
