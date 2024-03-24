package com.net.departmentservice.controller;

import com.net.departmentservice.dto.DepartmentDto;
import com.net.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    // Build save department REST API
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartmentdto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartmentdto, HttpStatus.CREATED);
    }

    //Build Department
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentDto = departmentService.getDepartmentByCodes(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

}
