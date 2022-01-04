/**
*
*@author Guilherme Wind - fc58640
*@author Diogo Freaza - fc56969
*
*/

public class SokobanMap {

  private final int rows;
  private final int columns;
  private final int boxes;
  private final int[] initialPlayerPosition;
  private final int[][] initialPositionBoxes;
  private final int[][] initialPositionGoals;
  private final boolean[][] occupiableMap;
  
  /**
   *Verifica se os parametros dados formam um mapa válido
   * 
   * @param rows numero de linhas
   * @param columns numero de colunas
   * @param occupiableMap matriz que define os lugares jogaveis
   * @param goals matriz com as posições dos obejtivos
   * @param boxes matriz com as posições das caixas
   * @param playerPos vetor com a posição do jogador
   * 
   * @ensures {@code /result == true || /result == false}
   * @return Retorna um boolean com o respetivo valor do mapa 
   */
  public static boolean isValidMap(int rows, int columns, boolean[][] occupiableMap,
                                      int[][] goals, int[][] boxes, int[] playerPos){
  
          boolean isValid = false;
          boolean condition = true;
          boolean goalsIsValid = false;
          boolean boxesIsValid = true;
          boolean positionIsValid = false;
  
          int playerPosX = playerPos[0];
          int playerPosY = playerPos[1];
  
          if(rows > 2 && columns > 2){
              if(occupiableMap != null && occupiableMap.length == rows){
                  if(goals.length == boxes.length && goals != null && goals.length > 0){
                      goalsIsValid = true;
                      for(int i = 0; i < occupiableMap.length; i++){
                          if(occupiableMap[i].length != columns){
                              condition = false;
                          }
                      }
                  }
              }
          }
  
          for(int i = 0; i < goals.length && goalsIsValid; i++){
              goalsIsValid &= goals[i] != null && goals[i].length == 2;
              goalsIsValid &= goals[i][0] >= 0 && goals[i][0] < rows;
              goalsIsValid &= goals[i][1] >= goals[i][1] && goals[i][1] < columns;
              goalsIsValid &= occupiableMap[goals[i][0]][goals[i][1]];
          }
  
          for(int i = 0; i < goals.length && boxesIsValid; i++){
              boxesIsValid &= boxes[i] != null && boxes[i].length == 2;
              boxesIsValid &= boxes[i][0] >= 0 && boxes[i][0] < rows;
              boxesIsValid &= boxes[i][1] >= boxes[i][1] && boxes[i][1] < columns;
              boxesIsValid &= occupiableMap[boxes[i][0]][boxes[i][1]];
          }
                  
          for(int i = 0; i < goals.length && goalsIsValid && boxesIsValid; i++){
              for(int j = 0; j < goals.length && goalsIsValid && boxesIsValid; j++){
                  if(i != j){
                      if(goals[i][0] == goals[j][0] && goals[i][1] == goals[j][1]){
                          goalsIsValid = false;
                      }
                      if(boxes[i][0] == boxes[j][0] && boxes[i][1] == boxes[j][1]){
                          boxesIsValid = false;
                      }
                  }
              }
          }
  
          if(playerPosX >= 0 && playerPosX < rows && playerPosY >= 0 && playerPosY < columns && occupiableMap[playerPosX][playerPosY]){
              positionIsValid = true;
          }
  
          if(positionIsValid && goalsIsValid && boxesIsValid && condition){
              isValid = true;
          }
          return isValid;
      }
  
  /**
   * Segundo os paramtros verificados no IsValidMap, constroi o respectivo mapa
   * 
   * @param rows numero de linhas
   * @param columns numero de colunas
   * @param occupiableMap matriz que define os lugares jogaveis
   * @param goals matriz com as posições dos obejtivos
   * @param boxes matriz com as posições das caixas
   * @param playerPos vetor com a posição do jogador
   * 
   * @ensures {@code isValidMap(rows,columns,occupiableMap,goals,boxes,playerPos)}
   */    
  public SokobanMap(int rows, int columns, boolean[][] occupiableMap, int[][] goals,
  int[][] boxes, int[] playerPos){
  this.rows = rows;
  this.columns = columns;
  this.boxes = boxes.length;
  this.initialPlayerPosition = playerPos;
  this.initialPositionBoxes = boxes;
  this.initialPositionGoals = goals;
  this.occupiableMap = occupiableMap;
  }
  
  /**
   * 
   * @return Retorna o número de linhas do mapa
   */
  public int getRows(){
  return this.rows;
  }
  
  /**
   * 
   * @return Retorna o número de colunas do mapa
   */
  public int getColumns(){
  return this.columns;
  }
  
  /**
   * 
   * @return Retorna o número de caixas exixtentes mo mapa
   */
  public int getNrBoxes(){
  return this.boxes;
  }
  
  
  /**
   * 
   * @return Retorna um vetor com a posição original do player
   */
  public int[] getInitialPlayerPosition(){
      int[] player = new int[this.initialPlayerPosition.length];
      for (int i = 0; i < player.length; i++) {
        player[i] = this.initialPlayerPosition[i];
      }
      return player;
    }
  
  
  /**
   * 
   * @return Retorna uma matriz com a posição (linha e coluna) de cada caixa
   */
  public int[][] getInitialPositionBoxes(){
      int[][] boxes = new int[this.initialPositionBoxes.length][this.initialPositionBoxes[0].length];
      for (int i = 0; i < boxes.length; i++) {
        for (int j = 0; j < boxes[i].length; j++){
          boxes[i][j] = initialPositionBoxes[i][j];
        }
      }
      return boxes;
  }
  
  
  
  /**
   * 
   * @return Retorna uma matriz com a posição (linha e coluna) de cada objetivo
   */
  
  public int[][] getInitialPositionGoals(){
      int[][] goals = new int[this.initialPositionGoals.length][this.initialPositionGoals[0].length];
      for (int i = 0; i < goals.length; i++) {
        for (int j = 0; j < goals[i].length; j++){
          goals[i][j] = initialPositionGoals[i][j];
        }
      }
      return goals;
  }
  
  /**
   * Verifica se a posição dada é ocupável (não tem parede) ou não
   * 
   * @param i numero da linha desejada
   * @param j numero da coluna desejada
   * 
   * @requires {@code 0≤i<getRows() &&  0≤j<getColumns()}
   * @ensures {@code /result == true || /result == false}
   * @return Retorna um boolean com o verdadeiro significado da posição dada
   */
  
  public boolean isOccupiable(int i, int j){
  boolean resultado = occupiableMap[i][j];
  return resultado;
  }
  }
