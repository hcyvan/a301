package com.navy.c.service;

import com.navy.c.exception.OrderListLengthException;
import com.navy.common.exception.ResourceNotFoundException;
import com.navy.common.model.Address;
import com.navy.common.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    private Integer ADDRESS_LIST_MAX = 10;
    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void createAddress(String addressContent, String phone, String customerId) throws OrderListLengthException{
        List<Address> addresses = addressRepository.findAll();
        if(addresses.size() > ADDRESS_LIST_MAX) {
            throw new OrderListLengthException(String.format("Order List Size is more than %d", ADDRESS_LIST_MAX));
        }
        Address oldDefaultAddress = addresses.stream().filter(a -> a.getCustomerId().equals(customerId))
                .filter(a -> a.getDefault().equals(true)).findFirst().orElse(null);
        if (oldDefaultAddress != null) {
            oldDefaultAddress.setDefault(false);
            addressRepository.save(oldDefaultAddress);
        }

        Address address = new Address(addressContent, phone, customerId);
        address.setDefault(true);
        addressRepository.save(address);
    }

    @Override
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(String id) {
        return addressRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Address", "id", id)
        );
    }

    @Override
    public void deleteAddressById(String id) {
        addressRepository.deleteById(id);
    }

}
