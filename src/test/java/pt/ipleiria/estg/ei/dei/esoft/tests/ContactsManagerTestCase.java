package pt.ipleiria.estg.ei.dei.esoft.tests;
import org.junit.jupiter.api.*;
import pt.ipleiria.estg.ei.dei.esoft.Contact;
import pt.ipleiria.estg.ei.dei.esoft.ContactsManager;

import java.util.List;

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

    @Test
    public void testListLabels() {
        // 6.1 Lista vazia
        assertTrue(cm.getLabels().isEmpty(), "Sem contactos, a lista de etiquetas devia estar vazia.");

        // 6.2 Vários contactos com a mesma etiqueta
        Contact c1 = new Contact("Ana", "910000001");
        Contact c2 = new Contact("Rui", "910000002");
        cm.addContact(c1, "amigos");
        cm.addContact(c2, "amigos");

        assertEquals(1, cm.getLabels().size(), "Deveria haver apenas 1 etiqueta única.");
        assertEquals(2, cm.getContacts("amigos").size(), "Deveriam existir 2 contactos na etiqueta 'amigos'.");

        // 6.3 Um contacto com várias etiquetas
        Contact c3 = new Contact("Rita", "910000003");
        cm.addContact(c3, "trabalho", "familia");
        assertTrue(cm.getLabels().contains("trabalho") && cm.getLabels().contains("familia"));
    }

    // ==========================================
    // 7. FILTRAR POR ETIQUETA
    // ==========================================

    @Test
    public void testFilterByLabel() {
        Contact c1 = new Contact("Ana", "911111111");
        Contact c2 = new Contact("Rui", "922222222");
        cm.addContact(c1, "amigos");
        cm.addContact(c2, "familia");

        // 7.1 Uma etiqueta
        List<Contact> amigos = cm.getContacts("amigos");
        assertEquals(1, amigos.size());
        assertTrue(amigos.contains(c1));

        // 7.2 Múltiplas etiquetas
        List<Contact> ambos = cm.getContacts("amigos", "familia");
        assertEquals(2, ambos.size(), "Deveria devolver os contactos das duas etiquetas.");

        // 7.3 Lista vazia se etiqueta não existe
        List<Contact> vazia = cm.getContacts("inexistente");
        assertTrue(vazia.isEmpty(), "Deveria devolver lista vazia para etiqueta inexistente.");
    }

    // ==========================================
    // 8. PESQUISA BÁSICA
    // ==========================================

    @Test
    public void testBasicSearch() {
        Contact c1 = new Contact("Joao", "Silva", "911111111", "joao@mail.com");
        cm.addContact(c1);

        // 8.1 Termo exato
        assertEquals(1, cm.search("Joao").size(), "Falhou a procurar termo exato.");
        assertEquals(1, cm.search("911111111").size(), "Falhou a procurar por telefone.");

        // 8.2 Termo parcial
        assertEquals(1, cm.search("joao@").size(), "Falhou a procurar termo parcial.");
        assertEquals(1, cm.search("Sil").size(), "Falhou a procurar termo parcial.");

        // 8.3 Insensível a maiúsculas/minúsculas
        assertEquals(1, cm.search("JOAO").size(), "Pesquisa deve ignorar maiúsculas/minúsculas.");
        assertEquals(1, cm.search("silva").size(), "Pesquisa deve ignorar maiúsculas/minúsculas.");
    }

    // ==========================================
    // 9. PESQUISA COM ETIQUETAS
    // ==========================================

    @Test
    public void testSearchWithLabels() {
        Contact c1 = new Contact("Joao", "Silva", "911111111");
        Contact c2 = new Contact("Joao", "Santos", "922222222");

        cm.addContact(c1, "amigos");
        cm.addContact(c2, "trabalho");

        // 9.1 Procurar apenas dentro da etiqueta
        List<Contact> result = cm.search("Joao", "amigos");
        assertEquals(1, result.size());
        assertEquals("Silva", result.get(0).getLastName(), "Só devia encontrar o João Silva (amigos).");

        // 9.2 Lista vazia se não houver contactos a cumprir o critério
        List<Contact> emptyResult = cm.search("Joao", "familia");
        assertTrue(emptyResult.isEmpty(), "Não existe nenhum João na etiqueta família.");
    }

    @Test
    public void testContactsManagerInitialization() {
        ContactsManager newCm = new ContactsManager();
        assertTrue(newCm.isEmpty(), "A lista de contactos deve começar completamente vazia.");
        assertTrue(newCm.getLabels().isEmpty(), "O gestor de contactos deve iniciar sem nenhuma etiqueta.");
    }

    @Test
    public void testDontAllowDuplicatedEmail() {
        var contact1 = new Contact("Ana", "Silva", "910000000", "ana@mail.com");
        var contact2 = new Contact("Ana", "Gomes", "920000000", "ana@mail.com"); // Email igual, telefone diferente

        cm.addContact(contact1);
        cm.addContact(contact2);

        assertEquals(1, cm.size(), "O sistema permitiu adicionar um contacto com um e-mail já existente!");
    }

    @Test
    public void testLabelsUpdatedAfterRemoval() {
        var contact = new Contact("Pedro", "911222333");
        cm.addContact(contact, "colegas");

        // Verifica se entrou na etiqueta
        assertEquals(1, cm.getContacts("colegas").size());

        // Remove o contacto
        cm.removeContact(contact);

        // Verifica se SAIU da etiqueta
        assertTrue(cm.getContacts("colegas").isEmpty(), "O contacto foi removido da lista geral, mas ficou 'esquecido' dentro da etiqueta!");
    }

}
