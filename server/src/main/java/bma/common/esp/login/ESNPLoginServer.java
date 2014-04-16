package bma.common.esp.login;

import bma.common.esp.exception.EspExecption;


public class ESNPLoginServer implements ESNPLogin.Iface{

	@Override
	public boolean espnLogin(int a, int b) throws EspExecption {
		if((a+b) == 3){
			return true;
		} else {
			return false;
		}
	}

}
