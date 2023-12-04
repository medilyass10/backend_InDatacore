package services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import model.Employee;

@Service
@Transactional
@Component
public class EmployeeService {

	 private final EmployeeRepository employeeRepository;

	    @Autowired
	    public EmployeeService(EmployeeRepository employeeRepository) {
	        this.employeeRepository = employeeRepository;
	    }
	    public List<Employee> getAllEmployees() {
	        return employeeRepository.findAll();
	    }

	    public Optional<Employee> getEmployeeById(Long id) {
	        return employeeRepository.findById(id);
	    }

	    public Employee createEmployee(Employee employee) {
	        return employeeRepository.save(employee);
	    }

	    public Employee updateEmployee(Long id, Employee updatedEmployee) {
	        Optional<Employee> existingEmployee = employeeRepository.findById(id);

	        if (existingEmployee.isPresent()) {
	        	 Employee employee = existingEmployee.get();
	             employee.setFirstName(updatedEmployee.getFirstName());
	             employee.setLastName(updatedEmployee.getLastName());
	             employee.setEmail(updatedEmployee.getEmail());
	             employee.setDob(updatedEmployee.getDob());
	             employee.setGender(updatedEmployee.getGender());
	             employee.setEducation(updatedEmployee.getEducation());
	             employee.setCompany(updatedEmployee.getCompany());
	             employee.setExperience(updatedEmployee.getExperience());
	             employee.setPackagep(updatedEmployee.getPackagep());

	             return employeeRepository.save(employee);
	        } else {
	            return null;
	        }
	    }

	    public void deleteEmployee(Long id) {
	        employeeRepository.deleteById(id);
	    }
	    

}
