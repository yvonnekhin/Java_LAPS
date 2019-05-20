package sg.edu.nus.demo.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO/* , generator = "system-uuid" */)
	/* @GenericGenerator(name = "system-uuid", strategy = "uuid2") */
	private Integer employeeId; 
	private String employeeName;
	private String userId;
	private String password;
	private String designation;
	private String employeeType; //admin or pro(employee,manager)
	private String managerId;
	private int annualLeaveBalance;
	private int medicalLeaveBalance;
	private int compLeaveBalance;
	
	@OneToMany(targetEntity=AnnualLeave.class, mappedBy="employee")
	public Collection<AnnualLeave> annualLeaveList;
	
	@OneToMany(targetEntity=MedicalLeave.class, mappedBy="employee")
	public Collection<MedicalLeave> medicalLeaveList;
	
	@OneToMany(targetEntity=CompLeave.class, mappedBy="employee")
	public Collection<CompLeave> compLeaveList;
	
	@ManyToOne 
	//@JoinColumn(name="managerid")	 
	@JoinColumn(name="id")	
	private Manager manager; //assume each employee only one manager
		
	public Employee() {}
	
	public Employee(/* String employeeId, */String employeeName, String userId, String password, String designation,
			String employeeType, String managerId, int annualLeaveBalance, int medicalLeaveBalance,
			int compLeaveBalance, Collection<AnnualLeave> annualLeaveList, Manager manager) {
		super();
		/* this.employeeId = employeeId; */
		this.employeeName = employeeName;
		this.userId = userId;
		this.password = password;
		this.designation = designation;
		this.employeeType = employeeType;
		this.managerId = managerId;
		this.annualLeaveBalance = annualLeaveBalance;
		this.medicalLeaveBalance = medicalLeaveBalance;
		this.compLeaveBalance = compLeaveBalance;
		this.annualLeaveList = annualLeaveList;
		this.manager = manager;
		
		if(designation=="developer")
		{
			this.annualLeaveBalance = 14;
		}
		else if (designation=="teamlead")
		{
			this.annualLeaveBalance = 18;
		}
		this.medicalLeaveBalance = 60;
		this.compLeaveBalance = 0;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public int getAnnualLeaveBalance() {
		return annualLeaveBalance;
	}

	public void setAnnualLeaveBalance(int annualLeaveBalance) {
		this.annualLeaveBalance = annualLeaveBalance;
	}

	public int getMedicalLeaveBalance() {
		return medicalLeaveBalance;
	}

	public void setMedicalLeaveBalance(int medicalLeaveBalance) {
		this.medicalLeaveBalance = medicalLeaveBalance;
	}

	public int getCompLeaveBalance() {
		return compLeaveBalance;
	}

	public void setCompLeaveBalance(int compLeaveBalance) {
		this.compLeaveBalance = compLeaveBalance;
	}

	public Collection<AnnualLeave> getAnnualLeaveList() {
		return annualLeaveList;
	}

	public void setAnnualLeaveList(Collection<AnnualLeave> annualLeaveList) {
		this.annualLeaveList = annualLeaveList;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", userId=" + userId
				+ ", password=" + password + ", designation=" + designation + ", employeeType=" + employeeType
				+ ", managerId=" + managerId + ", annualLeaveBalance=" + annualLeaveBalance + ", medicalLeaveBalance="
				+ medicalLeaveBalance + ", compLeaveBalance=" + compLeaveBalance + ", annualLeaveList="
				+ annualLeaveList + ", manager=" + manager + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		return result;
	}

	//Assuming manager view subordinate leave balance will be sorted by employee name
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		return true;
	}

}
