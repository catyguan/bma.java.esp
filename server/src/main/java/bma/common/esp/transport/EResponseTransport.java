package bma.common.esp.transport;

import java.util.ArrayList;
import java.util.List;

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
	 * 数据
	 */
	private List<ESNPDataFramer> dataList;
	
	public EResponseTransport(){
		dataList = new ArrayList<ESNPDataFramer>();
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

	public List<ESNPDataFramer> getDataList() {
		return dataList;
	}

	public void setDataList(List<ESNPDataFramer> dataList) {
		this.dataList = dataList;
	}
	
}
