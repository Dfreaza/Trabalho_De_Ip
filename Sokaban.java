/**
*author fc-58640
*author fc-58969
*
*/

public class ProjetoIP{
  public static void main(String[] args){

    int num = 43221141;
    int d = 8;

    verificarGame(num,d);

  }

/**
*Calcular a quantidade de digitos existente em num
*@param num (numero)
*@requires {@code num > 0}
*@ensures {@code /result >= 1}
*@return a quantidadeNumero com a soma de quantas vezes num foi divido por 10
*/

    static int digits(int num){
      int quantidadeNumero = 0;

      while(num > 0){
        num/=10;
        quantidadeNumero++;
      }
    return quantidadeNumero;
  }

/**
*Calcular a quantidade de vezes que um número d aparece numa sequencia numa
*@param num (uma sequencia de numeros)
*@param d (um numero apenas)
*@requires {@code num > 0 && 9 >= d > 0}
*ensures {@code /result >= 0}
*@return a quantidade de vezes que ocorre a incidencia do número na sequencia
*/

    static int occurrencesOf(int num, int d){
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
*Verificar se num é positivo e possui apenas digitos entre 1 a 5,
*e retonar true caso se verifique e false caso contrário
*@param num (sequencia de numeros)
*@requires {@code }                                                     // falta esta linha
*@ensures {@code /result == true || /result == false}
*@return ao resultado com o valor correto da variavel booleana
*/

    static boolean isValid(int num){
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
*Calcular o valor de 10 elevado a k
*@param k valor
*@param expoente o expoente                               rever estas linhas !!!!!!!
*@requires {@code k == 10}
*@ensures {@code expoente >= 1}
*@return k a ser multiplicado por 10
*/

    static int potencia(int k, int expoente){
      for (int i = 1; i < expoente; i++){
        k*=10;
      }
    return k;
  }

/**
*Devolve o número inteiro que representa a sequência à direita de default:
*@param num (sequência de números)
*@param d (um número de um digito)
*@requires {@code 9 >= d > 0 && }                                         rever a linha falta coisas
*@ensures {@code /result >= 0}
*@return retorna à sequenciaDireita a sequência de números à direita de d.
*/

    static int rightSubsequence(int num, int d){
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
*@param num (sequencia de numeros)
*@requires {@code }                                                     // falta esta linha
*@ensures {@code /result == true || /result == false}
*@return retorna o resultado com o valor correto da variavel booleana
*/

  static boolean ableToMoveRight(int num){
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
*@param num (sequencia de numeros)
*@param numDigits numero de digitos de num
*@requires {@code num > 0 && 9 >= numDigits > 0}                                               // falta esta linha
*@ensures {@code /result == true || /result == false}
*@return retorna o resultado com o valor correto da variavel booleana
*/

  static boolean isValidForGrid(int num, int numDigits){
    boolean numValido = false;

    if (isValid(num) && numDigits == digits(num)){
      numValido = true;
    }
    return numValido;
  }


/**
*Verifica as condiçoes e faz print com a mensagem
*@param num (sequencia de numeros)
*@param numDigits numero de digitos de num
*@requires {@code num > 0 && 9 >= numDigits > 0}                                            // falta esta linha
*@ensures faz print com uma mensagem
*@return none
*/

  static void verificarGame(int num, int numDigits){
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
}
