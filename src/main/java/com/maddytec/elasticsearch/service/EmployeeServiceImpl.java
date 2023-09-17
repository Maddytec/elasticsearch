package com.maddytec.elasticsearch.service;

import com.maddytec.elasticsearch.model.Employee;
import com.maddytec.elasticsearch.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee findById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void update(String id, Employee employee) {
        Optional<Employee> employeeOptinal = employeeRepository.findById(id);
        if (employeeOptinal.isPresent()) {
            employeeOptinal.get().setDepartment(employee.getDepartment());
            employeeOptinal.get().setName(employee.getName());
            employeeOptinal.get().setSalary(employee.getSalary());
            employeeRepository.save(employeeOptinal.get());
        } else {
            throw new RuntimeException("Employee not exists");
        }
    }

    @Override
    public void delete(String id) {
        Optional<Employee> employeeOptinal = employeeRepository.findById(id);
        if (employeeOptinal.isPresent()) {
            employeeRepository.delete(employeeOptinal.get());
        } else {
            throw new RuntimeException("Employee not exists");
        }
    }

    @Override
    public Page<Employee> findByDepartment(String department, Pageable pageable) {
        return employeeRepository.findByDepartment(department, pageable);
    }

    @Override
    public Page<Employee> findByDepartmentAndSalary(String department, Double minSalary, Pageable pageable) {
        return employeeRepository.findByDepartmentAndSalary(department, minSalary, pageable);
    }

    @Override
    public Employee findByName(String name) {
        return employeeRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public Page<Employee> findBySalaryBetween(double min, double max, Pageable pageable) {
        return employeeRepository.findBySalaryBetween(min, max, pageable);
    }

}
