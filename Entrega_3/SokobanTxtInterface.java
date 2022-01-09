/**
 * @author Guilherme Wind - fc58640
 * @author Diogo Freaza - fc56969
 */


import java.util.Scanner;
public class SokobanTxtInterface{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Menu(scan);
    }

    /**
     * Informa ao player o menu disponivel
     * 
     * @param scan Scanner para ler o direction dado
     */
    public static void Menu(Scanner scan){
        System.out.println("Welcome to Sokoban!");
        System.out.println("W = Up,  S = Down,  A = Left,  D = Rigth,  E = Exit,  R = Restart");
        Game(scan);
    }

    /**
     * Inicia o jogo conforme os direction dados
     * 
     * @param scan Scanner para as instruçoes dadas
     */
    public static void Game(Scanner scan){
        SokobanGame game = new SokobanGame();

        char direction;

        do{
            System.out.println(game.toString());
            direction = scan.next().charAt(0);
            
            if(!game.levelCompleted()){
                if (direction == 'W' || direction == 'w'){
                    game.move(Direction.UP);
                }
                else if (direction == 'A' || direction == 'a'){
                    game.move(Direction.LEFT);
                }
                else if (direction == 'D' || direction == 'd'){
                    game.move(Direction.RIGHT);
                }
                else if (direction == 'S' || direction == 's'){
                    game.move(Direction.DOWN);
                }
                else if(direction == 'R' || direction == 'r'){
                    game.restartLevel();
                }else if (direction != 'E' && direction != 'e'){
                        System.out.println("not valid");
                } 
            }
            if(game.levelCompleted() && !game.isTerminated()){
                System.out.println(game.toString());
                System.out.println("Press c to proceed to the next level or r to restart level!");
                direction = scan.next().charAt(0);
                if(direction == 'c' || direction == 'C'){
                    game.loadNextLevel();
                }
                else if(direction == 'r' || direction == 'R'){
                    game.restartLevel();
                }
            }
        }while(direction != 'E' && direction != 'e' && !game.isTerminated());
        System.out.println("You Win and and Thanks to play Sokoban Game!");
    }
}
