package bma.common.esp.coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import bma.common.esp.common.VarTypeCommon;
import bma.common.langutil.core.StringUtil;

public class BaseTypeTest {
	
	@Test
	public void testBoolean() throws IOException {
		ByteArrayOutputStream bosB = new ByteArrayOutputStream();
		boolean b = false; 
		BooleanCoder.booleanEncoder(bosB,b);
		System.out.println(b);
		ByteArrayInputStream bisB = new ByteArrayInputStream(bosB.toByteArray());
		System.out.println(BooleanCoder.booleanDecoder(bisB));
	}
	
	@Test
	public void testFixInt16() throws IOException {
		ByteArrayOutputStream bos16 = new ByteArrayOutputStream();
		short s = 16; 
		FixInt16Coder.fixInt16Encoder(bos16, s);
		System.out.println(s);
		ByteArrayInputStream bis16 = new ByteArrayInputStream(bos16.toByteArray());
		System.out.println(FixInt16Coder.fixInt16Decoder(bis16));
	}
	
	@Test
	public void testFixInt32() throws IOException {
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		int i = -32; 
		FixInt32Coder.fixInt32Encoder(bos32,i);
		System.out.println(i);
		ByteArrayInputStream bis32 = new ByteArrayInputStream(bos32.toByteArray());
		System.out.println(FixInt32Coder.fixInt32Decoder(bis32));
	}
	
	@Test
	public void testFixInt64() throws IOException {
		ByteArrayOutputStream bos64 = new ByteArrayOutputStream();
		long l = 88888888; 
		FixInt64Coder.fixInt64Encoder(bos64,l);
		System.out.println(l);
		ByteArrayInputStream bis64 = new ByteArrayInputStream(bos64.toByteArray());
		System.out.println(FixInt64Coder.fixInt64Decoder(bis64));
	}
	
	@Test
	public void testFixUint64() throws IOException {
		ByteArrayOutputStream bos64 = new ByteArrayOutputStream();
		long l = 88888888; 
		FixUint64Coder.fixUint64Encoder(bos64,l);
		System.out.println(l);
		ByteArrayInputStream bis64 = new ByteArrayInputStream(bos64.toByteArray());
		System.out.println(FixUint64Coder.fixUint64Decoder(bis64));
	}
	
	@Test
	public void testInt16() throws IOException {
		ByteArrayOutputStream bos16 = new ByteArrayOutputStream();
		short s = 32767; 
		Int16Coder.int16Encoder(bos16, s);
		System.out.println(s);
		ByteArrayInputStream bis16 = new ByteArrayInputStream(bos16.toByteArray());
		System.out.println(Int16Coder.int16Decoder(bis16));
	}
	
	@Test
	public void testInt32() throws IOException {
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		int i = 100000; 
		Int32Coder.int32Encoder(bos32,i);
		System.out.println(i);
		System.out.println(StringUtil.byte2Hex(bos32.toByteArray()));
		ByteArrayInputStream bis32 = new ByteArrayInputStream(bos32.toByteArray());		
		System.out.println(Int32Coder.int32Decoder(bis32));
	}
	
	@Test
	public void testUint32() throws IOException {
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		int i = 123232; 
		Uint32Coder.uint32Encoder(bos32,i);
		System.out.println(i);
		ByteArrayInputStream bis32 = new ByteArrayInputStream(bos32.toByteArray());
		System.out.println(Uint32Coder.uint32Decoder(bis32));
	}
	
	@Test
	public void testInt64() throws IOException {
		ByteArrayOutputStream bos64 = new ByteArrayOutputStream();
		long l = 92333720368544L; 
		Int64Coder.int64Encoder(bos64,l);
		System.out.println(l);
		System.out.println(StringUtil.byte2Hex(bos64.toByteArray()));
		ByteArrayInputStream bis64 = new ByteArrayInputStream(bos64.toByteArray());
		System.out.println(Int64Coder.int64Decoder(bis64));
	}
	
	@Test
	public void testFloat32() throws IOException {
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		float i = 32.34f; 
		Float32Coder.float32Encoder(bos32,i);
		System.out.println(i);
		ByteArrayInputStream bis32 = new ByteArrayInputStream(bos32.toByteArray());
		System.out.println(Float32Coder.float32Decoder(bis32));
	}
	
	@Test
	public void testFloat64() throws IOException {
		ByteArrayOutputStream bos64 = new ByteArrayOutputStream();
		double l = -9233372.11d; 
		Float64Coder.float64Encoder(bos64,l);
		System.out.println(l);
		ByteArrayInputStream bis64 = new ByteArrayInputStream(bos64.toByteArray());
		System.out.println(Float64Coder.float64Decoder(bis64));
	}
	
	@Test
	public void testString() throws IOException {
		ByteArrayOutputStream bosS = new ByteArrayOutputStream();
		String s = "我是帅哥"; 
		StringCoder.stringEncoder(bosS,s);
		System.out.println(s);
		ByteArrayInputStream bisS = new ByteArrayInputStream(bosS.toByteArray());
		System.out.println(StringCoder.stringDecoder(bisS));
	}
	
	@Test
	public void testLenString() throws IOException {
		ByteArrayOutputStream bosLS = new ByteArrayOutputStream();
		String s1 = "a"; 
		LenStringCoder.lenStringEncoder(bosLS,s1);
		System.out.println(s1);
		System.out.println(StringUtil.byte2Hex(bosLS.toByteArray()));
		ByteArrayInputStream bisLS = new ByteArrayInputStream(bosLS.toByteArray());
		System.out.println(LenStringCoder.lenStringDecoder(bisLS));
	}
	
	@Test
	public void testVar() throws IOException {
		ByteArrayOutputStream bosVar = new ByteArrayOutputStream();
		int a = 1;
		VarCoder.varEncoder(bosVar, VarTypeCommon.TYPE_INT32, a);
		System.out.println(a);
		System.out.println(StringUtil.byte2Hex(bosVar.toByteArray()));
		ByteArrayInputStream bisLS = new ByteArrayInputStream(bosVar.toByteArray());
		System.out.println(VarCoder.varDecoder(bisLS, VarTypeCommon.TYPE_INT32, true));
	}
	

}
