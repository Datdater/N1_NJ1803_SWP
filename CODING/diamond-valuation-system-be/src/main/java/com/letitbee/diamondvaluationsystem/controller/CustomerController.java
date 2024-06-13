package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.payload.CustomerDTO;
import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.service.CustomerService;
import com.letitbee.diamondvaluationsystem.utils.AppConstraint;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/customers")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Response<CustomerDTO>> getAllCustomer(@RequestParam(name = "pageNo", defaultValue = AppConstraint.PAGE_NO, required = false) int pageNo,
                                                                         @RequestParam(name = "pageSize", defaultValue = AppConstraint.PAGE_SIZE, required = false) int pageSize,
                                                                         @RequestParam(name = "sortBy", defaultValue = AppConstraint.SORT_BY, required = false) String sortBy,
                                                                         @RequestParam(name = "sortDir", defaultValue = AppConstraint.SORT_DIR, required = false) String sortDir) {

        return ResponseEntity.ok(customerService.getAllCustomer(pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("customerId") long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<CustomerDTO> getCustomerByPhoneOrName(@RequestParam(value = "phone", required = false) String phone,
                                                                      @RequestParam(value = "name", required = false) String name){
        return ResponseEntity.ok(customerService.getCustomerByPhoneOrName(phone,name));
    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> createCustomerInformation(@RequestBody @Valid CustomerDTO customerDto){
        CustomerDTO customer = customerService.createCustomerInformation(customerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

//    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerInformation(@RequestBody @Valid CustomerDTO customerDto, @PathVariable("id") long id){
        CustomerDTO customer = customerService.updateCustomerInformation(customerDto, id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
