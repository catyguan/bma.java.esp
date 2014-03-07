package bma.common.esp.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import bma.common.langutil.core.StringUtil;

public class BaseTypeTest {
	
	@Test
	public void testBoolean() throws IOException {
		ByteArrayOutputStream bosB = new ByteArrayOutputStream();
		boolean b = false; 
		BaseTypeEncoderTool.booleanEncoder(bosB,b);
		System.out.println(b);
		ByteArrayInputStream bisB = new ByteArrayInputStream(bosB.toByteArray());
		System.out.println(BaseTypeDncoderTool.booleanDncoder(bisB));
	}
	
	@Test
	public void testFixInt16() throws IOException {
		ByteArrayOutputStream bos16 = new ByteArrayOutputStream();
		short s = 16; 
		BaseTypeEncoderTool.fixInt16Encoder(bos16, s);
		System.out.println(s);
		ByteArrayInputStream bis16 = new ByteArrayInputStream(bos16.toByteArray());
		System.out.println(BaseTypeDncoderTool.fixInt16Dncoder(bis16));
	}
	
	@Test
	public void testFixInt32() throws IOException {
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		int i = 32; 
		BaseTypeEncoderTool.fixInt32Encoder(bos32,i);
		System.out.println(i);
		ByteArrayInputStream bis32 = new ByteArrayInputStream(bos32.toByteArray());
		System.out.println(BaseTypeDncoderTool.fixInt32Dncoder(bis32));
	}
	
	@Test
	public void testFixInt64() throws IOException {
		ByteArrayOutputStream bos64 = new ByteArrayOutputStream();
		long l = 92333720368544L; 
		BaseTypeEncoderTool.fixInt64Encoder(bos64,l);
		System.out.println(l);
		ByteArrayInputStream bis64 = new ByteArrayInputStream(bos64.toByteArray());
		System.out.println(BaseTypeDncoderTool.fixInt64Dncoder(bis64));
	}
	
	@Test
	public void testInt16() throws IOException {
		ByteArrayOutputStream bos16 = new ByteArrayOutputStream();
		short s = 32767; 
		BaseTypeEncoderTool.int16Encoder(bos16, s);
		System.out.println(s);
		ByteArrayInputStream bis16 = new ByteArrayInputStream(bos16.toByteArray());
		System.out.println(BaseTypeDncoderTool.int16Dncoder(bis16));
	}
	
	@Test
	public void testInt32() throws IOException {
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		int i = 100000; 
		BaseTypeEncoderTool.int32Encoder(bos32,i);
		System.out.println(i);
		System.out.println(StringUtil.byte2Hex(bos32.toByteArray()));
		ByteArrayInputStream bis32 = new ByteArrayInputStream(bos32.toByteArray());		
		System.out.println(BaseTypeDncoderTool.int32Dncoder(bis32));
	}
	
	@Test
	public void testUint32() throws IOException {
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		int i = 123232; 
		BaseTypeEncoderTool.uint32Encoder(bos32,i);
		System.out.println(i);
		ByteArrayInputStream bis32 = new ByteArrayInputStream(bos32.toByteArray());
		System.out.println(bis32.toString());
		System.out.println(BaseTypeDncoderTool.uint32Dncoder(bis32));
	}
	
	@Test
	public void testInt64() throws IOException {
		ByteArrayOutputStream bos64 = new ByteArrayOutputStream();
		long l = 92333720368544L; 
		BaseTypeEncoderTool.int64Encoder(bos64,l);
		System.out.println(l);
		ByteArrayInputStream bis64 = new ByteArrayInputStream(bos64.toByteArray());
		System.out.println(BaseTypeDncoderTool.int64Dncoder(bis64));
	}
	
	@Test
	public void testFloat32() throws IOException {
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		float i = 32.34f; 
		BaseTypeEncoderTool.float32Encoder(bos32,i);
		System.out.println(i);
		ByteArrayInputStream bis32 = new ByteArrayInputStream(bos32.toByteArray());
		System.out.println(BaseTypeDncoderTool.float32Dncoder(bis32));
	}
	
	@Test
	public void testFloat64() throws IOException {
		ByteArrayOutputStream bos64 = new ByteArrayOutputStream();
		double l = 9233372.11d; 
		BaseTypeEncoderTool.float64Encoder(bos64,l);
		System.out.println(l);
		ByteArrayInputStream bis64 = new ByteArrayInputStream(bos64.toByteArray());
		System.out.println(BaseTypeDncoderTool.float64Dncoder(bis64));
	}
	
	@Test
	public void testString() throws IOException {
		ByteArrayOutputStream bosS = new ByteArrayOutputStream();
		String s = "我是帅哥"; 
		BaseTypeEncoderTool.stringEncoder(bosS,s);
		System.out.println(s);
		ByteArrayInputStream bisS = new ByteArrayInputStream(bosS.toByteArray());
		System.out.println(BaseTypeDncoderTool.stringDncoder(bisS));
	}
	
	@Test
	public void testLenString() throws IOException {
		ByteArrayOutputStream bosLS = new ByteArrayOutputStream();
		String s1 = "a"; 
		BaseTypeEncoderTool.lenStringEncoder(bosLS,s1);
		System.out.println(s1);
		System.out.println(StringUtil.byte2Hex(bosLS.toByteArray()));
		ByteArrayInputStream bisLS = new ByteArrayInputStream(bosLS.toByteArray());
		System.out.println(BaseTypeDncoderTool.lenStringDncoder(bisLS));
	}
	

}
