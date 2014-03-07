package bma.common.esp.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
* 
* @ClassName: BaseTypeEncoderTool 
* @Description: esnp基础类型编码工具类 
* @author zhongrisheng
* @date 2014-3-5 下午03:17:39 
*
*/
public class BaseTypeEncoderTool {
	
	/**
	 * 编码类型
	 */
	public static final String UTF_CODER_TYPE = "UTF-8";
	
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
		return BaseTypeEncoderTool.uint64Encoder(buf, l);
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: int32Encoder 
	* @Description: 不定长 int 32位 编码
	* @param @param i
	* @param @return    
	* @return byte[]    
	* @throws
	 */
	public static int int32Encoder(OutputStream buf,int s) throws IOException{
		long l = ((long) s ) << 1;
		if(s < 0){
			l = ~l;
		}
		return BaseTypeEncoderTool.uint64Encoder(buf, l);
	}

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
		return BaseTypeEncoderTool.uint64Encoder(buf, l1);
	}
	
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
	* @Title: int32Encoder 
	* @Description: 不定长 int 32位 编码
	* @param @param i
	* @param @return    
	* @return byte[]    
	* @throws
	 */
	public static int uint32Encoder(OutputStream buf,int s) throws IOException{
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
	* @Title: int64Encoder 
	* @Description: 不定长 int 64位 编码
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
	* @Title: fixInt16Encoder 
	* @Description: 定长 int 16位 编码 BigEndian
	* @param @param s
	* @param @return    
	* @return byte[]  2个字节
	* @throws
	 */
	public static void fixInt16Encoder(OutputStream buf, short s) throws IOException{
		buf.write((byte) (s >> 8));
		buf.write((byte) (s >> 0));
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: fixInt32Encoder 
	* @Description: 定长 int 32位 编码 BigEndian
	* @param @param i
	* @param @return    
	* @return byte[]   4个字节 
	* @throws
	 */
	public static void fixInt32Encoder(OutputStream buf,int i) throws IOException{
		buf.write((byte) (i >> 24));
		buf.write((byte) (i >> 16));
		buf.write((byte) (i >> 8));
		buf.write((byte) (i >> 0));
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: fixInt64Encoder 
	* @Description: 定长 int 64位 编码 BigEndian
	* @param @param l
	* @param @return    
	* @return byte[] 8个字节   
	* @throws
	 */
	public static void fixInt64Encoder(OutputStream buf,long l) throws IOException{
		buf.write((byte) (l >> 56));
		buf.write((byte) (l >> 48));
		buf.write((byte) (l >> 40));
		buf.write((byte) (l >> 32));
		buf.write((byte) (l >> 24));
		buf.write((byte) (l >> 16));
		buf.write((byte) (l >> 8));
		buf.write((byte) (l >> 0));
	}
	
	
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
		BaseTypeEncoderTool.int32Encoder(buf, l);
	}
	
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
		BaseTypeEncoderTool.int64Encoder(buf, l);
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: stringEncoder 
	* @Description: 不带长度 string 编码
	* @param @param s
	* @param @return    
	* @return byte[]    
	* @throws
	 */
	public static void stringEncoder(OutputStream buf,String s) throws IOException{
		byte[] midbytes=s.getBytes(BaseTypeEncoderTool.UTF_CODER_TYPE);
		buf.write(midbytes);
	}
	
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
		byte[] midbytes=s.getBytes(BaseTypeEncoderTool.UTF_CODER_TYPE);
		int i = midbytes.length;
		BaseTypeEncoderTool.int32Encoder(buf, i);
		BaseTypeEncoderTool.stringEncoder(buf, s);
	}

}
