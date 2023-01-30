package visuals;

import java.util.HashMap;
import game.Tile;

/**
* The TilePrinter class stores ANSI-coded colors to color code and pretty print @see Tiles.
* 
* @author Arthur Bosquetti
* 
*/
public class TilePrinter {
	
	private static final String ANSI_RESET			= "\u001B[0m";			// 	  -
	private static final String ANSI_WHITE 			= "\u001b[38;5;231m";	// 	  0
	private static final String ANSI_LIGHT_BEIGE 	= "\u001b[38;5;230m";	// 	  2
	private static final String ANSI_BEIGE 			= "\u001b[38;5;223m";	// 	  4
	private static final String ANSI_LIGHT_ORANGE	= "\u001b[38;5;209m";	//    8
	private static final String ANSI_ORANGE			= "\u001b[38;5;208m";	//   16
	private static final String ANSI_MEDIUM_ORANGE	= "\u001b[38;5;202m";	//   32
	private static final String ANSI_RED			= "\u001b[38;5;196m";	//   64
	private static final String ANSI_LIGHT_YELLOW	= "\u001b[38;5;228m";	//  128
	private static final String ANSI_YELLOW			= "\u001b[38;5;227m";	//  256
	private static final String ANSI_MEDIUM_YELLOW	= "\u001b[38;5;226m";	//  512
	private static final String ANSI_STRONG_YELLOW	= "\u001b[38;5;220m";	// 1024
	private static final String ANSI_GREEN			= "\u001b[38;5;118m";	// 2048
	private static final String ANSI_BLACK			= "\u001b[38;5;232m";	// 2048+	
	
	private HashMap<Integer, String> colorMap;
	
	public TilePrinter() {
		colorMap = new HashMap<Integer, String>();	
		fillHashMap();
	}
	
	/**
	 * This method adds the pretty-print formatted output text to the colorMap Hash Map used when 
	 * printing tiles with values up to 2048.
	 */
	private void fillHashMap() {
		colorMap.put(0, ANSI_WHITE + "   0  " + ANSI_RESET);
		colorMap.put(2, ANSI_LIGHT_BEIGE + "   2  " + ANSI_RESET);
		colorMap.put(4, ANSI_BEIGE + "   4  " + ANSI_RESET);
		colorMap.put(8, ANSI_LIGHT_ORANGE + "   8  " + ANSI_RESET);
		colorMap.put(16, ANSI_ORANGE + "   16 " + ANSI_RESET);
		colorMap.put(32, ANSI_MEDIUM_ORANGE + "   32 " + ANSI_RESET);
		colorMap.put(64, ANSI_RED + "   64 " + ANSI_RESET);
		colorMap.put(128, ANSI_LIGHT_YELLOW + "  128 " + ANSI_RESET);
		colorMap.put(256, ANSI_YELLOW + "  256 " + ANSI_RESET);
		colorMap.put(512, ANSI_MEDIUM_YELLOW + "  512 " + ANSI_RESET);
		colorMap.put(1024, ANSI_STRONG_YELLOW + " 1024 " + ANSI_RESET);
		colorMap.put(2048, ANSI_GREEN + " 2048 " + ANSI_RESET);
	}
	
	public void printColoredTile(Tile tile) {
		if (tile.getNumber() <= 2048) System.out.print(colorMap.get(tile.getNumber()));
		else System.out.print(" " + ANSI_BLACK + tile.getNumber() + ANSI_RESET + " ");	
	}	
}
