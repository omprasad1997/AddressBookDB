package com.javapractice;

import com.javapractise.AddressBookData;
import com.javapractise.AddressBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class AddressBookServiceTest {

    @Test
    public void givenAddressBookInDB_WhenRetrieved_ShouldMatchAddressBookContactCount(){
        AddressBookService addressBookService = new AddressBookService();
        List<AddressBookData> addressBookDataList = addressBookService.readAddressBookDataFromDB(AddressBookService.IOService.DB_IO);
        Assertions.assertEquals(4,addressBookDataList.size());
    }

    @Test
    public void givenNewCityForPerson_WhenUpdated_ShouldSyncWithDB() {
        AddressBookService addressBookService = new AddressBookService();
        List<AddressBookData>  addressBookDataList = addressBookService.readAddressBookDataFromDB(AddressBookService.IOService.DB_IO);
        addressBookService.updatePersonCity("ganesh","bangalore");
        boolean result = addressBookService.checkAddressBookDataInSyncWithDB("ganesh");
        Assertions.assertTrue(result);

    }

    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatchAddressBookContactCount() {
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.readAddressBookDataFromDB(AddressBookService.IOService.DB_IO);
        LocalDate startDate = LocalDate.of(2018,01,01);
        LocalDate endDate = LocalDate.now();
        List<AddressBookData>  addressBookDataList =
                addressBookService.readEmployeePayrollDataForDateRange(AddressBookService.IOService.DB_IO,startDate,endDate);
        System.out.println(addressBookDataList);
        Assertions.assertEquals(4,addressBookDataList.size());

    }
}
