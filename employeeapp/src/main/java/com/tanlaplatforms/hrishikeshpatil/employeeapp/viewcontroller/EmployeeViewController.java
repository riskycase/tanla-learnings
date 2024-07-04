package com.tanlaplatforms.hrishikeshpatil.employeeapp.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.tanlaplatforms.hrishikeshpatil.employeeapp.entity.Employee;
import com.tanlaplatforms.hrishikeshpatil.employeeapp.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class EmployeeViewController {

    EmployeeService employeeService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employee/home";
    }

    @GetMapping("/modify-employee")
    public String getNewEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/modify-employee";
    }

    @GetMapping("/modify-employee/{id}")
    public String editEmployeePage(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeService.findEmployeeById(id));
        return "employee/modify-employee";
    }

    @GetMapping("/delete-employee/{id}")
    public String deletePage(@PathVariable int id, Model model) {
        // employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    @PostMapping("/modify-employee")
    public String addNewEmployeePost(@Valid @ModelAttribute(name = "employee") Employee employee,
            BeanPropertyBindingResult beanPropertyBindingResult) {
        if (beanPropertyBindingResult.hasErrors()) {
            return "employee/modify-employee";
        }
        if (employee.getId() == 0) {
            employeeService.addEmployee(employee);
        } else {
            employeeService.editEmployee(employee.getId(), employee);
        }
        return "redirect:/";
    }

}
