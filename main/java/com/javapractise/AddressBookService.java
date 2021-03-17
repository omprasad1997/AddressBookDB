package com.javapractise;

import java.util.List;

public class AddressBookService {

    public enum IOService{DB_IO}
    private List<AddressBookData> addressBookData;
    private AddressBookDBService addressBookDBService;

    public AddressBookService(){
        addressBookDBService = AddressBookDBService.getInstance();
    }

    public List<AddressBookData> readAddressBookDataFromDB(IOService ioService) {
        if(ioService.equals(IOService.DB_IO))
            this.addressBookData = addressBookDBService.readData();
        return this.addressBookData;
    }
}
