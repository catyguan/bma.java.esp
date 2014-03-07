package bma.common.esp.framer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

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
	 * 地址
	 */
	private List<Address> addressList;
	
	public ESNPAddressFramer() {
		super();
	}


	public ESNPAddressFramer(int framerType,InputStream in) throws IOException{
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setAddressList(in);
	}
	
		
	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
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
	public void setAddressList(InputStream in) throws IOException {
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
		
		Address address;
		while(addressIn.available() != 0){
			address = new Address();
			address.setAddressType(Int32Coder.int32Dncoder(addressIn));
			address.setAddress(LenStringCoder.lenStringDncoder(addressIn));
			addressList.add(address);
		}	
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
		if(addressList == null || addressList.isEmpty()){
			return ;
		}
		
		super.toOutputStream(out);	
		for(Address a : addressList){
			Int32Coder.int32Encoder(out, a.getAddressType());
			LenStringCoder.lenStringEncoder(out, a.getAddress());
		}
	}

	/**
	 * 
	* @ClassName: Address 
	* @Description: 地址子类
	* @author zhongrisheng
	* @date 2014-3-7 下午04:32:18 
	*
	 */
	class Address {
		
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
		
		
	}
	

}
