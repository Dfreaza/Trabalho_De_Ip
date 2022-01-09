import javax.xml.bind.JAXBElement.GlobalScope;

/**
 *@author Diogo Freaza - fc56969
 *@author Guilherme Wind - fc58640
 */

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


    /**
     * Cria o jogo Sokoban no nivel 1 
     */
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

    /**
     *Verifica o número de linhas do mapa
     * 
     * @return Retorna o número de linhas
     */
    public int getRows(){
       
        return numRows;
    }

    /**
     * Verifica o número de colunas do mapa
     * 
     * @return Retorna o número de colunas
     */
    public int getColumns(){
        
        return numColumns;
    }

    /**
     * Verifica a posição do player
     * 
     * @return Retorna um vetor com a posição do player
     */
    public int[] getPlayerPosition(){
        
        return playerPos;
    }

    /**
     * obtem a direção dada pelo jogaddor
     * 
     * @ensures devolve sempre um valor (DOWN, UP, RIGHT, LEFT) 
     * 
     * @return Retorna a direção dada e o seu respectivo valor 
     */
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

    /**
     * Verifica em que level o player se encontra
     * 
     * @return Retorna o level do player
     */
    public int getLevel(){
        
        return level; 
    }

    /**
     * Observa o número de movimentos dados pelo player
     * 
     * @return Retorna o número de movimentos
     */
    public int getNrMoves(){
        
        return numMoves;
    }

    /**
     * Verifica as posições das caixas
     * 
     * @return Retorna uma matriz com as posições das caixas
     */
    public int[][] getPositionBoxes(){
        
        return boxes;
    }

    /**
     * Verifica as posições dos objetivos
     * 
     * @return Retorna uma matiz com as posições dos obejtivos
     */
    public int[][] getPositionGoals(){
        
        return goals;
    }
    
    /**
     * Verifica se as coordenadas dadas são ou não ocupaveis
     * 
     * @param i Número da linha
     * @param j Número da coluna
     * 
     * @requires {@code 0≤i<getRows() e 0≤j<getColumns()}
     * 
     * @ensures {@code isValidMap(rows,columns,occupiableMap,goals,boxes,playerPos)}
     * 
     * @return Retorna um boleano com o respectivo valor lógico da posição
     */
    public boolean isOccupiable(int i, int j){
       
        return occupiable[i][j];
    }

    /**
     * Move o player segundo a direção dada pelo player
     * 
     * @param dir Direção dada pelo player
     * 
     * @requires {@code dir!=null && !levelCompleted()}
     */
    public void move(Direction dir){                        
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

    /**
     * Verifica se o level está concluido ou não ( todos os boxes nos goals)
     *
     * @ensures {@code /result == true || /result == false}
     * @return Retorna um boleano com o valor lógico do level 
     */
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

    /**
     * Verifica se o jogador atingiu o level máximo
     * 
     * @ensures {@code /result == true || /result == false}
     * @return Retorna um boleano com o  respectivo valor lógico
     */
    public boolean isTerminated(){
       boolean jogoAcabou = false;
       if(levelCompleted() && SokobanMapGenerator.numberOfLevels() == level){
           jogoAcabou = true;
       }
       return jogoAcabou;
    }

    /**
     * Após atingir o objetivo, carrega o próximo level
     * 
     * @requires {@code !isTerminated() && levelCompleted()}

     */
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
        
    /**
     * Reinicia o level em que o player se encontra
     */
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

    /**
     * Constroi o mapa segundo as intruções dadas
     * 
     * @ensures devolve uma string
     * @retrun Retorna uma string com o desenho do mapa
     */
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
                    }
                    for (int j2 = 0; j2 < boxes.length; j2++) {
                        if(boxes[j2][0] == i && boxes[j2][1] == j && !encontrouBoxGrid){
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


}



