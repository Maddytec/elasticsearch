package com.maddytec.elasticsearch.controller;

import com.maddytec.elasticsearch.model.Employee;
import com.maddytec.elasticsearch.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> getAll(Pageable pageable) {
        return employeeService.findAll(pageable);
    }

    @GetMapping("/salary")
    public Page<Employee> findBySalaryBetween(
            @RequestParam double min, @RequestParam double max, Pageable pageable) {
        return employeeService.findBySalaryBetween(min, max, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getById(@PathVariable("id") String id) {
        return employeeService.findById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Employee getByName(@RequestParam String name) {
        return employeeService.findByName(name);
    }

    @GetMapping("/department/{department}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> findByDepartment(@PathVariable("department") String department,
                                           Pageable pageable) {
        return employeeService.findByDepartment(department, pageable);
    }

    @GetMapping("/department/{department}/salary/{salary}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> findByDepartmentAndSalary(@PathVariable("department") String department,
                                           @PathVariable("salary") Double minSalary,
                                           Pageable pageable) {
        return employeeService.findByDepartmentAndSalary(department, minSalary, pageable);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable("id") String id,
                           @RequestBody Employee employee) {
        employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable("id") String id) {
        employeeService.delete(id);
    }

}
