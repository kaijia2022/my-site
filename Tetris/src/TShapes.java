import java.util.Stack;
public class TShapes {
	private static final int[] shapeIndex = {1,2,3,4,5,6,7};
	private int[][] shape;
	private static int[][] shape1 = {{1,0},
	                                 {1,0},
	                                 {1,1}},
			
						   shape2 = {{0,1},
								   	 {0,1},
								   	 {1,1}},
						   
						   shape3 = {{1,0},
								   	 {1,1},
								   	 {0,1}},
						   
						   shape4 = {{0,1},
								     {1,1},
								     {1,0}},
						   
						   shape5 = {{1,1},
								   	 {1,1}},
						   
						   shape6 = {{0,1},
								     {1,1},
						   			 {0,1}},
						   
						   shape7 = {{1,1,1,1}};
						   
	
	public TShapes() {
		
	}
	
	public void setRandomShape() {
		int i = (int)(Math.random() * shapeIndex.length);
		switch(i) {
		case 0: shape = shape1;     
				break;
		case 1: shape = shape2;
				break;
		case 2: shape = shape3;
				break;
		case 3: shape = shape4;
				break;
		case 4: shape = shape5;
				break;
		case 5: shape = shape6;
				break;
		case 6: shape = shape7;
				break;
		}
			
	}
	
	public int[][] getShape() {
		return shape;
	}
	
	public void rotateLeft() {
		int[][] temp = new int[shape[0].length][shape.length];
		Stack<Integer> stack = new Stack<Integer>();
		for (int c = 0; c < shape[0].length; c++) {
			for (int r = 0; r < shape.length; r++) {
				stack.push(shape[r][c]);
				}
			}
		for (int r=0;r < temp.length; r++) { 
			for (int c = temp[r].length-1; c>=0; c--){
				temp[r][c] = stack.pop();
				} 
			}
			shape = temp;
	}	
	

	
	/*public static void main (String[] args) {
		TShapes tshape = new TShapes();
		tshape.setRandomShape();
		int[][] shape = tshape.getShape();
		for (int r = 0; r < shape.length; r++) {
			for (int c = 0; c < shape[r].length; c++) {
				System.out.print(shape[r][c]);
			}
		}
		System.out.print("\n");
		tshape.rotateLeft();
		int[][] shape2 = tshape.getShape();
		for (int r = 0; r < shape2.length; r++) {
			for (int c = 0; c < shape2[r].length; c++) {
				System.out.print(shape2[r][c]);
			}
		}
		System.out.print("\n");
		tshape.rotateLeft();
		int[][] shape3 = tshape.getShape();
		for (int r = 0; r < shape3.length; r++) {
			for (int c = 0; c < shape3[r].length; c++) {
				System.out.print(shape3[r][c]);
			}
		}
		 
		
		
	}*/
	
	
}
