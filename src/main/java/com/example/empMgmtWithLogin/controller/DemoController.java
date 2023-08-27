package com.example.empMgmtWithLogin.controller;

import com.example.empMgmtWithLogin.entity.Employee;
import com.example.empMgmtWithLogin.service.EmployeeService;
import com.example.empMgmtWithLogin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class DemoController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    public DemoController(EmployeeService employeeService, RoleService roleService) {
        this.employeeService = employeeService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String showHome(Model theModel){
        List<Employee> employeeList = employeeService.findAll();
        theModel.addAttribute("employees",employeeList);

        return "home";
    }

    @GetMapping("/viewDetails")
    public String viewDetails(@RequestParam("employeeId") int id, Model theModel){


        Optional<Employee> employee = employeeService.findById(id);
        Employee employee1 = employee.get();
        theModel.addAttribute("employee",employee1);

        return "details";

    }

    @GetMapping("/showFormForUpdate")
    public String update(@RequestParam("employeeId")int id,Model theModel){
        Optional<Employee> employee = employeeService.findById(id);
        theModel.addAttribute("employee",employee);

        return "employee-form";
    }

    @PostMapping("/updateDetails")
    public String updateDetails(@ModelAttribute("employee")Employee theEmployee){
        employeeService.updateDetails(theEmployee);

        return "redirect:/";
    }

    @GetMapping("/updateProfile")
    public String updateProfile(@RequestParam("employeeId")int id,Model theModel){
        Optional<Employee> employee = employeeService.findById(id);
        theModel.addAttribute("employee",employee);
        return "updateForm";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id){
        employeeService.deleteById(id);

        return "redirect:/";
    }
}
