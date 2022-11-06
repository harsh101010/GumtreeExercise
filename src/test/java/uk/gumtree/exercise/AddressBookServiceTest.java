package uk.gumtree.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class AddressBookServiceTest {

    AddressBookService addressBookService;

    @BeforeEach
    void setUp() {
        addressBookService = new AddressBookService("addressbook.txt");
    }

    @Test
    void shouldGetNoOfMalesFromAValidAddressBook() {
        long expectedNoOfMales = 3;
        long actualNoOfMales = addressBookService.getNoOfMales();
        assertThat(actualNoOfMales).as("No of males does not match").isEqualTo(expectedNoOfMales);
    }
}