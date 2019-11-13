package at.rsg.jeekurs.service;

//Singelton, we get always 1 service
public class EmployeeServiceLocalFactory {
	private static EmployeeServiceLocal instance = null;
	
	private EmployeeServiceLocalFactory() {
		
	}
	
	public static EmployeeServiceLocal getInstance() {
		if(instance == null) {
			instance = new EmployeeServiceLocal();
		}
		return instance;
	}

}
