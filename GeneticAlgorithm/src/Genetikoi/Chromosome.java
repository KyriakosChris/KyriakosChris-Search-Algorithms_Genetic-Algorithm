package Genetikoi;

public class Chromosome {
	private int[][] list;
	private float score;

	public Chromosome(int N, int M, int s) {
		this.list = new int[N][M];
		this.score = s;
	}

	public int[][] getList() {
		return list;
	}

	public void setList(int[][] list) {
		this.list = list;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
}