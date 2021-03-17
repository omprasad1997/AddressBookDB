package com.javapractice;

import com.javapractise.AddressBookData;
import com.javapractise.AddressBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddressBookServiceTest {

    @Test
    public void givenAddressBookInDB_WhenRetrieved_ShouldMatchAddressBookContactCount(){
        AddressBookService addressBookService = new AddressBookService();
        List<AddressBookData> addressBookDataList = addressBookService.readAddressBookDataFromDB(AddressBookService.IOService.DB_IO);
        Assertions.assertEquals(4,addressBookDataList.size());
    }
}