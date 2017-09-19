import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	private static Scanner inp = new Scanner(System.in);

	public static void main(String[] args) {
		int genesCount = Integer.parseInt(readInput());
		int geneslength = Integer.parseInt(readInput());
		double[][] tMat = new double[4][4];
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer stk = new StringTokenizer(readInput(), " ");
			for (int j = 0; j < 4; j++) {
				String val = stk.nextToken();
				tMat[i][j] = Double.parseDouble(val.trim());
			}
		}
		
		GeneGenerator geneGen = new GeneGenerator(tMat);
		String initInp = readInput();
		if (initInp.length() == 1) {
			Gene init = Gene.get(initInp.charAt(0));
			if (init == null)
				throw new IllegalArgumentException("Invalid initial gene");
			for (int i = 0; i < genesCount; i++)
				System.out.println(geneGen.generate(geneslength, init));
		} else {
			StringTokenizer stk = new StringTokenizer(initInp, " ");
			double[] init = new double[4];
			for (int i = 0; i < 4; i++) {
				String val = stk.nextToken();
				init[i] = Double.parseDouble(val.trim());
			}
			for (int i = 0; i < genesCount; i++)
				System.out.println(geneGen.generate(geneslength,
						init[0], init[1], init[2], init[3]));
		}
	}
	
	private static String readInput() {
		String line;
		do {
			line = inp.nextLine().trim();
		} while (line.isEmpty() || line.startsWith("#"));
		return line;
	}
}
