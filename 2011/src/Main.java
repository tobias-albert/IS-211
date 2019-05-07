public class Main {

    public static void main(String[] args) {
        System.out.println("initial - ");
        setup();
        System.out.println("\n Alternative -");
        setupAlt();
    }

    /*
    Graphical representation of graph can be found at github in the 2011 folder
     */

    public static void setup() {
        ContactNet c = new ContactNet();
        int threshold = 10;

        Person Per = new Person("Per");
        Person Pål = new Person("Pål");
        Person Espen = new Person("Espen");
        Person Kongen = new Person("Kongen");
        for (int i = 0; i < 10; i++) {
            c.registerConversation(Per, Pål);
            c.registerConversation(Pål, Per);
            c.registerConversation(Pål, Espen);
            c.registerConversation(Espen, Pål);
            c.registerConversation(Espen, Kongen);
            c.registerConversation(Kongen, Espen);
            c.registerConversation(Pål, Kongen);
            c.registerConversation(Kongen, Pål);
        }

        Person person = Per;
        c.printDirect(person, threshold);
        c.printIndirect(person, threshold);
    }

    public static void setupAlt() {
        ContactNetAlt c = new ContactNetAlt();
        int threshold = 10;

        Person Per = new Person("Per");
        Person Pål = new Person("Pål");
        Person Espen = new Person("Espen");
        Person Kongen = new Person("Kongen");
        for (int i = 0; i < 10; i++) {
            c.registerConversationAlt(Per, Pål);
            c.registerConversationAlt(Pål, Per);
            c.registerConversationAlt(Pål, Espen);
            c.registerConversationAlt(Espen, Pål);
            c.registerConversationAlt(Espen, Kongen);
            c.registerConversationAlt(Kongen, Espen);
            c.registerConversationAlt(Pål, Kongen);
            c.registerConversationAlt(Kongen, Pål);
        }

        Person person = Per;
        c.printDirectAlt(person, threshold);
        c.printIndirectAlt(person, threshold);
    }

}

