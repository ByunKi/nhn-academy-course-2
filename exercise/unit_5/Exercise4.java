package exercise.unit_5;

public class Exercise4 {
    public static void main(String[] args) {
        Deck deck = new Deck();
        BlackjackHand hand = new BlackjackHand();

        deck.shuffle();
        for (int i = 0; i < (int) (Math.random() * 5) + 2; i++) {
            hand.addCard(deck.dealCard());
            System.out.println(hand.getCard(i));
        }

        System.out.println(hand.getBlackjackValue());
    }
}
