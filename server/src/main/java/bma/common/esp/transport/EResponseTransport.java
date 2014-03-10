package bma.common.esp.transport;

import bma.common.esp.framer.ESNPAddressFramer;
import bma.common.esp.framer.ESNPDataFramer;
import bma.common.esp.framer.ESNPMesNoFramer;
import bma.common.esp.framer.ESNPMesSnoFramer;
import bma.common.esp.framer.ESNPMesTypeFramer;

/**
 * 
* @ClassName: EResponseTransport 
* @Description: ESNP响应 
* @author zhongrisheng
* @date 2014-3-10 下午03:11:56 
*
 */
public class EResponseTransport {
	
	/**
	 * 消息ID
	 */
	private ESNPMesNoFramer mesNo;
	
	/**
	 * 消息源ID
	 */
	private ESNPMesSnoFramer mesSno;
	
	/**
	 * 消息类型
	 */
	private ESNPMesTypeFramer mesType;
	
	/**
	 * 地址
	 */
	private ESNPAddressFramer address;
	
	/**
	 * 数据
	 */
	private ESNPDataFramer data;

	public ESNPMesNoFramer getMesNo() {
		return mesNo;
	}

	public void setMesNo(ESNPMesNoFramer mesNo) {
		this.mesNo = mesNo;
	}

	public ESNPMesSnoFramer getMesSno() {
		return mesSno;
	}

	public void setMesSno(ESNPMesSnoFramer mesSno) {
		this.mesSno = mesSno;
	}

	public ESNPMesTypeFramer getMesType() {
		return mesType;
	}

	public void setMesType(ESNPMesTypeFramer mesType) {
		this.mesType = mesType;
	}

	public ESNPAddressFramer getAddress() {
		return address;
	}

	public void setAddress(ESNPAddressFramer address) {
		this.address = address;
	}

	public ESNPDataFramer getData() {
		return data;
	}

	public void setData(ESNPDataFramer data) {
		this.data = data;
	}

	
}
