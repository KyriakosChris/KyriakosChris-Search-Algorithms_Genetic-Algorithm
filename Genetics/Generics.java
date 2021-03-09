package Genetikoi;

import java.util.*;

public class Generics {
	float[] averages = new float[160];
	float[] mins = new float[160];
	final float Pmut = (float) 0.3;
	final float Pcross = (float) 0.7;
	final float Elitism = (float) 0.6;
	ArrayList<Integer> employee = new ArrayList<Integer>();
	ArrayList<Integer> hours = new ArrayList<Integer>();
	LinkedList<Chromosome> population = new LinkedList<>();

	public void Array() {
		for (int k = 0; k < 1000; k++) {
			Chromosome chr = new Chromosome(30, 14, 0);
			int[][] c = chr.getList();
			for (int j = 0; j < 14; j++) {
				WorkingHoursGenerator(j);
				for (int i = 0; i < 30; i++) {
					Random random = new Random();
					int p = random.nextInt(employee.size());
					c[employee.get(p)][j] = hours.get(p);
					employee.remove(p);
					hours.remove(p);
				}
			}
			chr.setList(c);
			population.add(chr);
		}
		for (int i = 0; i < population.size(); i++)
			Fitness(population.get(i));
		CrossOverCall();
	}

	public Chromosome RouletteWheelSelection() {
		float average = 0;
		Collections.shuffle(population);
		for (int j = 0; j < population.size() / 10; j++)
			average += population.get(j).getScore() / population.size();
		Random C = new Random();
		float rand = C.nextFloat() * average;
		float partialSum = 0;
		for (int j = 0; j < population.size() / 10; j++) {
			partialSum += population.get(j).getScore() / population.size();
			if (partialSum >= rand) {
				Chromosome chr = population.get(j);
				population.remove(j);
				return chr;
			}
		}
		return population.pollLast();

	}

	public void WorkingHoursGenerator(int day) {
		if (day == 0 || day == 1 || day == 7 || day == 8) {
			for (int i = 0; i < 30; i++) {
				if (i < 10) {
					employee.add(i);
					hours.add(1);
				} else if (i < 20) {
					employee.add(i);
					hours.add(2);
				} else if (i < 25) {
					employee.add(i);
					hours.add(3);
				} else {
					employee.add(i);
					hours.add(0);
				}
			}
		}
		if (day == 2 || day == 4 || day == 9 || day == 11) {
			for (int i = 0; i < 30; i++) {
				if (i < 5) {
					employee.add(i);
					hours.add(1);
				} else if (i < 15) {
					employee.add(i);
					hours.add(2);
				} else if (i < 20) {
					employee.add(i);
					hours.add(3);
				} else {
					employee.add(i);
					hours.add(0);
				}
			}
		}
		if (day == 3 || day == 5 || day == 6 || day == 10 || day == 12 || day == 13) {
			for (int i = 0; i < 30; i++) {
				if (i < 5) {
					employee.add(i);
					hours.add(1);
				} else if (i < 10) {
					employee.add(i);
					hours.add(2);
				} else if (i < 15) {
					employee.add(i);
					hours.add(3);
				} else {
					employee.add(i);
					hours.add(0);
				}
			}
		}
		Collections.shuffle(hours);
		Collections.shuffle(employee);
	}

	public void Fitness(Chromosome chr) {
		float score = 0;
		SoftConstraints s = new SoftConstraints();
		score = score + s.Seventyhours(chr);
		score = score + s.SevenDays(chr);
		score = score + s.FourNights(chr);
		score = score + s.NightToMorning(chr);
		score = score + s.AfternoonToMorning(chr);
		score = score + s.NightToAfternoon(chr);
		score = score + s.FourNightsTwoBreak(chr);
		score = score + s.SevenDaysTwoDaysBreak(chr);
		score = score + s.WorkWorkFreeWork(chr);
		score = score + s.WorkFreeWorkWork(chr);
		score = score + s.OneWeekend(chr);
		chr.setScore(score);
	}

	public void CrossOverCall() {
		int generations = 0;
		Random C = new Random();
		Chromosome Best = new Chromosome(30, 14, Integer.MAX_VALUE);
		for (generations = 0; generations <= 500; generations++) {
			for (int k = 0; k < population.size(); k++) {
				Chromosome chr1 = RouletteWheelSelection();
				if (Pcross < C.nextFloat() || Best.getScore() == chr1.getScore()) {
					population.add(chr1);
					continue;
				}
				if (chr1.getScore() < 50000) {
					if (Elitism > C.nextFloat()) {
						population.add(chr1);
						continue;
					}
				}
				Chromosome chr2 = RouletteWheelSelection();
				population.add(C.nextInt(population.size()), chr2);
				// CrossOverUniform(chr1, chr2);
				CrossOverTwoPoint(chr1, chr2);
			}
			float average = 0;
			float min = Integer.MAX_VALUE;
			for (int j = 0; j < population.size(); j++) {
				if (population.get(j).getScore() < min) {
					min = population.get(j).getScore();
					Best = population.get(j);
				}
				average += population.get(j).getScore() / population.size();
			}
			if (generations % 1 == 0) {
				// averages[generations / 10] = average;
				// mins[generations / 10] = min;
				System.out.println(
						"Generation: " + generations + "  Average score:  " + average + " Best Score:  " + min);
			}
		}
	}

	public void CrossOverTwoPoint(Chromosome chr1, Chromosome chr2) {
		Random C = new Random();
		int[][] c1 = chr1.getList();
		int[][] c2 = chr2.getList();
		int n1 = C.nextInt(7);
		int n2 = C.nextInt(7) + 7;
		int[][] tmp1 = new int[30][14];
		int Coin;
		Coin = C.nextInt(2);
		if (Coin == 0) {
			for (int j = 0; j < 14 - n2; j++) {
				for (int i = 0; i < 30; i++) {
					tmp1[i][j] = c1[i][j];
				}
			}
			for (int j = 14 - n2; j < 14; j++) {
				for (int i = 0; i < 30; i++) {
					tmp1[i][j] = c2[i][j];
				}
			}
		}
		if (Coin == 1) {
			for (int j = 0; j < n1; j++) {
				for (int i = 0; i < 30; i++) {
					tmp1[i][j] = c2[i][j];
				}
			}
			for (int j = n1; j < 14 - n2; j++) {
				for (int i = 0; i < 30; i++) {
					tmp1[i][j] = c1[i][j];
				}
			}
			for (int j = 14 - n2; j < 14; j++) {

				for (int i = 0; i < 30; i++) {
					tmp1[i][j] = c2[i][j];
				}
			}
		}
		if (Pmut > C.nextFloat()) {
			chr1.setList(tmp1);
			MutationInversion(chr1);
		} else {
			chr1.setList(tmp1);
			Fitness(chr1);
			population.add(chr1);
		}
	}

	public void CrossOverUniform(Chromosome chr1, Chromosome chr2) {
		Random C = new Random();
		int[][] c1 = chr1.getList();
		int[][] c2 = chr2.getList();
		int[][] tmp1 = new int[30][14];
		int Coin;
		for (int j = 0; j < 14; j++) {

			Coin = C.nextInt(2);
			for (int i = 0; i < 30; i++) {

				if (Coin == 0)
					tmp1[i][j] = c1[i][j];
				if (Coin == 1)
					tmp1[i][j] = c2[i][j];
			}
		}
		if (Pmut > C.nextFloat()) {
			chr1.setList(tmp1);
			MutationInversion(chr1);
		} else {
			chr1.setList(tmp1);
			Fitness(chr1);
			population.add(chr1);
		}
	}

	public void MutationSwap(Chromosome chr) {
		int[][] tmp1 = new int[30][14];
		Random C = new Random();
		int[][] c1 = chr.getList();
		tmp1 = chr.getList().clone();
		for (int j = 0; j < 7; j++) {
			int Coin = C.nextInt(4);
			if (Coin != 2)
				continue;
			for (int i = 0; i < 30; i++) {

				tmp1[i][j] = c1[i][j + 7];
				tmp1[i][j + 7] = c1[i][j];
			}
		}
		chr.setList(tmp1);
		Fitness(chr);
		population.add(chr);
	}

	public void MutationInversion(Chromosome chr) {
		int[][] tmp1 = new int[30][14];
		int[][] c1 = chr.getList().clone();
		for (int j = 0; j < 7; j++) {
			for (int p = 0; p < 30; p++) {
				tmp1[p][j] = c1[p][j + 7];
				tmp1[p][j + 7] = c1[p][j];
			}
		}
		chr.setList(tmp1);
		Fitness(chr);
		population.add(chr);
	}
}