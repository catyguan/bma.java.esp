package bma.common.esp.po;

public class EsnpAddressPo {
	
	//服务组
	private String group;
	
	//主机（节点）
	private String host;
	
	//服务
	private String service;
	
	//操作
	private String op;
	
	//操作关联对象
	private String object;	
	
	public EsnpAddressPo(String service, String op) {
		super();
		this.service = service;
		this.op = op;
	}

	public EsnpAddressPo(String group, String host, String service, String op,
			String object) {
		super();
		this.group = group;
		this.host = host;
		this.service = service;
		this.op = op;
		this.object = object;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}
	
	

}
