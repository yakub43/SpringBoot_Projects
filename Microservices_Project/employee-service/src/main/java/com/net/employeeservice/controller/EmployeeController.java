package com.net.employeeservice.controller;

import com.net.employeeservice.dto.APIResponseDTO;
import com.net.employeeservice.dto.EmployeeDto;
import com.net.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // SAve Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
       EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // get Employee from database
    @GetMapping("{Id}")
    public ResponseEntity<APIResponseDTO> getEmployee(@PathVariable("Id") Long Id){
        APIResponseDTO apiResponseDto = employeeService.getEmployeeById(Id);

        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
