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

    @Test
    public void testRemoveContact() {
        var contact = new Contact("foo", "931 456 907");
        cm.addContact(contact);
        assertEquals(1, cm.size());
        cm.removeContact(contact);
        // TODO implement method cm.isEmpty() too!
        // At class ContactsManager:
        // public boolean isEmpty() { return contacts.isEmpty(); }
        assertTrue(cm.isEmpty(), "<give a meaningful message>");
    }
    @Test
    public void testTryRemoveNonexistentContact() {
        var foobar = new Contact("Foo", "Bar", "928 032 179", "foo@bar.test");
        var dummy = new Contact("Mr.", "Dummy","964 475 145", "mr@dummy.test");
        cm.addContact(foobar);
        assertEquals(1, cm.size());
        cm.removeContact(dummy);
        assertFalse(cm.isEmpty(), "<give a meaningful message>");
    }
    @Test
    public void testDontRemoveUnlessIsSameContact() {
        var foo = new Contact("foo", "91X ABC DEF");
        var bar = new Contact("bar", "91X ABC DEF");
        cm.addContact(foo);
        assertEquals(1, cm.size());
        cm.removeContact(bar);
        assertFalse(cm.isEmpty(), "<give a meaningful message>");
    }

}
