package pt.ipleiria.estg.ei.dei.esoft;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Objects;

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
    public List<Contact> getContacts(String... labels) {
        if (labels.length == 0) return new LinkedList<>(contacts);
        List<Contact> result = new LinkedList<>();
        for (var label : labels) {
            var contactsLabel = this.labels.get(label);
            if (contactsLabel == null) continue;
            for (var contact : contactsLabel) {
                if (!result.contains(contact)) {
                    result.add(contact);
                }
            }
        }
        return result;
    }

    public List<Contact> search(String term, String... labels  ) {

        List<Contact> result = new LinkedList<>();
        for (var contact : contacts) {
            if (contact.getFirstName().contains(term) || contact.getLastName().contains(term)) {
                result.add(contact);
            }
        }
        if (labels.length == 0) return result;
        List<Contact> filteredResult = new LinkedList<>();
        for (var label : labels) {
            var contactsLabel = this.labels.get(label);
            if (contactsLabel == null) continue;
            for (var contact : contactsLabel) {
                if (result.contains(contact)) {
                    filteredResult.add(contact);
                }
            }
        }
        return filteredResult;
    }

    public void addContact(Contact contact, String... labels) {
        java.util.function.Predicate<Contact> duplicate = c ->
                Objects.equals(c.getPhone(), contact.getPhone()) ||
                        Objects.equals(c.getEmail(), contact.getEmail());

        if (contacts.stream().noneMatch(duplicate)) {
            contacts.add(contact);
        }

        if (labels.length == 0) return;

        for (var label : labels) {
            if (!this.labels.containsKey(label)) {
                this.labels.put(label, new LinkedList<>());
            }
            var contactsLabel = this.labels.get(label);

            // Adiciona à label se ainda não estiver lá
            if (contactsLabel.stream().noneMatch(duplicate)) {
                contactsLabel.add(contact);
            }
        }
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
    public boolean isEmpty() {
        return contacts.isEmpty();
    }

}
