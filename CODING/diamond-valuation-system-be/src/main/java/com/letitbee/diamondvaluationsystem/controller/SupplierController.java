package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.payload.SupplierDTO;
import com.letitbee.diamondvaluationsystem.service.SupplierService;
import com.letitbee.diamondvaluationsystem.utils.AppConstraint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {

    private SupplierService supplierService;
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<Response<SupplierDTO>> getAllSupplier(@RequestParam(name = "pageNo", defaultValue = AppConstraint.PAGE_NO, required = false) int pageNo,
                                                                @RequestParam(name = "pageSize", defaultValue = AppConstraint.PAGE_SIZE, required = false) int pageSize,
                                                                @RequestParam(name = "sortBy", defaultValue = AppConstraint.SORT_BY, required = false) String sortBy,
                                                                @RequestParam(name = "sortDir", defaultValue = AppConstraint.SORT_DIR, required = false) String sortDir) {
        return ResponseEntity.ok(supplierService.getAllSupplier(pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable("id") long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @PostMapping()
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDto){
        SupplierDTO supplier = supplierService.createSupplier(supplierDto);
        return ResponseEntity.ok(supplier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@RequestBody SupplierDTO supplierDto, @PathVariable("id") long id){
        SupplierDTO supplier = supplierService.updateSupplier(supplierDto, id);
        return ResponseEntity.ok(supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable("id") long id){
        supplierService.deleteSupplierById(id);
    }
}
