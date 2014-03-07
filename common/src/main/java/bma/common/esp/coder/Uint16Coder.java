package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Uint16Coder extends BaseCoder{
	
	/**
	 * @throws IOException 
	 * 
	* @Title: uint16Encoder 
	* @Description: 不定长 uint 16位 编码
	* @param @param s
	* @param @return    
	* @return byte[]    
	* @throws
	 */
	public static int uint16Encoder(OutputStream buf,short s) throws IOException{
		int i = 0;
		while(s >= 0x80){
			buf.write((byte) s | 0x80) ;
			s >>= 7;
			i++;
		}
		buf.write((byte) s);
		return i + 1;
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: uint16Dncoder 
	* @Description: 不定长 uint 16位 解码
	* @param @param b
	* @param @return    
	* @return short    
	* @throws
	 */
	public static short uint16Dncoder(InputStream buf) throws IOException{
		int s = 0;
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
				return (short) (s | (b) << w);

			}
			s |= (int)(b & 0x7f) << w;
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
