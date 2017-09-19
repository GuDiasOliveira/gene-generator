import java.util.Arrays;

public class GeneGenerator {
	private MarkovChain<Gene> mMarkov;
	
	public GeneGenerator(double[][] transitionMatrix) {
		double[][] tMat = new double[4][4];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				tMat[i][j] = transitionMatrix[i][j];
		mMarkov = new MarkovChain<>(tMat, Arrays.asList(
				Gene.ADENINE, Gene.GUANINE, Gene.CYTOSINE, Gene.THYMINE
				));
	}
	
	public void setProbability(Gene from, Gene to, double p) {
		mMarkov.set(from, to, p);
	}
	
	public Gene getGene(double pA, double pG, double pC, double pT) {
		double pSum = pA + pG + pC + pT;
		double rand = Math.random();
		if (rand < (pA / pSum))
			return Gene.ADENINE;
		rand -= pA / pSum;
		if (rand < (pG / pSum))
			return Gene.GUANINE;
		rand -= pG / pSum;
		if (rand < (pC / pSum))
			return Gene.CYTOSINE;
		return Gene.THYMINE;
	}
	
	public String generate(int length) {
		return generate(length, 0.25, 0.25, 0.25, 0.25);
	}
	
	public String generate(int length, double initpA, double initpG, double initpC, double initpT) {
		return generate(length, getGene(initpA, initpG, initpC, initpT));
	}
	
	public String generate(int length, Gene initial) {
		if (length <= 0)
			return "";
		char[] genes = new char[length];
		genes[0] = initial.getLetter();
		for (int i = 1; i < length; i++) {
			Gene lastGene = Gene.get(genes[i - 1]);
			double pA = mMarkov.get(lastGene, Gene.ADENINE);
			double pG = mMarkov.get(lastGene, Gene.GUANINE);
			double pC = mMarkov.get(lastGene, Gene.CYTOSINE);
			double pT = mMarkov.get(lastGene, Gene.THYMINE);
			genes[i] = getGene(pA, pG, pC, pT).getLetter();
		}
		return new String(genes);
	}
}