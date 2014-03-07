package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Int16Coder extends BaseCoder{

	/**
	 * @throws IOException 
	 * 
	* @Title: int16Encoder 
	* @Description: 不定长 int 16位 编码
	* @param @param s
	* @param @return    
	* @return byte[]    
	* @throws
	 */
	public static int int16Encoder(OutputStream buf,short s) throws IOException{
		long l = ((long) s ) << 1;
		if(s < 0){
			l = ~l;
		}
		return Uint64Coder.uint64Encoder(buf, l);
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: int16Dncoder 
	* @Description: 不定长 int 16位 解码
	* @param @param b
	* @param @return    
	* @return short    
	* @throws
	 */
	public static short int16Dncoder(InputStream buf) throws IOException{
		long l = Uint64Coder.uint64Dncoder(buf);
		long l2 = (long) (l >> 1);
		if((l & 1) != 0){
			l2 = ~l2;
		}
		return Long.valueOf(l2).shortValue();
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
