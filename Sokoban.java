import java.util.Scanner;

public class Sokoban {
    public static void main(String[] args) {

      Scanner scan = new Scanner(System.in);

      System.out.println("Welcome to Sokoban!");
      System.out.println("Consider that Sokoban symbols are represented by the following digits:");
      System.out.println(" "+"1 - blank  "+"2 - box  "+"3 - goal  "+"4 - wall  "+"5 - player  ");
      System.out.println("The symbolic representation on the right may help you while playing.");
      

        int[][] matriz ={{1,1},
                         {2,5},
                         {3,1},
                         {4,4},
                         {5,6},
                         {6,4},
                         {5,3}};

        int[][] matriz2 = {{4, 4 ,4, 1, 1, 1, 4, 1},{4, 3, 5, 2, 1, 1, 4, 1},{4, 4 ,4, 1, 2, 3, 4, 1},
                           {4, 3, 4, 4, 2, 1, 4, 1},{4, 1, 4, 1, 3, 1, 4, 4},{4, 2, 1, 2, 2, 2, 3, 4},
                           {4, 1, 1, 1, 3, 1, 1, 4}, {4, 4, 4, 4, 4, 4, 4, 4}};
        char opcaoEscolhida;
        do{
          print(matriz2, matriz);
          System.out.println("Select one of the following options: (R)ight, (L)eft, (U)p, (D)own, (E)xit");
          opcaoEscolhida = readOption(scan);
        }while(opcaoEscolhida != 'E');
          System.out.println("Thank you for playing Sokoban!");

      }








        //int[] lista = null;
       //boolean resultado = positionInGrid(matriz, lista);
        //System.out.println(resultado);
    
        //Scanner sc = new Scanner(System.in);
        //char r = readOption(sc);
        //System.out.println(r);

    

       //int [][] matrizTeste = {{4, 3, 5, 2, 1, 1, 4, 1},{4, 4 ,4, 1, 1, 1, 4, 1},{4, 4 ,4, 1, 1, 1, 4, 1}};
       //int[][] matriz3 = null;
       //System.out.println(isValidGrid(matriz3));
       //System.out.println(ableToMove(matrizTeste, 'D'));
        
    


  /*
  *Verifica se a matriz não é nula, quadrada e que tem a ocorrência do número 5 (exatamente uma ocorrẽncia),
  *ocorrência do número 2 (pelo menos uma vez), e a ocorrência do número 3 (não podendo utrapassar a ocorrência do numeor 2).
  *
  *@param Uma matriz (int[][] grid)
  *@ensures
  *@return Retorna um boleano depois de verificar a condição da matriz
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

  public static boolean positionInGrid(int[][] grid, int[] position){
    boolean resultado = false;
    boolean existePosition = position != null;
    if (existePosition){
      boolean tamanho = position.length == 2;
      boolean tamanhoZero = 0 <= position[0] || position[0]< grid.length;
      boolean tamanhoUm = 0 <= position[1] || position[1] < grid[1].length;
      if (tamanho && tamanhoZero && tamanhoUm){
        resultado = true;
      }
    }
    return resultado;
  }

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

  public static boolean isValidDirection(char direction){
    boolean resultado = false;
    if (direction == 'R' || direction == 'L' || direction == 'U' || direction == 'D' || direction == 'E'){
      resultado = true;
    }
    
    return resultado;
  }

  public static char readOption(Scanner scan){

    char readOption;
    boolean valido = false;
    do {
        readOption = scan.next().charAt(0);
        valido = isValidDirection(readOption);
        if(!valido){
          System.out.println("not valid");
        }
      }while (!valido);


      return readOption;  
    }

  public static int[] delta(char direction){
    int[] lista = new int[2];
    if (direction == 'R'){
      lista[0] = 0;
      lista[1] = 1;
    }
    else if (direction == 'L'){
      lista[0] = 0;
      lista[1] = -1;
    }
    else if (direction == 'U'){
      lista[0] = -1;
      lista[1] = 0;
    }
    else if (direction == 'D'){
      lista[0] = 1;
      lista[1] = 0;
    }
    return lista;
  }


  public static int[] getPlayer(int[][] grid){
    int[] posicao = new int[2];
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid[i].length; j++){
        if(grid[i][j] == 5){
          posicao[0] = i;
          posicao[1] = j;
        }
      }
    }
    return posicao;
  }


  public static boolean isAvailable(int[][] grid, int[] position){
    boolean ocupado = false;
    if(grid[position[0]][position[1]] != 3 || grid[position[0]][position[1]] != 1){
      ocupado = true;
    }
    return ocupado;
  }

  public static boolean belongsTo(int[][] grid, int[][] goals, int[] position){
      boolean resultado = true;
      if (goals[0].length != position.length){
          resultado = false;
      }
      else {
          for (int i = 0; i < goals.length ; i++){
            for (int j = 0; j < position.length; j++){
                if (goals[i][j] != position[j]){
                    resultado = false;
                }
            }
          }
      }
      return resultado;
  }


  public static boolean ableToMove(int[][] grid, char direction){
    boolean resultado = false;
    
    int[] localDoPlayer = getPlayer(grid);
    
    int[] direcao = delta(direction);
    
    int localParaMoverHorizontal = localDoPlayer[1] + direcao[1];
    
    int localParaMoverVertical = localDoPlayer[0] + direcao[0];
    
    int direcaoIndicada = grid[localParaMoverVertical][localParaMoverHorizontal];
    
    int quadriculaDepoisDaCaixa = grid[localParaMoverVertical+direcao[0]][localParaMoverHorizontal+direcao[1]];
      
    if(direcaoIndicada == 1 || direcaoIndicada == 3){
        resultado = true;
      }else if (direcaoIndicada == 2 && (quadriculaDepoisDaCaixa == 1 || quadriculaDepoisDaCaixa == 3)){
        resultado = true;
      }
    return resultado;
}

    public static void move(int[][] grid, int[][] goals, char direction){
      if (ableToMove(grid, direction)){
          int[] localDoPlayer = getPlayer(grid);

          int[] direcao = delta(direction);
    
          int localParaMoverHorizontal = localDoPlayer[1] + direcao[1];
          int localParaMoverVertical = localDoPlayer[0] + direcao[0];
          
          if (grid[localParaMoverVertical][localParaMoverHorizontal] == 1){
            grid[localParaMoverVertical][localParaMoverHorizontal] = 5;
            if (belongsTo(grid, goals, localDoPlayer)){
              grid[localDoPlayer[0]][localDoPlayer[1]] = 3;
            }
            else{
              grid[localDoPlayer[0]][localDoPlayer[1]] = 1;
            }
            
          }
          else if (grid[localParaMoverVertical][localParaMoverHorizontal] == 2){
            grid[localParaMoverVertical][localParaMoverHorizontal] = 5;
            if (belongsTo(grid, goals, localDoPlayer)){
              grid[localDoPlayer[0]][localDoPlayer[1]] = 3;
            }
            else{
              grid[localDoPlayer[0]][localDoPlayer[1]] = 1;
              grid[localParaMoverVertical + direcao[0]][localParaMoverHorizontal + direcao[1]] = 2;
            }
            
          }
          else if (grid[localParaMoverVertical][localParaMoverHorizontal] == 3){
            grid[localParaMoverVertical][localParaMoverHorizontal] = 5;
            if (belongsTo(grid, goals, localDoPlayer)){
              grid[localDoPlayer[0]][localDoPlayer[1]] = 3;
            }
            else{
              grid[localDoPlayer[0]][localDoPlayer[1]] = 1;
            }

          }
      }
      print(grid, goals);
    }



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
              System.out.print("  B");
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