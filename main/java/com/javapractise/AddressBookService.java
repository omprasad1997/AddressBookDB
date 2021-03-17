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

    public boolean checkAddressBookDataInSyncWithDB(String name) {
        List<AddressBookData> addressBookDataList = addressBookDBService.getAddressBookData(name);
        return addressBookDataList.get(0).equals(getAddressBookData(name));
    }

    public void updatePersonCity(String name, String city) {
        int result  = addressBookDBService.updateAddressBookData(name,city);
        if(result == 0) return;
        AddressBookData addressBookData = this.getAddressBookData(name);
        if(addressBookData!=null) addressBookData.city= city;
    }

    private AddressBookData getAddressBookData(String name) {
        return this.addressBookData.stream()
                .filter(employeeDataItem -> employeeDataItem.city.equals(name))
                .findFirst()
                .orElse(null);
    }
}
