package bma.common.esp.coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public abstract class BaseCoder {
	
	/**
	 * 编码类型
	 */
	public static final String UTF_CODER_TYPE = "UTF-8";
	
	/**
	 * 
	* @Title: decoder 
	* @Description: 解码
	* @param @param buf
	* @param @return    
	* @return Object    
	* @throws
	 */
	public abstract Object decoder(InputStream buf) throws IOException;
	
	/**
	 * 
	* @Title: encoder 
	* @Description: 编码
	* @param @param buf
	* @param @param obj    
	* @return void    
	* @throws
	 */
	public abstract void encoder(OutputStream buf, Object obj) throws IOException;

}
