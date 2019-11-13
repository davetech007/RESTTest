package at.rsg.jeekurs.service;

import java.util.List;

import at.rsg.jeekurs.domain.Employee;

public interface EmployeeService {
	
	public List<Employee> getAll() throws ServiceException;
	
	public Employee getById(int id) throws ServiceException;
	
	public Employee add(Employee e) throws ServiceException;
	
	public void removeById(int id) throws ServiceException;
	
	public void update(Employee e) throws ServiceException;
	
	

}
