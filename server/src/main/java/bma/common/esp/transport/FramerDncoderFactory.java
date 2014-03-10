package bma.common.esp.transport;

import java.io.IOException;
import java.io.InputStream;

import bma.common.esp.common.FramerCommon;
import bma.common.esp.framer.ESNPAddressFramer;
import bma.common.esp.framer.ESNPDataFramer;
import bma.common.esp.framer.ESNPMesNoFramer;
import bma.common.esp.framer.ESNPMesTypeFramer;

public class FramerDncoderFactory {
	
	public static void getERequestFramer(ERequestTransport eRequest,InputStream in,int framerType) throws IOException{
		
		switch (framerType) {
		case FramerCommon.FRAMER_TYPE_ID:
			ESNPMesNoFramer mnf = new ESNPMesNoFramer();
			mnf.readInputStream(framerType, in);
			eRequest.setMesNo(mnf);
			return ;
		case FramerCommon.FRAMER_TYPE_ADDRESS:
			ESNPAddressFramer af = new ESNPAddressFramer();
			af.readInputStream(framerType, in);
			eRequest.addAddressFramer(af);
			return ;
		case FramerCommon.FRAMER_TYPE_TYPE:
			ESNPMesTypeFramer mtf = new ESNPMesTypeFramer();
			mtf.readInputStream(framerType, in);
			eRequest.setMesType(mtf);
			return ;
		case FramerCommon.FRAMER_TYPE_DATA:
			ESNPDataFramer df = new ESNPDataFramer();
			df.readInputStream(framerType, in);
			eRequest.addDataFramer(df);
			return ;
		default:
			break;
		}
		return;
	}

}
