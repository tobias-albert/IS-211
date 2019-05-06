import java.util.Random;

public class Main {



    public static void main(String[] args) {
        ContactNet c = new ContactNet();
        c.setThreshold(10);
        Random r = new Random();

        Person Per = new Person("Per");
        Person Pål = new Person("Pål");
        Person Espen = new Person("Espen");
        Person Kongen = new Person("Kongen");
        c.persons.add(Per);
        c.persons.add(Pål);
        c.persons.add(Espen);
        c.persons.add(Kongen);
        for (int i = 0; i < 10; i++) {
            c.registerConversation(Per, Pål);
            c.registerConversation(Pål, Per);
            c.registerConversation(Per, Espen);
            c.registerConversation(Espen, Per);
            c.registerConversation(Espen, Kongen);
            c.registerConversation(Kongen, Espen);
            c.registerConversation(Kongen, Per);
            c.registerConversation(Per, Espen);
        }

        Person person = Per;
        c.printDirect(person);
        c.printIndirect(person);
    }
}
