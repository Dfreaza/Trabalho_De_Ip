/**
*author fc-58640
*author fc-56969
*
*/
import java.util.Scanner;

public class Sokoban{
  public static void main(String[] args){
    int d = 8;
    verificarGame(43521141,d);
    verificarGame(435211412,d);
    verificarGame(4352114,d);
    verificarGame(43527141,d);
    verificarGame(43525141,d);
    verificarGame(43221141,d);
    verificarGame(43121145,d);
    verificarGame(43121541,d);
    verificarGame(43121522,d);
    verificarGame(43121152,d);

    int[][] matriz = new int[3][3];
    int[] lista = null;
    boolean resultado = positionInGrid(matriz, lista);
    System.out.println(resultado);

    Scanner sc = new Scanner(System.in);
    char r = readOption(sc);
    System.out.println(r);

  }

  /**
  *Calcula a quantidade de digitos existentes em num
  *
  *@param num (numero)
  *@requires {@code num > 0}
  *@ensures {@code /result >= 1}
  *@return retorna quantidadeNumero com a soma de quantas vezes num foi divido por 10
  */
    public static int digits(int num){
      int quantidadeNumero = 0;

      while(num > 0){
        num/=10;
        quantidadeNumero++;
      }
    return quantidadeNumero;
  }

  /**
  *Calcula a quantidade de vezes que um numero d aparece numa sequencia num
  *
  *@param num e uma sequencia de numeros
  *@param d e um numero
  *@requires {@code num > 0 && 9 >= d > 0}
  *ensures {@code /result >= 0}
  *@return retorna a quantidade de vezes que ocorre a incidencia do número na sequencia
  */
    public static int occurrencesOf(int num, int d){
      int incidencia = 0;

      while(num > 1){
        if(num%10 == d){
          incidencia++;
        }
        num/=10;
      }
    return incidencia;
  }

  /**
  *Verifica se num é positivo e possui apenas digitos entre 1 a 5
  *
  *@param num (sequencia de numeros)
  *@ensures {@code /result == true || /result == false}
  *@return retorna o resultado com o valor correto da variavel booleana
  */
    public static boolean isValid(int num){
      boolean resultado = false;

      int ocorre5 = occurrencesOf(num,5);
      int ocorre6 = occurrencesOf(num,6);
      int ocorre7 = occurrencesOf(num,7);
      int ocorre8 = occurrencesOf(num,8);
      int ocorre9 = occurrencesOf(num,9);

      if (num > 0 && ocorre5 <= 1 && ocorre6 == 0 && ocorre7 == 0 && ocorre8 == 0 && ocorre9 == 0){
        resultado = true;
      }else{
        resultado = false;
      }
    return resultado;
  }

  /**
  *Calcula o valor de 10 elevado a k
  *
  *@param k valor
  *@param expoente o expoente
  *@requires {@code k == 10}
  *@ensures {@code expoente >= 1}
  *@return k a ser multiplicado por 10
  */
    public static int potencia(int k, int expoente){
      for (int i = 1; i < expoente; i++){
        k*=10;
      }
    return k;
  }

  /**
  *Devolve o numero inteiro que representa a sequência a direita de d
  *
  *@param num e uma sequencia de numeros dada
  *@param d é um numero com um digito
  *@requires {@code 9 >= d > 0 && occurrencesOf(num,d)==1 && isValid(num)}
  *@ensures {@code /result >= 0}
  *@return retorna na variavel sequenciaDireita a sequencia de numeros a direita de d.
  */
    public static int rightSubsequence(int num, int d){
      int numero = num%10;
      int num2 = num;
      int digitosDireitaD = 0;
      int sequenciaDireita;

      while (numero != d && numero>0){
        if (num2 % 10 != d){
          digitosDireitaD++;
        }
      num2/=10;
      numero = (num2)%10;
    }
        if (digitosDireitaD == 0){
          sequenciaDireita = 0;
        }
        else{
      int power = potencia(10,digitosDireitaD);
      sequenciaDireita = num % power;
    }
    return sequenciaDireita;
  }


  /**
  *Verifica se o jogador esta nas condiçoes de se mover para a direita
  *
  *@param num e uma sequencia de numeros dada
  *@requires {@code isValid(num) && occurrencesOf(num,5)==1}
  *@ensures {@code /result == true || /result == false}
  *@return retorna o resultado com o valor correto da variavel booleana
  */
  public static boolean ableToMoveRight(int num){
    boolean moverDireita = false;

    int sequenciaDireita = rightSubsequence(num,5);
    int quantidadeNumero = digits(sequenciaDireita);
    int power = potencia(10, quantidadeNumero-2);
    int primeirosDoisNumADireita = sequenciaDireita/power;
    int primeiroDireita = primeirosDoisNumADireita/10;
    int segundoDireita = primeirosDoisNumADireita % 10;

    if ( primeiroDireita % 2 != 0 || (sequenciaDireita > 10 && (primeiroDireita*segundoDireita)%4 != 0)){
      moverDireita = true;
    }
    return moverDireita;
  }


  /**
  *
  *@param num e uma sequencia de numeros dada
  *
  *@param numDigits numero de digitos de num
  *@requires {@code num > 0 && 9 >= numDigits > 0 && isValid(num)}
  *@ensures {@code /result == true || /result == false}
  *@return retorna o resultado com o valor correto da variavel booleana
  */
  public static boolean isValidForGrid(int num, int numDigits){
    boolean numValido = false;

    if (isValid(num) && numDigits == digits(num)){
      numValido = true;
    }
    return numValido;
  }


  /**
  *Verifica as condiçoes e faz print com a mensagem
  *
  *@param num e uma sequencia de numeros dada
  *@param numDigits numero de digitos de num
  *@ensures faz print com uma mensagem
  *@return none
  */
  public static void verificarGame(int num, int numDigits){
    int ocorre5 = occurrencesOf(num,5);

      if (isValidForGrid(num,numDigits) && ableToMoveRight(num) && ocorre5==1){
        System.out.println("Válido, 5 ocorre, é movível para a direita");
      }
      else if (isValidForGrid(num,numDigits) && digits(num) == numDigits && ocorre5==1){
        System.out.println("Válido, 5 ocorre, não é movível para a direita");
      }
      else if (isValidForGrid(num,numDigits) && ocorre5==0){
        System.out.println("Válido, 5 não ocorre");
      }
      else
        System.out.println("Inválido");
      }

      //
      //
      //
      //
      // 
      // Parte 2 do projeto !!!!!!!!!!!
      //
      //
      //
      //
      //
      //

  public static boolean isValidGrid(int[][] grid){
    boolean resultado = true; 
    if (grid == null){
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
      lista[0] = -1;
      lista[1] = 0;
    }
    else if (direction == 'D'){
      lista[0] = 1;
      lista[1] = 0;
    }
    return lista;
  }












}
