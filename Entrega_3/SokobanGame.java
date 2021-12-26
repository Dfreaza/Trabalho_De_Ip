public class SokobanGame {
    
    private SokobanMap map;
    private String ultimoMov;
    private int level;
    private int numMoves;

    public SokobanGame(){
        level = 1;
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
        
        return map.getInitialPlayerPosition();
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
        
        return map.getInitialPositionBoxes();
    }

    public int[][] getPositionGoals(){
        
        return map.getInitialPositionGoals();
    }
    
    public boolean isOccupiable(int i, int j){
       
        return map.isOccupiable(i, j);
    }

    public void move(Direction dir){
        
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
       
        return false;
    }

    public void loadNextLevel(){
        if (!isTerminated() && levelCompleted()){
            level += 1;
            map = new SokobanMap(SokobanMapGenerator.getNrRows(level),
                                 SokobanMapGenerator.getNrColumns(level), 
                                 SokobanMapGenerator.getOccupiableMap(level), 
                                 SokobanMapGenerator.getGoals(level), 
                                 SokobanMapGenerator.getBoxes(level), 
                                 SokobanMapGenerator.getPlayer(level));
        }
    }

    public void restartLevel(){

    }

    public String toString(){
        
        return null;
    }

}
