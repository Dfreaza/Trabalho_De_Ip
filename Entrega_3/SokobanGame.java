public class SokobanGame {
    
    private SokobanMap map;
    private String ultimoMov;
    private int level;
    private int numMoves;
    private int[] playerPos;
    private int[][] boxes;

    public SokobanGame(){
        level = 1;
        playerPos = SokobanMapGenerator.getPlayer(level);
        boxes = SokobanMapGenerator.getBoxes(level);
        map = new SokobanMap(SokobanMapGenerator.getNrRows(level),
                              SokobanMapGenerator.getNrColumns(level), 
                              SokobanMapGenerator.getOccupiableMap(level), 
                              SokobanMapGenerator.getGoals(level), 
                              SokobanMapGenerator.getBoxes(level), 
                              SokobanMapGenerator.getPlayer(level));
    }

    public int getRows(){
       
        return map.getRows();
    }

    public int getColumns(){
        
        return map.getColumns();
    }

    public int[] getPlayerPosition(){
        
        return playerPos;
    }

    public Direction getDirection(){
        Direction resultado = null;
        if (ultimoMov == "DOWN" || ultimoMov == null){
            resultado = Direction.DOWN;
        }
        else if(ultimoMov == "UP"){
            resultado = Direction.UP;
        }
        else if(ultimoMov == "LEFT"){
            resultado = Direction.LEFT;
        }
        else if(ultimoMov == "RIGHt"){
            resultado = Direction.RIGHT;
        }
        return resultado;
    }

    public int getLevel(){
        
        return level; 
    }

    public int getNrMoves(){
        
        return numMoves;
    }

    public int[][] getPositionBoxes(){
        
        return boxes;
    }

    public int[][] getPositionGoals(){
        
        return map.getInitialPositionGoals();
    }
    
    public boolean isOccupiable(int i, int j){
       
        return map.isOccupiable(i, j);
    }

    public void move(Direction dir){                        // falta este !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        numMoves++;
        ultimoMov = dir.name();
    }

    public boolean levelCompleted(){
        boolean resultado = true;
        boolean condicao1 = false;
        int[][] goalsArray = getPositionGoals();
        int[][] boxesArray = getPositionBoxes();

        for(int i = 0; i < goalsArray.length; i++){
            int [] box = boxesArray[i];
            for(int j = 0; j < goalsArray[i].length; j++){
                if(box[0] == goalsArray[i][0] && box[1] == goalsArray[i][1]){
                    condicao1 = true;
                }
            }
            resultado &= condicao1;
            condicao1 = false;
        }

        return resultado;
        
    }

    public boolean isTerminated(){
       boolean jogoAcabou = false;
       if(levelCompleted() && SokobanMapGenerator.numberOfLevels() == level){
           jogoAcabou = true;
       }
       return jogoAcabou;
    }

    public void loadNextLevel(){
            level += 1;
            playerPos = SokobanMapGenerator.getPlayer(level);
            boxes = SokobanMapGenerator.getBoxes(level);
            map = new SokobanMap(SokobanMapGenerator.getNrRows(level),
                                 SokobanMapGenerator.getNrColumns(level), 
                                 SokobanMapGenerator.getOccupiableMap(level), 
                                 SokobanMapGenerator.getGoals(level), 
                                 SokobanMapGenerator.getBoxes(level), 
                                 SokobanMapGenerator.getPlayer(level));
        
    }

    public void restartLevel(){
        this.map = new SokobanMap(SokobanMapGenerator.getNrRows(level),
                             SokobanMapGenerator.getNrColumns(level), 
                             SokobanMapGenerator.getOccupiableMap(level), 
                             SokobanMapGenerator.getGoals(level), 
                             SokobanMapGenerator.getBoxes(level), 
                             SokobanMapGenerator.getPlayer(level));
    }

    public String toString(){
        int linhas = map.getRows();
        int colunas = map.getColumns();
        boolean[][] occupiableMap = SokobanMapGenerator.getOccupiableMap(level); 
        int[][] goals = SokobanMapGenerator.getGoals(level);
        

        String mapa = "+";
        for(int x = 0; x < linhas*2; x++){
            mapa += "-";
        }
        mapa += "+";
        mapa += "\nLEVEL: " + this.level;
        mapa += "\n+------ MAP ------+";

        for(int i = 0; i < linhas; i++){
            mapa += "\n|";
            for(int j = 0; j < colunas; j++){
                if (occupiableMap[i][j] == false) {
                mapa += "-";
                }
                else if (playerPos[0] == i && playerPos[1] == j) {
                mapa += "P";
                }
                else{
                for (int x = 0; x < goals.length; x++){
                    if (boxes[x][0] == i && boxes[x][1] == j && goals[x][0] == i && goals[x][1] == j) {
                    mapa += "*";
                    }
                    else if (boxes[x][0] == i && boxes[x][1] == j){
                        mapa += "B";
                    }
                    else if (goals[x][0] == i && goals[x][1] == j){
                    mapa += "G";
                    }
                    else{
                    mapa += " ";
                    }
                }
            }
        }
      mapa += "|";
    }
    mapa += "\n";

    mapa = "+-----------------+";
    mapa += "MOVES: " + this.numMoves;
    mapa = "+-----------------+";

    return mapa;
   }

}

