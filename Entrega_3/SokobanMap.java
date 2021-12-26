public class SokobanMap {

    private final int rows;
    private final int columns;
    private final int boxes;
    private int[] initialPlayerPosition;
    private int[][] initialPositionBoxes;
    private int[][] initialPositionGoals;
    private boolean[][] occupiableMap;


    public static boolean isValidMap(int rows, int columns, boolean[][] occupiableMap,
                                    int[][] goals, int[][] boxes, int[] playerPos){
        int goalsEquals = 0;
        int boxesEquals = 0;
        boolean goalsIsValid = false;
        boolean boxesIsValid = false;
        boolean isValid = false;

        if(rows > 2 && columns > 2){
            if(occupiableMap != null && occupiableMap.length == rows &&
              occupiableMap[0].length == columns){
                if(goals != null && goals.length > 0){
                    for(int i = 0; i < goals.length; i++){
                        if(goals[i] != null && (goals[i].length == 2) && (0 <= goals[i][0] &&
                           goals[i][0] < rows) && (0 <= goals[i][1] && goals[i][i] < columns)){
                                for(int j = 0; j < goals.length; j++){
                                    if(goals[i] == goals[j]){
                                        goalsEquals++;
                                    }
                                }
                            }
                        }
                    }
                }
            }


        if(goalsEquals == 0){
           goalsIsValid = true;
        }

        if(boxes != null && boxes.length > 0){
            for(int i = 1; i < boxes.length; i++){
                if(boxes[i] != null && (boxes[i].length == 2) && (0 <= boxes[i][0] && boxes[i][0] < rows) && (0 <= boxes[i][1] && boxes[i][i] < columns)){
                    for(int x = 0; x < boxes.length; x++){
                        for(int y = 0; y < boxes.length; y++){
                            if(boxes[x] == boxes[y]){
                                boxesEquals++;
                            }
                        }
                    }
                }
            }
        }

        if(boxesEquals == 0){
            boxesIsValid = true;
        }
        /*
        if(goals.length == boxes.length){
            for(int i = 0; i < goals.length; i++){
                if(goals[i] == occupiableMap && boxes[i] == occupiableMap){
                    for(int x = 0; x < playerPos.length; x++){
                        if(playerPos[x] == occupiableMap && playerPos[x] != boxes){
                            if(goalsIsValid && boxesIsValid){
                                isValid = true;
                            }
                        }
                    }
                }
            }
        }
        */
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


    int[][] grid = new int[rows][columns];
    for (int i = 0;i < rows; i++) {
      for (int j = 0;j < columns; j++) {
        if (occupiableMap[i][j] == false) {
          grid[i][j] = 4;
        }
        else if (playerPos[0] == i && playerPos[1] == j) {
          grid[i][j] = 5;
        }
        else{
          for (int x = 0; x < goals.length; x++){
            if (boxes[x][0] == i && boxes[x][1] == j) {
              grid[i][j] = 2;
            }
            else if (goals[x][0] == i && goals[x][1] == j){
              grid[i][j] = 3;
            }
            else{
              grid[i][j] = 1;
            }
          }
        }
      }
    }
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
    return this.initialPlayerPosition;
  }

  public int[][] getInitialPositionBoxes(){
    return this.initialPositionBoxes;
  }

  public int[][] getInitialPositionGoals(){
    return this.initialPositionGoals;
  }

  public boolean isOccupiable(int i, int j){
    boolean resultado = occupiableMap[i][j];
    return resultado;
  }


}
