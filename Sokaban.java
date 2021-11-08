public class Sokaban{

    public static void main(String[] args) {
      int digitos = digits(456);
      System.out.println("Digitos " + digitos);

      int ocorrencia = occurrencesOf(456,2);
      System.out.println("ocorrencia " + ocorrencia);

      boolean eValido = isValid(456);
      System.out.println("E valido " + eValido);

      //int sequenciaDaDireita = rightSubsequence(43521141,5);
      //System.out.println("sequenciaDaDireita " + sequenciaDaDireita);

      boolean podeMoverParaDireita = ableToMoveRight(43521141);
      System.out.println("podeMoverParaDireita " + podeMoverParaDireita);


      isValidForGrid(43221141,8);
    }



    static int digits(int num){
      int numDigitos = 0;
      while (num > 1){
        numDigitos++;
        num /= 10;
      }
      return numDigitos;
    }


    static int occurrencesOf(int num, int d){
      int incidencia = 0;
      while(num > 1 ){
       if( num % 10 == d ){
         incidencia++;
       }
      num/=10;
     }
      return incidencia;

      }


    static boolean isValid(int num){
      boolean resultado;
      int ocorre5 = occurrencesOf(num,5);
      int ocorre6 = occurrencesOf(num,6);
      int ocorre7 =  occurrencesOf(num,7);
      int ocorre8 = occurrencesOf(num,8);
      int ocorre9 = occurrencesOf(num,9);

      if (num > 0 && ocorre5 == 1 && ocorre6 == 0 && ocorre7 == 0 && ocorre8 == 0 && ocorre9 == 0){
        resultado = true;
      }
      else
        resultado = false;

      return resultado;
    }

    static int potencia(int k, int expoente){
      for (int i = 1;i < expoente ; i++) {
        k  *= 10;
      }
      return k;
    }


    static int rightSubsequence(int num, int d){
      int numero = num % 10;
      int numDigitosDepoisDed = 0;
      while (numero != d){
        numDigitosDepoisDed++;
        numero /=10;
      }
      int power = potencia(10,numDigitosDepoisDed);
      int sequenciaDaDireita = num % power;

      return sequenciaDaDireita;
    }




    static boolean ableToMoveRight(int num){
      boolean podeMoverParaDireita = false;

      int sequenciaDaDireita = 3141;        // O correto é rightSubsequence(num) alterar quando for para entregar!!!

      int numDigitos = digits(sequenciaDaDireita);

      int power = potencia(10,numDigitos-2);     //faz a potencia de 10 elevado ao num de digitos - 2 (-2 porque a gente quer os dois primeiros digitos)

      int primeirosDoisNumADireita = sequenciaDaDireita/power;   //encontra os dois primeiros numeros depois do 5

      int primeironumADireita = primeirosDoisNumADireita/10;

      int segundoNumADireita = primeirosDoisNumADireita % 10;

      System.out.println(primeironumADireita);

      if (primeironumADireita % 2 != 0 || (sequenciaDaDireita > 10 &&
         ((primeironumADireita)*(segundoNumADireita))%4 != 0)){
              podeMoverParaDireita = true;
      }
      return podeMoverParaDireita;
    }



    static void isValidForGrid(int num, int numDigits){
      boolean valido = isValid(num);
      int digitos = digits(num);
      int ocorre5 = occurrencesOf(num,5);
      boolean existe5EmNum;
      if (ocorre5 == 1){
        existe5EmNum = true;
      }
      else
        existe5EmNum = false;


      if (valido && digitos == numDigits && existe5EmNum) {
        System.out.println("(válido, 5 ocorre, é movível para a direita)");
      }
      else if (valido && digitos == numDigits){
        System.out.println("(válido, 5 ocorre, não é movível para a direita)");
      }
      else if (valido){
        System.out.println("(válido, 5 não ocorre)");
      }
      else
        System.out.println("(inválido)");
    }





}
