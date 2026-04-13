import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

public class ContactsManager {
    private List<Contact> contacts;
    private HashMap<String, List<Contact>> labels;

    public ContactsManager() {
        contacts = new LinkedList<>();
        labels = new HashMap<>(200);
    }

    public List<String> getLabels() {
        return new LinkedList<>(labels.keySet());

    }
    public List<Contact> getContacts(String labels) {
        return this.labels.get(labels);
    }

    public List<Contact> search(String term, String labels  ) {
        List<Contact> result = new LinkedList<>();
        for (Contact contact : this.labels.get(labels)) {
            if (contact.getFirstName().contains(term) || contact.getLastName().contains(term) || contact.getPhone().contains(term) || contact.getEmail().contains(term)) {
                result.add(contact);
            }
        }
        return result;
    }

    public void addContact(Contact contact, String labels) {
        this.contacts.add(contact);
        if (!this.labels.containsKey(labels)) {
            this.labels.put(labels, new LinkedList<>());
        }
        this.labels.get(labels).add(contact);
    }

    public void removeContact(Contact contact){
        this.contacts.remove(contact);
        for (List<Contact> contacts : this.labels.values()) {
            contacts.remove(contact);
        }
    }

    public int size() {
        return contacts.size();
    }

}
