public class Main {

    public static void main(String[] args) {
        System.out.println("initial - ");
        setup();
        System.out.println("\n Alternative -");
        setupAlt();
    }

    public static void setup() {
        ContactNet c = new ContactNet();
        c.connectionThreshold = 10;

        Person Per = new Person("Per");
        Person Pål = new Person("Pål");
        Person Espen = new Person("Espen");
        Person Kongen = new Person("Kongen");
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

    public static void setupAlt() {
        ContactNetAlt c = new ContactNetAlt();
        c.connectionThreshold = 10;

        Person Per = new Person("Per");
        Person Pål = new Person("Pål");
        Person Espen = new Person("Espen");
        Person Kongen = new Person("Kongen");
        for (int i = 0; i < 10; i++) {
            c.registerConversationAlt(Per, Pål);
            c.registerConversationAlt(Pål, Per);
            c.registerConversationAlt(Per, Espen);
            c.registerConversationAlt(Espen, Per);
            c.registerConversationAlt(Espen, Kongen);
            c.registerConversationAlt(Kongen, Espen);
            c.registerConversationAlt(Kongen, Per);
            c.registerConversationAlt(Per, Espen);
        }

        Person person = Per;
        c.printDirectAlt(person);
        c.printIndirectAlt(person);
    }

}

