package bma.common.esp.framer.vo;

public class DataBody{
	
	/**
	 * 数据名
	 */
	private String dataName;
	
	/**
	 * 数据类型
	 */
	private int dataType;
	
	
	/**
	 * 数据
	 */
	private Object data;

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
