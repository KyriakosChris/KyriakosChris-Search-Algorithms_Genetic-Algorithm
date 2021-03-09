package Genetikoi;

public class Main {
	public static void main(String[] args) {
		Generics g = new Generics();
		g.Array();
		// printX5ForExcel();
	}

	public static void printX5ForExcel() {
		float[] averages = new float[160];
		float[] mins = new float[160];
		for (int i = 0; i < 5; i++) {
			System.out.println(i);
			Generics g = new Generics();
			g.Array();
			for (int j = 0; j < 150; j++) {
				averages[j] = averages[j] + g.averages[j];
				mins[j] = mins[j] + g.mins[j];
			}
		}
		for (int j = 0; j < 150; j++) {
			averages[j] = averages[j] / 5;
			mins[j] = mins[j] / 5;
		}
		for (int j = 0; j < 150; j++)
			System.out.println(averages[j]);
		System.out.println();
		for (int j = 0; j < 150; j++)
			System.out.println(mins[j]);
	}
}