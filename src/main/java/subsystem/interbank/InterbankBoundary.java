package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.ApplicationProgrammingInterface;
/**
 *  * singleton: day la mot class chi co cac method ho tro, nen dung singleton de toi uu bo nho
 *  */
public class InterbankBoundary {

	private static InterbankBoundary instance;

	public static InterbankBoundary getInstance(){
		if(instance == null){
			instance = new InterbankBoundary();
		}
		return instance;
	}

	private InterbankBoundary(){}

	// coupling: data -> chi phu thuoc mot so tham so
	String query(String url, String data) {
		String response = null;
		try {
			response = ApplicationProgrammingInterface.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
