package com.letitbee.diamondvaluationsystem.service.impl;

import com.letitbee.diamondvaluationsystem.entity.Customer;
import com.letitbee.diamondvaluationsystem.entity.Supplier;
import com.letitbee.diamondvaluationsystem.exception.ResourceNotFoundException;
import com.letitbee.diamondvaluationsystem.payload.CustomerDTO;
import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.payload.SupplierDTO;
import com.letitbee.diamondvaluationsystem.repository.SupplierRepository;
import com.letitbee.diamondvaluationsystem.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private ModelMapper mapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper mapper) {
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
    }
    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDto) {
        Supplier supplier = mapToEntity(supplierDto);
        Supplier newSupplier = supplierRepository.save(supplier);
        return mapToDto(newSupplier);
    }

    @Override
    public Response<SupplierDTO> getAllSupplier(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy) : Sort.by(sortBy).descending();
        //Set size page and pageNo
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Supplier> page = supplierRepository.findAll(pageable);

        List<Supplier> supplierList = page.getContent();

        List<SupplierDTO> supplierDTOList = supplierList.stream().
                map(supplier -> mapToDto(supplier)).
                toList();

        Response<SupplierDTO> response = new Response<>();

        response.setContent(supplierDTOList);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPage(page.getTotalPages());
        response.setTotalElement(page.getTotalElements());
        response.setLast(page.isLast());

        return response;
    }

    @Override
    public SupplierDTO getSupplierById(long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", id + ""));
        return mapToDto(supplier);
    }

    @Override
    public SupplierDTO updateSupplier(SupplierDTO supplierDto, long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", id + ""));
        supplier.setImage(supplierDto.getImage());
        return mapToDto(supplierRepository.save(supplier));
    }

    @Override
    public void deleteSupplierById(long id) {

    }
    private SupplierDTO mapToDto(Supplier supplier) {
        SupplierDTO supplierDTO =  mapper.map(supplier, SupplierDTO.class);
        supplierDTO.setDiamondMarketID(supplierRepository.findAllDiamond(supplier));
        return supplierDTO;
    }
    private Supplier mapToEntity(SupplierDTO supplierDto) {
        return mapper.map(supplierDto, Supplier.class);
    }
}
