package pt.ipleiria.estg.ei.dei.esoft.tests;
import org.junit.jupiter.api.*;
import pt.ipleiria.estg.ei.dei.esoft.Contact;
import pt.ipleiria.estg.ei.dei.esoft.ContactsManager;

import static org.junit.jupiter.api.Assertions.*;

public class ContactsManagerTestCase {
    private ContactsManager cm;
    @BeforeEach
    public void setUp() {
        cm = new ContactsManager();
    }
    @Test
    public void testAddContact() {
        var contact = new Contact("foo", "917 252 063");
        cm.addContact(contact);
        assertEquals(1, cm.size());
        assertSame(contact, cm.getContacts().get(0));
    }

    @Test
    public void testDontAllowDuplicatedContacts() {
        var contact1 = new Contact("foo", "931 456 907");
        var contact2 = new Contact("foo", "931 456 907"); // same contact!
        cm.addContact(contact1);
        cm.addContact(contact2);
        // Help the developer by writing a friendly message 
        assertEquals(1, cm.size(), "Duplicated contacts!");
    }

}
