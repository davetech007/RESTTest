package at.rsg.jeekurs.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement   //Just for EmployeeResource MediaType.XML
@Entity           //For JPA! Database will
@Table(name = "EMPLOYEES")  //JPA Table name
public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id   //JPA Primary key
	@GeneratedValue(strategy = GenerationType.AUTO)   //JPA from Database automatic created
	private Integer id;
	
	@NotNull //Bean validation
	@NotBlank //Bean validation
	@Size(max = 20, min = 3) //Bean validation
	@Column(name = "fullname", nullable = false)  //JPA column name, and not null
	private String name;
	
	@Min(18)  //Bean validation
	
	private int age;
	
	@Positive //Bean validation
	private int salary;
	
	//@JsonbTransient
	@XmlTransient    //wenn it is, property is going to be ignored in serialize!!
	private boolean grossVerdiener;
	

	public Employee() {
		super();
	}


	public Employee(String name, int age, int salary) {
		super();
		setName(name);
		setAge(age);
		setSalary(salary);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	
	

	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}

	
	
	
	public boolean isGrossVerdiener() {
		return grossVerdiener;
	}


	public void setGrossVerdiener(boolean grossVerdiener) {
		this.grossVerdiener = grossVerdiener;
	}
	
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + ", grossVerdiener="
				+ grossVerdiener + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
