package com.net.employeeservice.mapper;

import com.net.employeeservice.dto.EmployeeDto;
import com.net.employeeservice.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto maptoEmployeeDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentCode(),
                employee.getEmail(),
                employee.getOrganizationCode()
        );
        return employeeDto;
    }
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getDepartmentCode(),
                employeeDto.getEmail(),
                employeeDto.getOrganizationCode()
        );
        return employee;
    }
}
