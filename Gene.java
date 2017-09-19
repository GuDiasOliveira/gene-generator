
public enum Gene {
	ADENINE('A'),
	GUANINE('G'),
	CYTOSINE('C'),
	THYMINE('T');
	
	private char letter;
	
	Gene(char letter) {
		this.letter = letter;
	}
	
	public char getLetter() {
		return this.letter;
	}
	
	public static Gene get(char letter) {
		for (Gene g : values())
			if (g.getLetter() == letter)
				return g;
		return null;
	}
	
	@Override
	public String toString() {
		return String.valueOf(letter);
	}
}