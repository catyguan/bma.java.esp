package bma.common.esp.transport;

import java.util.ArrayList;
import java.util.List;

import bma.common.esp.framer.ESNPAddressFramer;
import bma.common.esp.framer.ESNPDataFramer;
import bma.common.esp.framer.ESNPMesNoFramer;
import bma.common.esp.framer.ESNPMesTypeFramer;

/**
 * 
* @ClassName: ERequestTransport 
* @Description: ESNP请求 
* @author zhongrisheng
* @date 2014-3-10 下午03:03:57 
*
 */
public class ERequestTransport {
	
	/**
	 * 消息ID
	 */
	private ESNPMesNoFramer mesNo;
	
	/**
	 * 消息类型
	 */
	private ESNPMesTypeFramer mesType;
	
	/**
	 * 地址
	 */
	private List<ESNPAddressFramer> addressList;
	
	/**
	 * 数据
	 */
	private List<ESNPDataFramer> dataList;
	
	public ERequestTransport(){
		addressList = new ArrayList<ESNPAddressFramer>();
		dataList = new ArrayList<ESNPDataFramer>();
	}
	
	public void addAddressFramer(ESNPAddressFramer af){
		this.addressList.add(af);
	}
	
	public void addDataFramer(ESNPDataFramer df){
		this.dataList.add(df);
	}

	public ESNPMesNoFramer getMesNo() {
		return mesNo;
	}

	public void setMesNo(ESNPMesNoFramer mesNo) {
		this.mesNo = mesNo;
	}

	public ESNPMesTypeFramer getMesType() {
		return mesType;
	}

	public void setMesType(ESNPMesTypeFramer mesType) {
		this.mesType = mesType;
	}

	public List<ESNPAddressFramer> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<ESNPAddressFramer> addressList) {
		this.addressList = addressList;
	}

	public List<ESNPDataFramer> getDataList() {
		return dataList;
	}

	public void setDataList(List<ESNPDataFramer> dataList) {
		this.dataList = dataList;
	}

	
	
	
	

}
