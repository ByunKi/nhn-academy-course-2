package exercise.unit_5;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise5 {
    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.play();
    }
}

class BlackjackGame {
    private final Deck deck;
    private Player player;
    private Player computer;

    public BlackjackGame() {
        deck = new Deck();
        player = new Player();
        computer = new Player();
    }

    public Deck getDeck() {
        return deck;
    }

    public void play() {
        getDeck().shuffle();

        for (int i = 0; i < 2; i++) {
            player.getHand().addCard(getDeck().dealCard());
            computer.getHand().addCard(getDeck().dealCard());
        }
        if (doesPlayerWin(0)) {
            System.out.println("사용자 승리!");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            String decision = "";
            do {
                System.out.println(player.getHand().getBlackjackValue());
                System.out.print("Stand: S, Hit: H: ");
                decision = scanner.nextLine().toUpperCase();

                if (!(decision.equals("S") || decision.equals("H"))) {
                    System.out.println("다시");
                    continue;
                }

                if (decision.equals("H")) {
                    player.getHand().addCard(getDeck().dealCard());

                    if (doesPlayerWin(1)) {
                        System.out.println("사용자 승리!");
                        return;
                    }
                }

            } while (decision.equals("H"));

            do {
                computer.getHand().addCard(getDeck().dealCard());
                System.out.println(computer.getHand().getBlackjackValue());
                if (doesPlayerWin(2)) {
                    System.out.println("사용자 승리!");
                    return;
                } else {
                    System.out.println("컴퓨터 승리!");
                    return;
                }

            } while (computer.getHand().getBlackjackValue() <= 16);

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

    }

    private boolean doesPlayerWin(int situationNumber) {
        int playerScore = player.getHand().getBlackjackValue();
        int computerScore = computer.getHand().getBlackjackValue();

        switch (situationNumber) {
            case 0: {
                if (playerScore == 21) {
                    return true;
                } else if (computerScore == 21 || playerScore == computerScore) {
                    return false;
                }

                break;
            }

            case 1: {
                if (playerScore > 21) {
                    return false;
                } else if (playerScore == 21) {
                    return true;
                }

                break;
            }

            case 2: {
                if (computerScore > 21) {
                    return true;
                } else if (computerScore == 21) {
                    return false;
                }

                return playerScore >= computerScore;
            }

            default:
                return false;
        }

        return false;
    }
}

class Player {
    private final BlackjackHand hand;

    public Player() {
        this.hand = new BlackjackHand();
    }

    public BlackjackHand getHand() {
        return hand;
    }
}