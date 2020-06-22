public class Euchre{
    public static void main(String[] args) {
        GamePlay game = new GamePlay();
        game.shuffle();
        int[] dealStyle = Logic.dealStyle(3, 2, 3, 2);
        game.deal(dealStyle);
        System.out.println(game);
        System.out.println("The kitty is " + game.getKitty());
        game.chooseTrump();
        System.out.println(game);
        game.playRound();
        System.out.println(game);
        game.playRound();
        System.out.println(game);
        game.playRound();
        System.out.println(game);
        game.playRound();
        System.out.println(game);
        game.playRound();
        System.out.println(game);
        game.endOfRound();
    }
}