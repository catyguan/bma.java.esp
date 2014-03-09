package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Float32Coder implements BaseCoder{
	
	/**
	 * @throws IOException 
	 * 
	* @Title: float32Encoder 
	* @Description: float 32位 编码
	* @param @param f
	* @param @return    
	* @return byte[]    
	* @throws
	 */
	public static void float32Encoder(OutputStream buf,float f) throws IOException{    
		int l = Float.floatToIntBits(f);    
		FixUint32Coder.fixUint32Encoder(buf, l);
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: float32Dncoder 
	* @Description: float 32位 解码
	* @param @param b
	* @param @return    
	* @return float    
	* @throws
	 */
	public static float float32Dncoder(InputStream buf) throws IOException{
		int i = FixUint32Coder.fixUint32Dncoder(buf);
		return Float.intBitsToFloat(i);
	}

	@Override
	public Object decoder(InputStream buf) throws IOException {
		return float32Dncoder(buf);
	}

	@Override
	public void encoder(OutputStream buf, Object obj) throws IOException {
		if (obj instanceof Float) {
			Float f = (Float) obj;
			float32Encoder(buf,f);
			return ;
		}
		throw new IllegalArgumentException("not float type");	
		
	}

}
