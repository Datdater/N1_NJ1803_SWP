package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.payload.SupplierDTO;

public interface SupplierService {
    SupplierDTO createSupplier(SupplierDTO supplierDto);

    Response<SupplierDTO> getAllSupplier(int pageNo, int pageSize, String sortBy, String sortDir);

    SupplierDTO getSupplierById(long id);

    SupplierDTO updateSupplier(SupplierDTO supplierDto, long id);

    void deleteSupplierById(long id);
}
