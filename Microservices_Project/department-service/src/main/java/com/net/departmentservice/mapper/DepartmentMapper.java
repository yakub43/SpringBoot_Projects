package com.net.departmentservice.mapper;

import com.net.departmentservice.dto.DepartmentDto;
import com.net.departmentservice.entity.Department;

public class DepartmentMapper {

    public static DepartmentDto mapToDepartmentDto(Department department){

        DepartmentDto savedDepartmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return savedDepartmentDto;
    }
    public static Department mapToDepartment(DepartmentDto departmentDto){
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentCode(),
                departmentDto.getDepartmentDescription()
        );
        return department;
    }
}
