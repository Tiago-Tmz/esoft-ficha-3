package pt.ipleiria.estg.ei.dei.esoft.tests;

import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.ei.dei.esoft.Contact;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTestCase {

    @Test
    public void testCreateContact() {
        var contact = new Contact("foo", "bar", "912 345 678");
        assertEquals("foo", contact.getFirstName());
        assertEquals("bar", contact.getLastName());
        assertEquals("912 345 678", contact.getPhone());
    }

    @Test
    public void testAllConstructors() {
        // Testar construtor simples
        Contact c1 = new Contact("João", "911111111");
        assertEquals("João", c1.getFirstName());
        assertEquals("911111111", c1.getPhone());
        assertNull(c1.getLastName());

        // Testar construtor completo
        Date bday = new Date();
        Contact c2 = new Contact("Maria", "Silva", "922222222", "maria@mail.com", bday);
        assertEquals("Maria", c2.getFirstName());
        assertEquals("Silva", c2.getLastName());
        assertEquals("maria@mail.com", c2.getEmail());
        assertEquals(bday, c2.getBirthday());
    }

    @Test
    public void testSettersAndGetters() {
        Contact contact = new Contact("Nome", "900000000");

        contact.setFirstName("Carlos");
        contact.setLastName("Santos");
        contact.setEmail("carlos@mail.com");
        contact.setPhone("933333333");

        assertEquals("Carlos", contact.getFirstName());
        assertEquals("Santos", contact.getLastName());
        assertEquals("carlos@mail.com", contact.getEmail());
        assertEquals("933333333", contact.getPhone());
    }
}