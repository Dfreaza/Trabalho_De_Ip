import java.util.Scanner;

public class Sokoban {
    public static void main(String[] args) {
        int[][] matriz = new int[3][3];
        int[] lista = null;
        boolean resultado = positionInGrid(matriz, lista);
        System.out.println(resultado);
    
        Scanner sc = new Scanner(System.in);
        char r = readOption(sc);
        System.out.println(r);

        int[][] matriz2 = {{4, 4 ,4, 1, 1, 1, 4, 1},{4, 3, 5, 2, 1, 1, 4, 1},{4, 4 ,4, 1, 2, 3, 4, 1}};
        print(matriz2, matriz);
        
    }


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
    if (direction == 'R' || direction == 'L' || direction == 'U' || direction == 'D'){
      resultado = true;
    }
    
    return resultado;
  }

  public static char readOption(Scanner scan){

    System.out.println("Welcome to Sokoban!");
    System.out.println("Consider that Sokoban symbols are represented by the following digits:");
    System.out.println(" "+"1 - blank  "+"2 - box  "+"3 - goal  "+"4 - wall  "+"5 - player  ");
    System.out.println("The symbolic representation on the right may help you while playing.");

    char readOption;
    boolean valido = false;
    do {
        readOption = scan.next().charAt(0);
        valido = isValidDirection(readOption);
        if(!valido && readOption != 'E'){
          System.out.println("not valid");
        }
      }while (!valido && readOption != 'E');
      
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
      lista[0] = 1;
      lista[1] = 0;
    }
    else if (direction == 'D'){
      lista[0] = -1;
      lista[1] = 0;
    }
    return lista;
  }


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


  public static boolean isAvailable(int[][] grid, int[] position){
    boolean ocupado = false;
    if(grid[position[0]][position[1]] != 3){
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
      /*int [] localDoPlayer = getPlayer(grid);
      int[] direcao = delta(direction);
      int localParaMoverHorizontal = localDoPlayer[0] + direcao[0]; 
      int localParaMoverVertical = localDoPlayer[1] + direcao[1];
      int direcaoIndicada = grid[localParaMoverHorizontal][localParaMoverVertical];*/
      
      /*a) a posição adjacente à direita (resp., à esquerda, acima, abaixo) existe e não está ocupada, ou seja,
      contém um número ímpar ou 
      b) a posição adjacente à direita (resp., à esquerda, acima, abaixo) contém uma caixa (representado pelo dígito 2) mas a posição adjacente à da caixa, na mesma direção, existe e não está ocupada */
      return resultado;
}

    public static void move(int[][] grid, int[][] goals, char direction){

    }



        /**
     * Imprime os elementos de um vetor de inteiros separados por espacos De
     * interesse apenas pedagogico. Para imprimir vetor qq devem usar
     * System.out.println(Arrays.toString(v))
     *
     * @param v O vetor a imprimir
     * @requires {@code v != null}
     */
    public static void printArray(int[] v) {
        for (int i = 0; i < v.length; i++)
            System.out.print(v[i] + " ");
        System.out.println();
    }


    public static void print(int[][] grid, int[][] goals){
        for (int i = 0; i < grid.length; i++){
            printArray(grid[i]);

            }
        }
    


}