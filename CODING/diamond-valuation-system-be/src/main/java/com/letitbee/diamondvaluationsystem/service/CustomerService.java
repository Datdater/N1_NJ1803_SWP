package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.entity.Customer;
import com.letitbee.diamondvaluationsystem.payload.CustomerDTO;
import com.letitbee.diamondvaluationsystem.payload.PaymentMethodDTO;
import com.letitbee.diamondvaluationsystem.payload.Response;


public interface CustomerService {

    Response<CustomerDTO> getAllCustomer(int pageNo, int pageSize, String sortBy, String sortDir);

    CustomerDTO getCustomerById(long id);

    CustomerDTO createCustomerInformation(CustomerDTO customerDto);

    CustomerDTO updateCustomerInformation(CustomerDTO customerDto, Long id);
//    void deleteCustomerById(Long id);

    CustomerDTO  getCustomerByPhoneOrName(String phone,String name);

    interface PaymentMethod {
        Response<PaymentMethodDTO> getAllPaymentMethod();
    }
}
