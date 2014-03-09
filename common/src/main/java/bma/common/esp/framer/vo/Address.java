package bma.common.esp.framer.vo;

/**
 * 
* @ClassName: Address 
* @Description: 地址子类
* @author zhongrisheng
* @date 2014-3-7 下午04:32:18 
*
 */
public class Address {
	
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