import javax.xml.bind.JAXBElement.GlobalScope;
import java.util.Arrays;

public class SokobanGame {
    
    private SokobanMap map;
    private String ultimoMov;
    private int level;
    private int numMoves;
    private int numRows;
    private int numColumns;
    private int[] playerPos;
    private boolean[][] occupiable;
    private int[][] goals;
    private int[][] boxes;



    public SokobanGame(){
        level = 1;
        numMoves = 0;
        numRows = SokobanMapGenerator.getNrRows(level);
        numColumns = SokobanMapGenerator.getNrColumns(level);
        playerPos = SokobanMapGenerator.getPlayer(level);
        boxes = SokobanMapGenerator.getBoxes(level);
        goals = SokobanMapGenerator.getGoals(level);
        occupiable = SokobanMapGenerator.getOccupiableMap(level);
        map = new SokobanMap(numRows,numColumns, occupiable, goals, boxes,playerPos);
    }

    public int getRows(){
       
        return numRows;
    }

    public int getColumns(){
        
        return numColumns;
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
        else if(ultimoMov == "RIGHT"){
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
        
        return goals;
    }
    
    public boolean isOccupiable(int i, int j){
       
        return occupiable[i][j];
    }

    public void move(Direction dir){                        // falta este !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ultimoMov = dir.name(); 
        int[] direcao = new int[2];
        if (dir == Direction.RIGHT){
            direcao[0] = 0;
            direcao[1] = 1;
        }
        else if (dir == Direction.LEFT){
            direcao[0] = 0;
            direcao[1] = -1;
        }
        else if (dir == Direction.UP){
            direcao[0] = -1;
            direcao[1] = 0;
        }
        else if (dir == Direction.DOWN){
            direcao[0] = 1;
            direcao[1] = 0;
        }

        boolean podeMover = true;

        int localParaMoverHorizontal = playerPos[1] + direcao[1];

        int localParaMoverVertical = playerPos[0] + direcao[0];

        int[] vetor = {localParaMoverVertical, localParaMoverHorizontal};

        boolean estaNoMapa = false;

        boolean estaNoMapacaixa =false;

        boolean direcaoPosIndicada = false;

        boolean estaNoMapaLinha = 0 <= vetor[0] && vetor[0]< getRows();
        
        boolean estaNoMapaColuna = 0 <= vetor[1] && vetor[1] < getColumns();

          if (estaNoMapaLinha && estaNoMapaColuna){
            estaNoMapa = true;
          }

        if(estaNoMapa){
        boolean direcaoIndicada = isOccupiable(vetor[0], vetor[1]);
            if(direcaoIndicada){   
                int[] posicaoPosCaixa = {vetor[0]+direcao[0], vetor[1]+direcao[1]};
                boolean estaNaLinhaCaixa = true;
                boolean estaNaColunaCaixa = true;
                for(int i = 0; i < boxes.length; i++){
                    if (vetor[0] == boxes[i][0] && vetor[1] == boxes[i][1]) {
                        estaNaLinhaCaixa &= 0 <= posicaoPosCaixa[0] && posicaoPosCaixa[0]< getRows();
                        

                        estaNaColunaCaixa &= 0 <= posicaoPosCaixa[1] && posicaoPosCaixa[1] < getColumns();
                        

                        if (estaNaLinhaCaixa && estaNaColunaCaixa){
                             estaNoMapacaixa = true;
                          }
                        if (estaNoMapacaixa) {
                            direcaoPosIndicada = isOccupiable(posicaoPosCaixa[0], posicaoPosCaixa[1]);
                        }
                        else{
                            podeMover = false;
                        }
                    }
                }
                if (direcaoPosIndicada) {                               
                    for(int j = 0; j < boxes.length; j++){
                        if (posicaoPosCaixa[0] == boxes[j][0] && posicaoPosCaixa[1] == boxes[j][1]) {
                            podeMover = false;
                        }
                    }
                  }
                  else if (!direcaoPosIndicada && estaNoMapacaixa){
                      podeMover = false;
                  } 
            }
            else{
                podeMover = false;
            } 
           }
           else{
            podeMover = false;
        } 
        
        

        if (podeMover && direcaoPosIndicada) {
            for (int x = 0; x < boxes.length; x++) {
                if (vetor[0] == boxes[x][0] && vetor[1] == boxes[x][1]){
                    boxes[x][0] += direcao[0];                     
                    boxes[x][1] += direcao[1]; 
                    playerPos[0] += direcao[0];                               
                    playerPos[1] += direcao[1];
                    numMoves++;
                }
            }
        }
        else if (podeMover){
            playerPos[0] += direcao[0];                                
            playerPos[1] += direcao[1];
            numMoves++;                                        
        }


    }

    public boolean levelCompleted(){
        boolean resultado = false;
        int numBoxes = 0;
        int[][] goalsArray = getPositionGoals();
        int[][] boxesArray = getPositionBoxes();

        for(int i = 0; i < goalsArray.length; i++){
            for(int j = 0; j < goalsArray.length; j++){
                if(boxesArray[j][0] == goalsArray[i][0] && boxesArray[j][1] == goalsArray[i][1]){
                    numBoxes++;
                }
            }
        }
        if(numBoxes == goalsArray.length){
            resultado =true;
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
            ultimoMov = null;
            numMoves = 0;
            numRows = SokobanMapGenerator.getNrRows(level);
            numColumns = SokobanMapGenerator.getNrColumns(level);
            playerPos = SokobanMapGenerator.getPlayer(level);
            boxes = SokobanMapGenerator.getBoxes(level);
            goals = SokobanMapGenerator.getGoals(level);
            occupiable = SokobanMapGenerator.getOccupiableMap(level);
            map = new SokobanMap(numRows,numColumns, occupiable, goals, boxes,playerPos);
    }
        
    

    public void restartLevel(){
        numMoves = 0;
        ultimoMov = null;
        numRows = SokobanMapGenerator.getNrRows(level);
        numColumns = SokobanMapGenerator.getNrColumns(level);
        playerPos = SokobanMapGenerator.getPlayer(level);
        boxes = SokobanMapGenerator.getBoxes(level);
        goals = SokobanMapGenerator.getGoals(level);
        occupiable = SokobanMapGenerator.getOccupiableMap(level);
        map = new SokobanMap(numRows,numColumns, occupiable, goals, boxes,playerPos);
    }

    public String toString(){
        int linhas = map.getRows();
        int colunas = map.getColumns();
        boolean[][] occupiableMap = SokobanMapGenerator.getOccupiableMap(level); 
        int[][] goals = SokobanMapGenerator.getGoals(level);
        boolean encontrou = false;
        boolean encontrouBoxGrid = false;
        

        String mapa = "+";
        for(int x = 0; x <= linhas*2; x++){
            mapa += "-";
        }
        mapa += "+";
        mapa += "\nLEVEL: " + this.level;
        mapa += "\n+";
        for(int x = 1; x < linhas-1; x++){
            mapa += "-";
        }
        mapa += " MAP ";
        for(int x = 1; x < linhas-1; x++){
            mapa += "-";
        }
        mapa += "+";

        for (int i = 0; i < linhas; i++) {
            mapa += "\n| ";
            for (int j = 0; j < colunas; j++) {
                encontrou = false;
                encontrouBoxGrid = false;
                if (occupiableMap[i][j] == false) {
                    mapa += "- ";
                }
                else if (this.playerPos[0] == i && this.playerPos[1] == j){
                    mapa += "P ";
                }
                else{
                    for (int x = 0; x < goals.length; x++) {
                        if (goals[x][0] == i && goals[x][1] == j) {
                            for (int k = 0; k < goals.length; k++) {
                                if (boxes[k][0] == i && boxes[k][1] == j) {
                                    mapa += "* ";
                                    encontrou = true;
                                    encontrouBoxGrid = true;
                                }
                            }
                            if(!encontrouBoxGrid) {
                                mapa += "G ";
                                encontrou = true;
                            }
                            
                        }
                        else if(boxes[x][0] == i && boxes[x][1] == j && !encontrouBoxGrid){
                            mapa += "B ";
                            encontrou = true;
                        }
                    }
                    if (!encontrou){
                        mapa += "  ";
                    }
                }
            }
            mapa += "|";
        }
        mapa += "\n";


    mapa += "+";
    for(int x = 0; x <= linhas*2; x++){
        mapa += "-";
    }
    mapa += "+";
    mapa += "\nMOVES: " + this.numMoves;
    mapa += "\n+";
    for(int x = 0; x <= linhas*2; x++){
        mapa += "-";
    }
    mapa += "+";

    return mapa;
   }

   public static void main(String[] args) {
    
    SokobanGame game = new SokobanGame();

    game.loadNextLevel();

    game.loadNextLevel();

    game.loadNextLevel();

    System.out.println(game.toString());
    game.move(Direction.UP);
    System.out.println(game.toString());
    game.move(Direction.DOWN);
    System.out.println(game.toString());
    game.move(Direction.LEFT);
    System.out.println(game.toString());
    game.move(Direction.RIGHT);
    System.out.println(game.toString());
    game.move(Direction.RIGHT);
    System.out.println(game.toString());
    game.move(Direction.RIGHT);
    System.out.println(game.toString());
    game.move(Direction.RIGHT);
    System.out.println(game.toString());
    game.move(Direction.DOWN);
    System.out.println(game.toString());

   }
}



