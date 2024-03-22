package com.net.employeeservice.service;

import com.net.employeeservice.dto.APIResponseDTO;
import com.net.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
     APIResponseDTO getEmployeeById(Long employeeId);

}
