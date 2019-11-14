package at.rsg.jeekurs.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import at.rsg.jeekurs.domain.Employee;

@Stateless
public class EmployeeServiceJPA implements EmployeeService {
	
	@PersistenceContext  		//JPA spec.injection
	private EntityManager em;   //JPA needs

	@Override
	public List<Employee> getAll() throws ServiceException {
		try {
			return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
		}catch (Exception cause){
			cause.printStackTrace();
			throw new ServiceException(cause);
		}
	}

	@Override
	public Employee getById(int id) throws ServiceException {
		try {
			return em.find(Employee.class, id);
		}catch(Exception cause) {
			cause.printStackTrace();
			throw new ServiceException(cause);
		}
	}

	@Override
	public Employee add(Employee e) throws ServiceException {
		try {
			e.setId(null);  //ID s going to be generated 
			em.persist(e);
			return e;
		}catch(Exception cause) {
			cause.printStackTrace();
			throw new ServiceException(cause);
		}
	}

	@Override
	public void removeById(int id) throws ServiceException {
			Employee employee = getById(id);
		try {
			em.remove(employee);
		}catch(Exception cause) {
			cause.printStackTrace();
			throw new ServiceException(cause);
		}
	}

	@Override
	public void update(Employee e) throws ServiceException {
		try {
			em.merge(e);
			em.flush();
		}catch(Exception cause) {
			cause.printStackTrace();
			throw new ServiceException(cause);
		}
	}

}
