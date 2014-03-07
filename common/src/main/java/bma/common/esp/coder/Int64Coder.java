package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Int64Coder extends BaseCoder {
	
	/**
	 * @throws IOException 
	 * 
	* @Title: int64Encoder 
	* @Description: 不定长 int 64位 编码
	* @param @param l
	* @param @return    
	* @return byte[]    
	* @throws
	 */
	public static int int64Encoder(OutputStream buf,long l) throws IOException{
		long l1 = ((long) l ) << 1;
		if(l < 0){
			l1 = ~l1;
		}
		return Uint64Coder.uint64Encoder(buf, l1);
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: int64Dncoder 
	* @Description: 不定长 int 64位 解码
	* @param @param b
	* @param @return    
	* @return long    
	* @throws
	 */
	public static long int64Dncoder(InputStream buf) throws IOException{
		long l = Uint64Coder.uint64Dncoder(buf);
		long l2 = (long) (l >> 1);
		if((l & 1) != 0){
			l2 = ~l2;
		}
		return l2;
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
