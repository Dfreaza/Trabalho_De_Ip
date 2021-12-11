/**
*@author Guilherme Wind fc-58640
*@author Diogo Freaza fc-56969
*
*/


import java.util.Scanner;

public class Sokoban {
    public static void main(String[] args){

      System.out.println("Welcome to Sokoban!");
      System.out.println("Consider that Sokoban symbols are represented by the following digits:");
      System.out.println(" "+"1 - blank  "+"2 - box  "+"3 - goal  "+"4 - wall  "+"5 - player  ");
      System.out.println("The symbolic representation on the right may help you while playing.");

        Scanner scan = new Scanner(System.in);
        int[][] grid = {{4,4,4,1,1,1,4,1},
                        {4,3,5,2,1,1,4,1},
                        {4,4,4,1,2,3,4,1},
                        {4,3,4,4,2,1,4,1},
                        {4,1,4,1,3,1,4,4},
                        {4,2,1,2,2,2,3,4},
                        {4,1,1,1,3,1,1,4},
                        {4,4,4,4,4,4,4,4}};

        int[][] goals = {{1,1},
                         {2,5},
                         {3,1},
                         {4,4},
                         {5,6},
                         {6,4},
                         {5,3}};

                         char opcaoEscolhida;
                         do{
                           print(grid, goals);
                           System.out.println("Select one of the following options: (R)ight, (L)eft, (U)p, (D)own, (E)xit");
                           opcaoEscolhida = readOption(scan);
                           if(opcaoEscolhida != 'E' && opcaoEscolhida != 'e'){
                             move(grid, goals, opcaoEscolhida);
                           }
                         }while(opcaoEscolhida != 'E' && opcaoEscolhida != 'e');
                           System.out.println("Thank you for playing Sokoban!");
                         }



  /** 
  *Verifica se a matriz não é nula, quadrada e que tem a ocorrência do número 5 (exatamente uma ocorrencia),
  *ocorrencia do número 2 (pelo menos uma vez), e a ocorrencia do número 3 (não podendo utrapassar a ocorrencia do numero 2).
  *
  *@param grid Uma matriz dada com o mapa do jogo 
  *@ensures {@code /result == true || /result == false}
  *@return Retorna um boleano depois de verificar a condição de grid
  */
  public static boolean isValidGrid(int[][] grid){
    boolean resultado = true;
    int ocorre2 = 0;
    int ocorre3 = 0;
    int ocorre5 = 0;
    int ocorre6 = 0;
    int ocorre7 = 0;
    int ocorre8 = 0;
    int ocorre9 = 0;

    if (grid != null && grid.length > 2 && grid[0].length > 2){
        for (int i = 0; i < grid.length; i++){
        for(int j = 0; j < grid.length; i++){

            if(grid[i][j] == 2){
            ocorre2++;
            }
            else if (grid[i][j] == 3){
                ocorre3++;
            }
            else if (grid[i][j] == 5){
                ocorre5++;
            }
            else if (grid[i][j] == 6){
                ocorre6++;
            }
            else if (grid[i][j] == 7){
                ocorre7++;
            }
            else if (grid[i][j] == 8){
                ocorre8++;
            }
            else if (grid[i][j] == 9){
                ocorre9++;
            }
          }
        }

        int naoOcorre = ocorre6 + ocorre7 + ocorre8 + ocorre9;

        if (ocorre5 != 1 && ocorre2 < 1 && ocorre3 > ocorre2 && naoOcorre != 0){
            resultado = false;
            }
    }
    else{
        resultado = false;
    }
      return resultado;
    }
  

    /** 
  *Verifica se a posicao position é uma posicao valida na matriz grid
  *
  *@param grid Uma matriz dada com o mapa do jogo 
  *@param position Um vetor dado com uma posicao do jogo
  *@requires {@code isValidGrid(grid)}
  *@ensures {@code /result == true || /result == false}
  *@return Retorna um boleano depois de verificar se a posicao position esta em grid
  */
  public static boolean positionInGrid(int[][] grid, int[] position){
    boolean resultado = false;
    boolean existePosition = position != null;
    if (existePosition){
      boolean tamanho = position.length == 2;
      boolean tamanhoZero = 0 <= position[0] && position[0]< grid.length;
      boolean tamanhoUm = 0 <= position[1] && position[1] < grid[1].length;
      if (tamanho && tamanhoZero && tamanhoUm){
        resultado = true;
      }
    }
    return resultado;
  }


    /** 
  *Verifica se as posicoes objetivo estao assinaladas na grid
  *
  *@param grid Uma matriz dada com o mapa do jogo 
  *@param goals Uma matriz dada com as posicoes de goals 
  *@requires {@code isValidGrid(grid)}
  *@ensures {@code /result == true || /result == false}
  *@return Retorna um boleano depois de verificar as condicoes de goals
  */
  public static boolean goalsInGrid(int[][] grid, int[][] goals){
    boolean resultado = true;
    int numDeDois = 0;
    if (isValidGrid(goals)){
      for (int i = 0;i < goals.length; i++){
        if (!positionInGrid(grid, goals[i])){
          resultado = false;
        }
      }
      if (resultado){
        for(int i = 0; i < goals.length; i++){
          for(int j = 0; j < goals[i].length; j++){
            if (goals[i][j] == 2){
              numDeDois++;
            }
            else if(goals[i][j] != 3){
              resultado = false;
            }
          }
        }
      if (goals.length != numDeDois){
          resultado = false;
        }
      }
    }
    else{
      resultado = false;
    }

    return resultado;
  }
  



    /** 
  *Verifica se o char direction e uma direcao de movimento valida
  *
  *@param direction um char com a direcao que o personagem move 
  *@requires {@code Type(direction == char)}  
  *@ensures {@code /result == true || /result == false}
  *@return Retorna um boleano depois de verificar a condição do char direction 
  */
  public static boolean isValidDirection(char direction){
    boolean resultado = false;
    if (direction == 'R' || direction == 'r' || direction == 'L' || direction == 'l' || direction == 'u' || direction == 'U' || direction == 'd' || direction == 'D'){
      resultado = true;
    }

    return resultado;
  }


    /**
  *Recebe um char escrito pelo usuario, se o que for escrito nao for uma opcao valida manda uma mensagem de erro e o char outra vez 
  *
  *@param sc Um Scanner com o input do usuário 
  *@requires {@code sc != null} 
  *@ensures retorna um caracter valido
  *@return Retorna um caracter (depois de verificar se é valido)
  */
  public static char readOption(Scanner sc){

    char readOption;
    boolean valido = false;
    do {
        readOption = sc.next().charAt(0);
        valido = isValidDirection(readOption);
        if (!valido && readOption != 'E' && readOption != 'e'){
        System.out.println("not valid");
        }
      }while (!valido && readOption != 'E' && readOption != 'e');

      return readOption;
    }


  
    /** 
  *Devolve um vetor com o deslocamento com duas posicoes: A primeira para as linha a segunda para as colunas
  *
  *@param direction Um caracter dado pelo usuario
  *@requires {@code isValidDirection(direction)}
  *@ensures {@code \result.length==2 && \result[0]*\result[1]==0 && -1≤\result[i]≤1}
  *@return Retorna um vetor com o deslocamento do char direction
  */
  public static int[] delta(char direction){
    int[] lista = new int[2];
    if (direction == 'R' || direction == 'r'){
      lista[0] = 0;
      lista[1] = 1;
    }
    else if (direction == 'L' || direction == 'l'){
      lista[0] = 0;
      lista[1] = -1;
    }
    else if (direction == 'U' || direction == 'u'){
      lista[0] = -1;
      lista[1] = 0;
    }
    else if (direction == 'D' || direction == 'd'){
      lista[0] = 1;
      lista[1] = 0;
    }
    return lista;
  }


  /** 
  *Procura pela posicao do jogador (digito 5) na grid e devolve num vetor
  *
  *@param grid Uma matriz dada com o mapa do jogo 
  *@requires {@code isValidDirection(direction)}
  *@ensures {@code positionInGrid(grid,\result)}
  *@return Retorna um vetor com a posiçao do jogador
  */
  public static int[] getPlayer(int[][] grid){
    int[] posicao = new int[2];
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid.length; j++){
        if(grid[i][j] == 5){
          posicao[0] = i;
          posicao[1] = j;
        }
      }
    }
    return posicao;
  }




    /** 
  *Verifica se a posição de position não está ocupada na grid
  *
  *@param grid Uma matriz dada com o mapa do jogo 
  *@param position Um vetor dado com uma posicao do jogo  
  *@requires {@code isValidGrid(grid) && positionInGrid(grid, position)}
  *@ensures {@code /result == true || /result == false}
  *@return Retorna um boleano depois de verificar se position nao esta ocupada 
  */
  public static boolean isAvailable(int[][] grid, int[] position){
    boolean ocupado = false;
    if(grid[position[0]][position[1]] != 3 || grid[position[0]][position[1]] != 1){
      ocupado = true;
    }
    return ocupado;
  }



    /** 
  *Verifica se a posicao da em position corresponde a uma posicao objetivo na matriz goals 
  *
  *@param grid Uma matriz dada com o mapa do jogo 
  *@param goals Uma matriz dada com as posicoes de goals 
  *@param position Um vetor dado com uma posicao do jogo 
  *@requires {@code isValidGrid(grid) && positionInGrid(grid, position) &&  goalsInGrid(grid,goals)}
  *@ensures {@code /result == true || /result == false}
  *@return Retorna um boleano depois de verificar a condição da matriz
  */
  public static boolean belongsTo(int[][] grid, int[][] goals, int[] position){
      boolean resultado = false;

      for(int i = 0; i < goals.length; i++){
        if(goals[i][0] == position[0] && goals[i][1] == position[1]){
        resultado = true;
      }
    }
      return resultado;
    }


    /** 
  *Verifica se o jogador se pode deslocar para a posicao da por char direction
  *
  *@param grid Uma matriz dada com o mapa do jogo 
  *@param direction Um caracter dado pelo usuario
  *@requires {@code isValidGrid(grid) && isValidDirection(direction)} 
  *@ensures {@code /result == true || /result == false}
  *@return Retorna o resultado com o valor correto da variavel booleana
  */
  public static boolean ableToMove(int[][] grid, char direction){
    boolean resultado = false;

    int[] localDoPlayer = getPlayer(grid);

    int[] direcao = delta(direction);

    int localParaMoverHorizontal = localDoPlayer[1] + direcao[1];

    int localParaMoverVertical = localDoPlayer[0] + direcao[0];

    int[] vetor = {localParaMoverVertical, localParaMoverHorizontal};

    if(positionInGrid(grid, vetor)){
      int direcaoIndicada = grid[localParaMoverVertical][localParaMoverHorizontal];
        if(direcaoIndicada == 1 || direcaoIndicada == 3){
          resultado = true;
        }else if (direcaoIndicada == 2){
          int[] posicaoPosCaixa = {vetor[0]+direcao[0], vetor[1]+direcao[1]};
          if(positionInGrid(grid, posicaoPosCaixa)){
          int direcaoPosIndicada = grid[posicaoPosCaixa[0]][posicaoPosCaixa[1]];
          resultado = direcaoPosIndicada == 1 || direcaoPosIndicada == 3;
        }
        }
      }

    return resultado;
  }
  
  /** 
  *Verifica se o jogador pode se deslocar na direcao dada no caso de conseguir mover atualiza a grid deslocando o jogador na posicao dado por direction
  *
  *@param grid Uma matriz dada com o mapa do jogo 
  *@param goals Uma matriz dada com as posicoes de goals
  *@requires {@code isValidGrid(grid) && isValidDirection(direction) && goalsInGrid(grid,goals)} 
  *@return none
  */
  public static void move(int[][] grid, int[][] goals, char direction){
    if (ableToMove(grid, direction)){
        int[] localDoPlayer = getPlayer(grid);

        int[] direcao = delta(direction);

        int localParaMoverHorizontal = localDoPlayer[1] + direcao[1];
        int localParaMoverVertical = localDoPlayer[0] + direcao[0];

        int destino = grid[localParaMoverVertical][localParaMoverHorizontal];

          grid[localParaMoverVertical][localParaMoverHorizontal] = 5;

          if (destino == 2)
              grid[localParaMoverVertical + direcao[0]][localParaMoverHorizontal + direcao[1]] = 2;

          if (belongsTo(grid, goals, localDoPlayer)){
              grid[localDoPlayer[0]][localDoPlayer[1]] = 3;
          } else{
              grid[localDoPlayer[0]][localDoPlayer[1]] = 1;
          }
        }
    }


  /**
  *Imprime a grid dada com valores numerios e ao mesmo tempo imprime a matriz com simbolos escolhidos
  *
  *@param grid Uma matriz dada com o mapa do jogo 
  *@param goals Uma matriz dada com as posicoes de goals
  *@requires {@code isValidGrid(grid) && goaslInGrid(grid,goals)}
  *@ensures faz o print da matriz
  *@return none
  */
  public static void print(int[][] grid, int[][] goals){
      for (int i = 0; i < grid.length; i++){
        for (int j = 0; j < grid[i].length; j++){
          System.out.print(grid[i][j] + " ");
        }
        System.out.print(" | ");
        for (int k = 0; k < grid[i].length; k++){
          if(grid[i][k] == 1){
            System.out.print("   ");
          }else if(grid[i][k] == 2){
            int[] vetor = {i,k};
            if(belongsTo(grid, goals, vetor)){
            System.out.print("  *");
          }
          else{
            System.out.print("  B");
          }
          }else if(grid[i][k] == 3){
            System.out.print("  G");
          }else if(grid[i][k]== 4){
            System.out.print("  -");
          }else if(grid[i][k] == 5){
            System.out.print("  P");
          }
        }
      System.out.println();
          }
    }

}