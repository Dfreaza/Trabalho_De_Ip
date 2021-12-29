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
        int goalsEquals = 0;
        int boxesEquals = 0;
        boolean eMatriz = true;
        boolean goalsIsValid = false;
        boolean boxesIsValid = false;
        boolean isValid = true;

        if(rows > 2 && columns > 2){
            if(occupiableMap != null){
                for(int x = 0; x < occupiableMap.length; x++){
                    if (occupiableMap.length != occupiableMap[x].length){
                        eMatriz = false;
                    }
                }
                if(goals != null && goals.length > 0 && eMatriz){
                    for(int i = 0; i < goals.length; i++){
                        if(goals[i] != null && (goals[i].length == 2) && (0 <= goals[i][0] &&
                           goals[i][0] < rows) && (0 <= goals[i][1] && goals[i][1] < columns)){
                                for(int j = 0; j < goals.length; j++){
                                    if(goals[i][0] == goals[j][0] && goals[i][1] == goals[j][1] && i != j){
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
            for(int i = 0; i < boxes.length; i++){
                if(boxes[i] != null && (boxes[i].length == 2) 
                    && (0 <= boxes[i][0] && boxes[i][0] < rows) 
                    && (0 <= boxes[i][1] && boxes[i][1] < columns)){
                    for(int x = 0; x < boxes.length; x++){
                        for(int y = 0; y < boxes.length; y++){
                            if(boxes[x][0] == boxes[y][0] && boxes[x][1] == boxes[y][1] && x != y){
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
        
        if(goals.length == boxes.length && goalsIsValid && boxesIsValid){ 
            for(int i = 0; i < goals.length; i++){
                int linhaGoals = goals[i][0];
                int colunaGoals = goals[i][1];
                int linhaBoxes = boxes[i][0];
                int colunaBoxes = boxes[i][1]; 
                int linhaPlayer = playerPos[0];
                int colunaPlayer = playerPos[1];
                if(!occupiableMap[linhaGoals][colunaGoals] || !occupiableMap[linhaBoxes][colunaBoxes] || 
                   !occupiableMap[linhaPlayer][colunaPlayer] || 
                   (linhaPlayer == linhaBoxes && colunaPlayer == colunaBoxes)){
                            isValid = false;   
                }

            }
        }
        else{
            isValid = false;
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
