package com.navy.c.service;

import com.navy.c.exception.OrderListLengthException;
import com.navy.common.model.Address;

import java.util.List;

public interface AddressService {
    void createAddress(String address, String phone, String customerId) throws OrderListLengthException;
    List<Address> getAddresses();
    Address getAddressById(String id);
    void deleteAddressById(String id);
}
