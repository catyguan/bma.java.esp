package bma.common.esp.test;

import java.io.IOException;

import bma.common.esp.exception.EspExecption;
import bma.common.esp.server.core.ESNPServerTransport;
import bma.common.esp.server.processor.EFunction;
import bma.common.esp.server.processor.EHandler;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

public class Test extends EHandler{
	
	private Iface iface;

	public interface Iface{
		
		public void add(ESNPServerTransport eTransport,
				ERequest eRequest, EResponse eResponse) throws IOException;
		
		public int add(int a,int b) throws EspExecption;
				
	}
	
	public Test(){
		super.getFunctionMap().put("add", new Add());
	}
	
	public class Add extends EFunction{
		
		//获取参数
		public add_arg get_add_arg(ERequest eRequest){
			return new add_arg();
		}
		
		//获取结果
		public add_result get_add_result(int c){
			add_result result = new add_result();
			result.c = c;
			return result;
		}
		
		//发送结果
		public void send_add_result(ESNPServerTransport eTransport,EResponse eResponse,add_result result) throws IOException{
			eTransport.write(eResponse);
			eTransport.flush();
		}

		@Override
		public void execute(ESNPServerTransport eTransport,
				ERequest eRequest, EResponse eResponse) throws IOException {
/*			
			//获取参数
			add_arg arg= this.get_add_arg(eRequest);
			//调用实现
			add_result result = this.get_add_result(iface.add(arg.a, arg.b));
			//发送结果
			this.send_add_result(eTransport, eResponse, result);
	*/		
			iface.add(eTransport, eRequest, eResponse);
		}
	}
	
	//参数对象
	public class add_arg {
		int a;
		int b;
		public void setA(int a) {
			this.a = a;
		}
		public void setB(int b) {
			this.b = b;
		}
	}
	
	//结果对象
	public class add_result {
		int c;
		public int getC() {
			return c;
		}

		public void setC(int c) {
			this.c = c;
		}
		
	}

	public Iface getIface() {
		return iface;
	} 

	public void setIface(Iface iface) {
		this.iface = iface;
	}
	
	

}
