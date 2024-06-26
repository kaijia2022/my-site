
public class CharMatrix {
	
	public final char SPACE = ' ';
	private char[][] grid;
	
	public CharMatrix(int rows, int cols) {
		grid = new char[rows][cols];
		clear();
	}
	
	public int rows() {
		return grid.length;
	}
	
	public int cols() {
		return grid[0].length;
	}
	
	public char charAt(int row, int col) {
		return grid[row][col];
	}
	
	public void setCharAt(int row, int col, char ch) {
		grid[row][col] = ch;		
	}
	
	public boolean isEmpty(int row, int col) {
		return charAt(row,col) == SPACE;
	}
	
	public char[][] getGrid() {
		return grid;
	}
	
	public void clear() {
		for (int r = 0; r < rows(); r++) {
			for (int c = 0; c < cols(); c++) {
				setCharAt(r,c, SPACE);
			}
		}
	}
	public void shift(int rowX) {
		int cols = cols();
		for (int r = rowX; r>=1; r--)
			for (int c = 0; c < cols; c++)
				grid[r][c] = grid[r-1][c];
		for (int c = 0; c< cols; c++)
			grid[0][c] = SPACE;		
	}
	
	public String rowToString(int row){
	    return new String(grid[row]);
	}
	

	
}
