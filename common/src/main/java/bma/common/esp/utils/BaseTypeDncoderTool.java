package bma.common.esp.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 
* @ClassName: BaseTypeDncoderTool 
* @Description: esnp基础类型解码工具类  
* @author zhongrisheng
* @date 2014-3-6 上午11:19:48 
*
 */
public class BaseTypeDncoderTool {
	
	/**
	 * 编码类型
	 */
	public static final String UTF_CODER_TYPE = "UTF-8";
	
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
		long l = BaseTypeDncoderTool.uint64Dncoder(buf);
		long l2 = (long) (l >> 1);
		if((l & 1) != 0){
			l2 = ~l2;
		}
		return Long.valueOf(l2).shortValue();
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: int32Dncoder 
	* @Description: 不定长 int 32位 解码
	* @param @param b
	* @param @return    
	* @return int    
	* @throws
	 */
	public static int int32Dncoder(InputStream buf) throws IOException{
		long l = BaseTypeDncoderTool.uint64Dncoder(buf);
		long l2 = (long) (l >> 1);
		if((l & 1) != 0){
			l2 = ~l2;
		}
		return Long.valueOf(l2).intValue();
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
		long l = BaseTypeDncoderTool.uint64Dncoder(buf);
		long l2 = (long) (l >> 1);
		if((l & 1) != 0){
			l2 = ~l2;
		}
		return l2;
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
	
	/**
	 * @throws IOException 
	 * 
	* @Title: int32Dncoder 
	* @Description: 不定长 int 32位 解码
	* @param @param b
	* @param @return    
	* @return int    
	* @throws
	 */
	public static int uint32Dncoder(InputStream buf) throws IOException{
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
				return (s | (b) << w);

			}
			s |= (int)(b & 0x7f) << w;
			w += 7;
			count--;
		}
		return 0;
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
	
	/**
	 * @throws IOException 
	 * 
	* @Title: fixInt16Dncoder 
	* @Description: 定长 int 16位 解码 BigEndian
	* @param @param b
	* @param @return    
	* @return short    
	* @throws
	 */
	public static short fixInt16Dncoder(InputStream buf) throws IOException{
		int s = 0;
	    s += (buf.read() & 0xff) << 8;
	    s += (buf.read() & 0xff);
		return (short) s;
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: fixInt32Dncoder 
	* @Description: 定长 int 32位 解码 BigEndian
	* @param @param b
	* @param @return    
	* @return int    
	* @throws
	 */
	public static int fixInt32Dncoder(InputStream buf) throws IOException{
		int s = 0;
		s += (buf.read() & 0xff) << 24;
		s += (buf.read() & 0xff) << 16;
		s += (buf.read() & 0xff) << 8;
		s += (buf.read() & 0xff) ;
		return (int) s;
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: fixInt64Dncoder 
	* @Description: 定长 int 64位 解码 BigEndian
	* @param @param b
	* @param @return    
	* @return long    
	* @throws
	 */
	public static long fixInt64Dncoder(InputStream buf) throws IOException{
		long s = 0;
		s += (long)(buf.read() & 0xff) << 56;
		s += (long)(buf.read() & 0xff) << 48;
		s += (long)(buf.read() & 0xff) << 40;
		s += (long)(buf.read() & 0xff) << 32;
		s += (long)(buf.read() & 0xff) << 24;
		s += (long)(buf.read() & 0xff) << 16;
		s += (long)(buf.read() & 0xff) << 8;
		s += (long)(buf.read() & 0xff) << 0;
		return s;
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
		int i = BaseTypeDncoderTool.int32Dncoder(buf);
		return Float.intBitsToFloat(i);
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
	public static double float64Dncoder(InputStream buf) throws IOException{
		long l = BaseTypeDncoderTool.int64Dncoder(buf);
		return Double.longBitsToDouble(l);
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
}
