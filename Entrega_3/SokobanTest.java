import java.util.Arrays;

/**
* The {@code SokobanTest} class tests the methods and functions described in the
* API of classes Direction, SokobanMap and SokobanGame in simple scenarios.
* This class is provided in the scope of the course Introduction to Programming
* at FCUL (2021/22).
*
* @author Wellington Oliveira
* @date 08 Dez 2021
*/

public class SokobanTest {

	/**
	 * Executes tests on classes Direction, SokobanMap e SokobanGame
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(">>>> Starting tests <<<<");
		testDirection();
		testSokobanMap();
		testSokobanGame();
		System.out.println(">>>> Tests finished <<<<");
		System.out.println("Do not forget: these are just a couple of simple tests.");
		System.out.println("Test your code with additional tests!!!");
	}

	/**
	 * Tests the directions of enum Direction:
	 */
	private static void testDirection() {
		String msgTest;

		System.out.println(">>> Testing enum Direction:");
		Direction[] userDir = Direction.values();
		System.out.println(">>Direction size: ");

		msgTest = userDir.length == 4 ? "OK"
				: "\n ERROR: incorrect size. Expected: 4 - Found: " + userDir.length + " \n";
		System.out.println(msgTest);

		System.out.println(">>Direction elements: ");
		Direction[] trueDir = { Direction.DOWN, Direction.UP, Direction.LEFT, Direction.RIGHT };
		msgTest = containsAllDirections(userDir, trueDir) ? "OK"
				: "\n ERROR: Direction does not contain the correct elements ";
		System.out.println(msgTest);

	}

	/**
	 * Check if the Directions are correct.
	 *
	 * @param userDir user-defined Direction
	 * @param trueDir true Direction
	 * @return if userDir and trueDir are the same, returns true. Return false
	 *         otherwise.
	 */
	private static boolean containsAllDirections(Direction[] userDir, Direction[] trueDir) {
		boolean ret = true;
		boolean found = false;
		for (Direction dir : trueDir) {
			for (Direction sDir : userDir) {
				if (dir.equals(sDir))
					found = true;
			}
			ret &= found;
			found = false;
		}
		return ret;
	}

	/**
	 * Creates a SokobanMap of a given level
	 *
	 * @param level given level
	 * @return an instance of SokobanMap
	 */
	private static SokobanMap getMap(int level) {

		SokobanMap map = new SokobanMap(
				SokobanMapGenerator.getNrRows(level),
				SokobanMapGenerator.getNrColumns(level),
				SokobanMapGenerator.getOccupiableMap(level),
				SokobanMapGenerator.getGoals(level),
				SokobanMapGenerator.getBoxes(level),
				SokobanMapGenerator.getPlayer(level));
		return map;
	}

	/**
	 * Check if two matrices are the same
	 *
	 * @param m matrix of int
	 * @param n matrix of int
	 * @return if m and n are equals, returns true. Return false otherwise.
	 */
	private static boolean equalsMatrix(int[][] m, int[][] n) {
		boolean ret = true;
		for (int i = 0; i < n.length; i++)
			ret &= Arrays.equals(m[i], n[i]);
		return ret;
	}

	/**
	 * Check if all occupiable spaces on the given SokobanMap are the same as in the
	 * given level
	 *
	 * @param map   given SokobanMap
	 * @param level given level
	 * @return if the occupiable spaces are the same, returns true. Return false
	 *         otherwise.
	 */
	private static int[] checkIsOccupiable(SokobanMap map, int level) {
		boolean[][] occupiableMap = SokobanMapGenerator.getOccupiableMap(level);
		int[] wrongPos = new int[] { -1, -1 };
		for (int i = 0; i < map.getRows(); i++) {
			for (int j = 0; j < map.getColumns(); j++) {
				if (map.isOccupiable(i, j) != occupiableMap[i][j]) {
					wrongPos[0] = i;
					wrongPos[1] = j;
				}
			}
		}
		return wrongPos;
	}

	/**
	 * Check if all occupiable spaces on the given SokobanGame are the same as in
	 * the given level
	 *
	 * @param map   given SokobanGame
	 * @param level given level
	 * @return if the occupiable spaces are the same, returns true. Return false
	 *         otherwise.
	 */
	private static int[] checkIsOccupiable(SokobanGame game, int level) {
		boolean[][] occupiableMap = SokobanMapGenerator.getOccupiableMap(level);
		int[] wrongPos = new int[] { -1, -1 };
		for (int i = 0; i < game.getRows(); i++) {
			for (int j = 0; j < game.getColumns(); j++) {
				if (game.isOccupiable(i, j) != occupiableMap[i][j]) {
					wrongPos[0] = i;
					wrongPos[1] = j;
				}
			}
		}
		return wrongPos;
	}

	/**
	 * Check if a given game is in a given level
	 *
	 * @param game  given SokobanGame
	 * @param level given level
	 * @return if the SokobanGame is in the given level, returns true; Return false
	 *         otherwise.
	 */
	private static boolean isGameInLevel(SokobanGame game, int level) {
		return game.getRows() == SokobanMapGenerator.getNrRows(level) &&
				game.getColumns() == SokobanMapGenerator.getNrColumns(level) &&
				Arrays.equals(checkIsOccupiable(game, level), new int[] { -1, -1 }) &&
				equalsMatrix(game.getPositionGoals(), SokobanMapGenerator.getGoals(level)) &&
				equalsMatrix(game.getPositionBoxes(), SokobanMapGenerator.getBoxes(level));
	}

	/**
	 * Check if the restart level is in fact returning the original values to the
	 * elements of the game.
	 *
	 * @param game given SokobanGame
	 */
	private static void testReset(SokobanGame game) {
		String msgTest;
		String error = "";
		int level = game.getLevel();
		boolean pPos = Arrays.equals(game.getPlayerPosition(), SokobanMapGenerator.getPlayer(level));
		error += pPos ? "" : " wrong player positon ";
		boolean boxes = equalsMatrix(game.getPositionBoxes(), SokobanMapGenerator.getBoxes(level));
		error += boxes ? "" : " wrong boxes positon ";
		boolean goals = equalsMatrix(game.getPositionGoals(), SokobanMapGenerator.getGoals(level));
		error += goals ? "" : " wrong goals positon ";
		boolean moves = game.getNrMoves() == 0;
		error += moves ? "" : " wrong moves quantity ";
		boolean direction = game.getDirection() == Direction.DOWN;
		error += direction ? "" : " wrong direction";

		msgTest = error.equals("") ? "OK"
				: "\n ERROR: " + error + "\n";
		System.out.println(msgTest);
	}

	/**
	 * Check if a given number of movements results in the right results for the
	 * player on a given SokobanGame.
	 *
	 * @param game   given SokobanGame
	 * @param nMoves expected quantity of moves at the end of the movement
	 * @param pPos   expected player position at the end of the movement
	 * @param moves  given directions the character should move
	 */
	private static void moveAndCheck(SokobanGame game, int nMoves, int[] pPos, Direction... moves) {

		String msgTest;
		for (Direction dir : moves) {
			game.move(dir);
		}

		System.out.print("Direction: ");
		msgTest = game.getDirection() == moves[moves.length - 1] ? "OK" : "\n ERROR: incorrect direction \n";
		System.out.println(msgTest);

		System.out.print("Moves: ");
		msgTest = game.getNrMoves() == nMoves ? "OK" : "\n ERROR: incorrect number of moves \n";
		System.out.println(msgTest);

		System.out.print("Player position: ");
		msgTest = Arrays.equals(pPos, game.getPlayerPosition()) ? "OK"
				: "\n ERROR: incorrect player position \n";
		System.out.println(msgTest);
	}

	/**
	 * Check if a given number of movements results in the right results for the
	 * player and the box they are pushing on a given SokobanGame.
	 *
	 * @param game   given SokobanGame
	 * @param nMoves expected quantity of moves at the end of the movement
	 * @param pPos   expected player position at the end of the movement
	 * @param bPos   expected box position at the end of the movement
	 * @param bIndex index of the box that will be moved.
	 * @param moves  given directions the character should move
	 */
	private static void moveCharacter(SokobanGame game, int nMoves, int[] pPos, int[] bPos, int bIndex,
			Direction... moves) {
		String msgTest;
		moveAndCheck(game, nMoves, pPos, moves);
		System.out.print("Box position: ");
		msgTest = Arrays.equals(bPos, game.getPositionBoxes()[bIndex]) ? "OK"
				: "\n ERROR: incorrect box position \n";
		System.out.println(msgTest);
	}

	/**
	 * Tests functions and methods of class SokobanMap:
	 * - SokobanMap(...);
	 * - static isValid(...)
	 * - getRows()
	 * - getColumns()
	 * - getNrBoxes()
	 * - getInitialPlayerPosition()
	 * - getInitialPositionBoxes()
	 * - getInitialPositionGoals()
	 * - isOccupiable(int i, int j)
	 */
	private static void testSokobanMap() {

		String msgTest = "";
		System.out.println(">>> Testing class SokobanMap:");
		System.out.println(">> Testing constructor:");
		int level = 1;
		try {
			SokobanMap map = getMap(level);
			msgTest = "OK";
			System.out.println(msgTest);

			System.out.println(">>Testing function boolean isValidMap(...)");

			System.out.println("> on a valid map: ");
			msgTest = SokobanMap.isValidMap(
					SokobanMapGenerator.getNrRows(level),
					SokobanMapGenerator.getNrColumns(level),
					SokobanMapGenerator.getOccupiableMap(level),
					SokobanMapGenerator.getGoals(level),
					SokobanMapGenerator.getBoxes(level),
					SokobanMapGenerator.getPlayer(level)) ? "OK" : "\n ERROR: function did not validate a valid map \n";
			System.out.println(msgTest);

			System.out.println("> on a invalid map: ");
			msgTest = !SokobanMap.isValidMap(
					SokobanMapGenerator.getNrRows(level),
					SokobanMapGenerator.getNrColumns(level),
					SokobanMapGenerator.getOccupiableMap(level),
					SokobanMapGenerator.getGoals(level),
					SokobanMapGenerator.getBoxes(level),
					new int[] { -1, -1 }) ? "OK" : "\n ERROR: function did validate an invalid map \n";
			System.out.println(msgTest);

			System.out.println(">>Testing function int getRows()");
			msgTest = SokobanMapGenerator.getNrRows(level) == map.getRows() ? "OK"
					: "\n ERROR: incorrect number of rows \n";
			System.out.println(msgTest);

			System.out.println(">>Testing function int getColumns()");
			msgTest = SokobanMapGenerator.getNrColumns(level) == map.getColumns() ? "OK"
					: "\n ERROR: incorrect number of columns \n";
			System.out.println(msgTest);

			System.out.println(">>Testing function int getNrBoxes()");
			msgTest = SokobanMapGenerator.getBoxes(level).length == map.getNrBoxes() ? "OK"
					: "\n ERROR: incorrect number of boxes \n";
			System.out.println(msgTest);

			System.out.println(">>Testing function int[] getInitialPlayerPosition()");
			msgTest = Arrays.equals(SokobanMapGenerator.getPlayer(level), map.getInitialPlayerPosition()) ? "OK"
					: "\n ERROR: incorrect player position \n";
			System.out.println(msgTest);

			System.out.println(">>Testing function int[][] getInitialPositionBoxes()");
			msgTest = equalsMatrix(SokobanMapGenerator.getBoxes(level), map.getInitialPositionBoxes()) ? "OK"
					: "\n ERROR: incorrect boxes positions \n";
			System.out.println(msgTest);

			System.out.println(">>Testing function int[][] getInitialPositionGoals()");
			msgTest = equalsMatrix(SokobanMapGenerator.getGoals(level), map.getInitialPositionGoals()) ? "OK"
					: "\n ERROR: incorrect goals positions \n";
			System.out.println(msgTest);

			System.out.println(">>Testing function boolean isOccupiable(int i, int j)");
			int[] checkIsOccupiable = checkIsOccupiable(map, level);
			msgTest = Arrays.equals(checkIsOccupiable, new int[] { -1, -1 }) ? "OK"
					: "\n ERROR: incorrect for position " + checkIsOccupiable[0] + "," + checkIsOccupiable[1] + " \n";
			System.out.println(msgTest);

		} catch (Exception e) {
			msgTest = "\n ERROR: Failed because of " + e.toString() + " \n";
			System.out.println(msgTest);
		}
	}

	/**
	 * Tests functions and methods of class SokobanMap:
	 * - SokobanGame();
	 * - getRows()
	 * - getColumns()
	 * - getPlayerPosition()
	 * - getDirection()
	 * - getLevel()
	 * - getNrMoves()
	 * - getPositionBoxes()
	 * - getPositionGoals()
	 * - isOccupiable(int i, int j)
	 * - move(Direction dir)
	 * - levelCompleted()
	 * - loadNextLevel()
	 * - restartLevel()
	 */
	private static void testSokobanGame() {

		String msgTest = "";
		System.out.println(">>> Testing class SokobanGame:");
		System.out.println(">> Testing constructor:");
		try {
			SokobanGame game = new SokobanGame();
			msgTest = "OK";
			System.out.println(msgTest);

			System.out.println(">>Testing function int getRows()");
			msgTest = SokobanMapGenerator.getNrRows(game.getLevel()) == game.getRows() ? "OK"
					: "ERROR: incorrect number of rows ";
			System.out.println(msgTest);

			System.out.println(">>Testing function int getColumns()");
			msgTest = SokobanMapGenerator.getNrColumns(game.getLevel()) == game.getColumns() ? "OK"
					: "ERROR: incorrect number of columns ";
			System.out.println(msgTest);

			System.out.println(">>Testing function int[] getPlayerPositon()");
			msgTest = Arrays.equals(SokobanMapGenerator.getPlayer(game.getLevel()), game.getPlayerPosition()) ? "OK"
					: "ERROR: incorrect player position ";
			System.out.println(msgTest);

			System.out.println(">>Testing function Direction getDirection()");
			msgTest = game.getDirection() == Direction.DOWN ? "OK" : "ERROR: incorrect direction ";
			System.out.println(msgTest);

			System.out.println(">>Testing function int getLevel()");
			msgTest = game.getLevel() == 1 ? "OK" : "ERROR: incorrect level ";
			System.out.println(msgTest);

			System.out.println(">>Testing function int getNrMoves()");
			msgTest = game.getNrMoves() == 0 ? "OK" : "ERROR: incorrect number of moves ";
			System.out.println(msgTest);

			System.out.println(">>Testing function int[][] getPositionBoxes()");
			msgTest = equalsMatrix(SokobanMapGenerator.getBoxes(game.getLevel()), game.getPositionBoxes()) ? "OK"
					: "ERROR: incorrect boxes positions ";
			System.out.println(msgTest);

			System.out.println(">>Testing function int[][] getPositionGoals()");
			msgTest = equalsMatrix(SokobanMapGenerator.getGoals(game.getLevel()), game.getPositionGoals()) ? "OK"
					: "ERROR: incorrect goals positions ";
			System.out.println(msgTest);

			System.out.println(">>Testing function boolean isOccupiable(int i, int j)");
			int[] checkIsOccupiable = checkIsOccupiable(game, game.getLevel());
			msgTest = Arrays.equals(checkIsOccupiable, new int[] { -1, -1 }) ? "OK"
					: "ERROR: incorrect for position " + checkIsOccupiable[0] + "," + checkIsOccupiable[1];
			System.out.println(msgTest);

			System.out.println(">>Testing function void loadNextLevel()");
			game.loadNextLevel();
			game.loadNextLevel();
			game.loadNextLevel();
			msgTest = game.getLevel() == 4 && isGameInLevel(game, 4) ? "OK"
					: "\n ERROR: incorrect level loaded \n";
			System.out.println(msgTest);

			game = new SokobanGame();

			System.out.println(">>Testing function void move(Direction dir)");

			System.out.println(">Moving RIGHT UP UP -> box off-grid");
			moveAndCheck(game, 2, new int[] { 1, 2 }, Direction.RIGHT, Direction.UP, Direction.UP);

			System.out.println(">Moving RIGHT UP UP -> off-grid");
			moveAndCheck(game, 4, new int[] { 0, 3 }, Direction.RIGHT, Direction.UP, Direction.UP);

			// going to level 4 to test more movements
			System.out.println("...Going to level 4...");
			game.loadNextLevel();
			game.loadNextLevel();
			game.loadNextLevel();

			System.out.println(">Moving UP -> Wall");
			moveAndCheck(game, 0, new int[] { 1, 2 }, Direction.UP);

			System.out.println(">Moving DOWN -> Wall");
			moveAndCheck(game, 0, new int[] { 1, 2 }, Direction.DOWN);

			System.out.println(">Moving LEFT -> Open goal");
			moveAndCheck(game, 1, new int[] { 1, 1 }, Direction.LEFT);

			System.out.println(">Moving RIGHT RIGHT -> Push box");
			moveCharacter(game, 3, new int[] { 1, 3 }, new int[] { 1, 4 }, 0, Direction.RIGHT, Direction.RIGHT);

			System.out.println(">Moving RIGHT RIGHT -> Push box wall");
			moveCharacter(game, 4, new int[] { 1, 4 }, new int[] { 1, 5 }, 0, Direction.RIGHT, Direction.RIGHT);

			System.out.println(">Moving DOWN -> Push two boxes");
			moveCharacter(game, 4, new int[] { 1, 4 }, new int[] { 2, 4 }, 1, Direction.DOWN);

			game = new SokobanGame();

			System.out.println(">>Testing function levelCompleted()");
			// complete level 1
			game.move(Direction.UP);
			game.move(Direction.UP);
			game.move(Direction.RIGHT);
			game.move(Direction.DOWN);
			game.move(Direction.DOWN);

			msgTest = game.levelCompleted() ? "OK"
					: "\n ERROR: level is not being detected as completed correctly \n";
			System.out.println(msgTest);

			System.out.println(">>Testing function void restartLevel()");
			game.restartLevel();

			System.out.println(">without moving");
			testReset(game);

			System.out.println(">moving boxes and restart");
			game.move(Direction.UP);
			game.move(Direction.RIGHT);
			game.restartLevel();

			game.move(Direction.UP);
			game.move(Direction.RIGHT);
			game.restartLevel();
			testReset(game);

		} catch (Exception e) {
			msgTest = "\n ERROR: Failed because of " + e.toString() + "\n";
			System.out.println(msgTest);
		}
	}

}
