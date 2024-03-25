package com.net.employeeservice.service.impl;

import com.net.employeeservice.dto.APIResponseDTO;
import com.net.employeeservice.dto.DepartmentDto;
import com.net.employeeservice.dto.EmployeeDto;
import com.net.employeeservice.entity.Employee;
import com.net.employeeservice.repo.EmployeeRepository;
import com.net.employeeservice.service.APIClient;
import com.net.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    /*    @Autowired
        private RestTemplate restTemplate;*/
    @Autowired
    private APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeedto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()
        );

        return savedEmployeedto;
    }
    @Override
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDTO getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).get();

       /* ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);*/
        DepartmentDto responseEntity = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDto savedEmployeedto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDTO apiResponseDTO = new APIResponseDTO();

        apiResponseDTO.setEmployeeDto(savedEmployeedto);
        apiResponseDTO.setDepartmentDto(responseEntity);

        return apiResponseDTO;
    }
    public APIResponseDTO getDefaultDepartment(Long employeeId, Exception exception){

        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto responseEntity = new DepartmentDto();
        responseEntity.setDepartmentName("R&D Department");
        responseEntity.setDepartmentCode("RD001");
        responseEntity.setDepartmentDescription("Research and Development Department");

        EmployeeDto savedEmployeedto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDTO apiResponseDTO = new APIResponseDTO();

        apiResponseDTO.setEmployeeDto(savedEmployeedto);
        apiResponseDTO.setDepartmentDto(responseEntity);

        return apiResponseDTO;
    }
}
