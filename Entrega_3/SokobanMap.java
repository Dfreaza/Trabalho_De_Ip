public class SokobanMap {

    private final int rows;
    private final int columns;
    private final int boxes;
    private final int[] initialPlayerPosition;
    private final int[][] initialPositionBoxes;
    private final int[][] initialPositionGoals;
    private final boolean[][] occupiableMap;


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

  public int getRows(){
    return this.rows;
  }

  public int getColumns(){
    return this.columns;
  }

  public int getNrBoxes(){
    return this.boxes;
  }
 
  public int[] getInitialPlayerPosition(){
    int[] player = new int[this.initialPlayerPosition.length];
    for (int i = 0; i < player.length; i++) {
      player[i] = this.initialPlayerPosition[i];
    }
    return player;
  }

  public int[][] getInitialPositionBoxes(){
    int[][] boxes = new int[this.initialPositionBoxes.length][this.initialPositionBoxes[0].length];
    for (int i = 0; i < boxes.length; i++) {
      for (int j = 0; j < boxes[i].length; j++){
        boxes[i][j] = initialPositionBoxes[i][j];
      }
    }
    return boxes;
  }

  public int[][] getInitialPositionGoals(){
    int[][] goals = new int[this.initialPositionGoals.length][this.initialPositionGoals[0].length];
    for (int i = 0; i < goals.length; i++) {
      for (int j = 0; j < goals[i].length; j++){
        goals[i][j] = initialPositionGoals[i][j];
      }
    }
    return goals;
  }
  

  public boolean isOccupiable(int i, int j){
    boolean resultado = occupiableMap[i][j];
    return resultado;
  }


}
