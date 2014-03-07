package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Uint64Coder extends BaseCoder{
	
	/**
	 * @throws IOException 
	 * 
	* @Title: uint64Encoder 
	* @Description: 不定长 uint 64位 编码
	* @param @param l
	* @param @return    
	* @return byte[]    
	* @throws
	 */
	public static int uint64Encoder(OutputStream buf,long l) throws IOException{
		int i = 0;
		while(l >= 0x80){
			buf.write((byte) l | 0x80) ;
			l >>= 7;
			i++;
		}
		buf.write((byte) l);
		return i + 1;
	}
	
	
	/**
	 * @throws IOException 
	 * 
	* @Title: uint64Dncoder 
	* @Description: 不定长 uint 64位 解码
	* @param @param b
	* @param @return    
	* @return long    
	* @throws
	 */
	public static long uint64Dncoder(InputStream buf) throws IOException{
		long s = 0;
		int b;
		int w = 0;
		int count = buf.available();
		int i = 0;
		while(count != 0){
			b =  buf.read();
			if(b < 0x80){
				if (i > 9 || i == 9 && b > 1 ){
				     return 0; // overflow
				}
				return (s | (long)(b) << w);

			}
			s |= (long)(b & 0x7f) << w;
			w += 7;
			count--;
		}
		return 0;
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
