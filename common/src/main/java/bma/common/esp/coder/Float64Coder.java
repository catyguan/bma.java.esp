package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Float64Coder implements BaseCoder{
	
	/**
	 * @throws IOException 
	 * 
	* @Title: float64Encoder 
	* @Description: float 64位 编码
	* @param @param d
	* @param @return    
	* @return byte[]    
	* @throws
	 */
	public static void float64Encoder(OutputStream buf,double d) throws IOException{
		long l = Double.doubleToLongBits(d);
		FixInt64Coder.fixInt64Encoder(buf, l);
	}
	
	
	
	/**
	 * @throws IOException 
	 * 
	* @Title: float64Dncoder 
	* @Description: float 64位 解码
	* @param @param b
	* @param @return    
	* @return double    
	* @throws
	 */
	public static double float64Decoder(InputStream buf) throws IOException{
		long l = FixInt64Coder.fixInt64Decoder(buf);
		return Double.longBitsToDouble(l);
	}



	@Override
	public Object decoder(InputStream buf) throws IOException {
		return float64Decoder(buf);
	}



	@Override
	public void encoder(OutputStream buf, Object obj) throws IOException {
		if (obj instanceof Double) {
			Double d = (Double) obj;
			float64Encoder(buf,d);
			return ;
		}
		throw new IllegalArgumentException("not double type");	
		
	}

}
