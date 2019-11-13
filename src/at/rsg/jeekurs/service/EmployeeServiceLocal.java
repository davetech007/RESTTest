package at.rsg.jeekurs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.rsg.jeekurs.domain.Employee;

public class EmployeeServiceLocal implements EmployeeService {
	private Map<Integer, Employee> employeeMap = new HashMap<>();
	private static int id = 0;
	
	@Override
	public List<Employee> getAll() throws ServiceException {
		return new ArrayList<Employee>(employeeMap.values());
	}

	@Override
	public Employee getById(int id) throws ServiceException {
		return employeeMap.get(id);
	}

	@Override
	public Employee add(Employee e) throws ServiceException {
		if(e.getId() == null) {
			e.setId(++id);
		}
		employeeMap.put(id, e);
		System.out.println("Added new Emloyee: " + e.toString());
		return e;
	}

	@Override
	public void removeById(int id) throws ServiceException {
		employeeMap.remove(id);
	}

	@Override
	public void update(Employee e) throws ServiceException {
		employeeMap.put(e.getId(), e);
	}

}
