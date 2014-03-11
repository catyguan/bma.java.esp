package bma.common.esp.framer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bma.common.esp.coder.Int32Coder;
import bma.common.esp.coder.LenStringCoder;

/**
 * 
* @ClassName: ENSPAddressFramer 
* @Description: 地址帧
* @author zhongrisheng
* @date 2014-3-7 下午04:24:38 
*
 */
public class ESNPAddressFramer extends ESNPBaseFramer {
	
	/**
	 * 地址类型
	 */
	private int addressType;
	
	/**
	 * 地址
	 */
	private String address;

	public int getAddressType() {
		return addressType;
	}

	public void setAddressType(int addressType) {
		this.addressType = addressType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public ESNPAddressFramer() {
		super();
	}


	public ESNPAddressFramer(int framerType,InputStream in) throws IOException{
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setAddressFramer(in);
	}
	
	/**
	 * 
	* @Title: setAddressList 
	* @Description: 从流中读取地址信息
	* @param @param in
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void setAddressFramer(InputStream in) throws IOException {
		if(super.getFramerType() == 0 || super.getFramerBodyLength() == 0){
			return ;
		}
		
		int length = Long.valueOf(super.getFramerBodyLength()).intValue();
		if(in.available() < length){
			return ;
		}
		byte[] addressByte = new byte[length];
		in.read(addressByte, 0, length);
		InputStream addressIn = new ByteArrayInputStream(addressByte);
		
		this.setAddressType(Int32Coder.int32Dncoder(addressIn));
		this.setAddress(LenStringCoder.lenStringDncoder(addressIn));

		addressIn.close();
	}
	
	/**
	 * 
	* @Title: addressFramerToOutputStream 
	* @Description: 将地址帧写入流
	* @param @param out
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void addressFramerToOutputStream(OutputStream out) throws IOException{
		if(addressType == 0 || address == null){
			return ;
		}
			
		ByteArrayOutputStream adressOut = new ByteArrayOutputStream();

		Int32Coder.int32Encoder(adressOut, this.getAddressType());
		LenStringCoder.lenStringEncoder(adressOut, this.getAddress());

		byte[] adressByte = adressOut.toByteArray();
		super.toOutputStream(out,adressByte.length);	
		out.write(adressByte);
	}


	@Override
	public void readInputStream(int framerType, InputStream in)
			throws IOException {
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setAddressFramer(in);
		
	}
	

}
