package com.codegym.baitap1.controller;

import com.codegym.baitap1.model.Customer;
import com.codegym.baitap1.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public String customers(Model model,@PageableDefault(size = 2) Pageable pageable) {
        Page<Customer> customers = customerService.findAll(pageable);
        model.addAttribute("customers", customers);
        return "index";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<Customer> customer = customerService.findById(id);
        model.addAttribute("customer", customer.get());
        return "edit";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id, Model model) {
        Optional<Customer> customer = customerService.findById(id);
        model.addAttribute("customer", customer.get());
        return "delete";

    }
    @PostMapping("/delete")
    public String delete(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
        customerService.delete(customer.getId());
        redirectAttributes.addFlashAttribute("message", "Customer deleted successfully");
        return "redirect:/customers";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable("id") long id, Model model) {
        Optional<Customer> customer = customerService.findById(id);
        model.addAttribute("customer", customer.get());
        return "view";
    }
}
