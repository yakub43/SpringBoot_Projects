package com.net.employeeservice.service.impl;

import com.net.employeeservice.dto.APIResponseDTO;
import com.net.employeeservice.dto.DepartmentDto;
import com.net.employeeservice.dto.EmployeeDto;
import com.net.employeeservice.dto.OrganizationDto;
import com.net.employeeservice.entity.Employee;
import com.net.employeeservice.mapper.EmployeeMapper;
import com.net.employeeservice.repo.EmployeeRepository;
import com.net.employeeservice.service.APIClient;
import com.net.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private WebClient webClient;
    /*    @Autowired
        private RestTemplate restTemplate;*/
    @Autowired
    private APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
/*        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );*/
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

/*        EmployeeDto savedEmployeedto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()
        );*/
        EmployeeDto employeeDto1 = EmployeeMapper.maptoEmployeeDto(employee);

        return employeeDto1;
    }
    @Override
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDTO getEmployeeById(Long employeeId) {

        logger.info("Inside Employee By Id Method");
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("There is no employee Associated with the given Id"));

       /* ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);*/
        DepartmentDto responseEntity = apiClient.getDepartment(employee.getDepartmentCode());

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8080/api/organizations/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

/*        EmployeeDto savedEmployeedto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );*/

        EmployeeDto employeeDto = EmployeeMapper.maptoEmployeeDto(employee);

        APIResponseDTO apiResponseDTO = new APIResponseDTO();

        apiResponseDTO.setEmployeeDto(employeeDto);
        apiResponseDTO.setDepartmentDto(responseEntity);
        apiResponseDTO.setOrganizationDto(organizationDto);
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
                employee.getDepartmentCode(),
                employee.getOrganizationCode()
        );

        APIResponseDTO apiResponseDTO = new APIResponseDTO();

        apiResponseDTO.setEmployeeDto(savedEmployeedto);
        apiResponseDTO.setDepartmentDto(responseEntity);

        return apiResponseDTO;
    }
}
