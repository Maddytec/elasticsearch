package com.maddytec.elasticsearch.service;

import com.maddytec.elasticsearch.model.Employee;
import com.maddytec.elasticsearch.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        Iterable<Employee> employeeIterable = employeeRepository.findAll();
        return StreamSupport.stream(employeeIterable.spliterator(), false)
                .collect(Collectors.toList());
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
    public Employee findByName(String name) {
        return employeeRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException());
    }

}
