package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BooleanCoder extends BaseCoder{
	
	/**
	 * @throws IOException 
	 * 
	* @Title: booleanEncoder 
	* @Description: 布尔值编码
	* @param @param b
	* @param @return    
	* @return byte[]    
	* @throws
	 */
	public static void booleanEncoder(OutputStream buf,boolean b) throws IOException{
		byte bt;
		if(!b){
			bt = 0;
		} else {
			bt = 1;
		}
		buf.write(bt);
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: booleanDncoder 
	* @Description: 布尔值解码
	* @param @param b
	* @param @return    
	* @return boolean    
	* @throws
	 */
	public static boolean booleanDncoder(InputStream buf) throws IOException{
		return buf.read() == 0 ? false : true;
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
