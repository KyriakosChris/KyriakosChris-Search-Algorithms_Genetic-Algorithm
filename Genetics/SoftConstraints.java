package Genetikoi;

public class SoftConstraints {

	public float Seventyhours(Chromosome chr) {
		int[][] c = chr.getList();
		int counter = 0;
		for (int j = 0; j < 30; j++) {
			int hours = 0;
			for (int i = 0; i < 14; i++) {
				if (c[j][i] != 0)
					hours = hours + 8;
			}
			if (hours > 70)
				counter += 1000;
		}
		return counter;
	}

	public float SevenDays(Chromosome chr) {
		int[][] c = chr.getList();
		int counter = 0;
		for (int j = 0; j < 30; j++) {
			int ContiniousDays = 0;
			for (int i = 0; i < 14; i++) {
				if (c[j][i] != 0)
					ContiniousDays++;
				if (c[j][i] == 0) {
					ContiniousDays = 0;
				}
				if (ContiniousDays > 7)
					counter += 1000;
			}
		}
		return counter;
	}

	public float FourNights(Chromosome chr) {
		int[][] c = chr.getList();
		int counter = 0;
		for (int j = 0; j < 30; j++) {
			int ContiniousNights = 0;
			for (int i = 0; i < 14; i++) {
				if (c[j][i] == 3)
					ContiniousNights++;
				if (c[j][i] != 3) {
					ContiniousNights = 0;
				}
				if (ContiniousNights > 4)
					counter += 1000;
			}
		}
		return counter;
	}

	public float NightToMorning(Chromosome chr) {
		int[][] c = chr.getList();
		int counter = 0;
		for (int j = 0; j < 30; j++) {
			for (int i = 0; i < 13; i++) {
				if (c[j][i] == 3 && c[j][i + 1] == 1)
					counter += 1000;
			}
		}
		return counter;
	}

	public float AfternoonToMorning(Chromosome chr) {
		int[][] c = chr.getList();
		int counter = 0;
		for (int j = 0; j < 30; j++) {
			for (int i = 0; i < 13; i++) {
				if (c[j][i] == 2 && c[j][i + 1] == 1)
					counter += 800;
			}
		}
		return counter;
	}

	public float NightToAfternoon(Chromosome chr) {
		int[][] c = chr.getList();
		int counter = 0;
		for (int j = 0; j < 30; j++) {
			for (int i = 0; i < 13; i++) {
				if (c[j][i] == 3 && c[j][i + 1] == 2)
					counter += 800;
			}
		}
		return counter;
	}

	public float FourNightsTwoBreak(Chromosome chr) {
		int[][] c = chr.getList();
		int counter = 0;
		for (int j = 0; j < 30; j++) {
			int ContiniousNights = 0;
			for (int i = 0; i < 12; i++) {
				if (c[j][i] == 3)
					ContiniousNights++;
				if (c[j][i] != 3) {
					ContiniousNights = 0;
				}
				if (ContiniousNights > 4)
					if (!(c[j][i + 1] == 0 && c[j][i + 2] == 0))
						counter += 100;
			}
		}
		return counter;
	}

	public float SevenDaysTwoDaysBreak(Chromosome chr) {
		int[][] c = chr.getList();
		int counter = 0;
		for (int j = 0; j < 30; j++) {
			int ContiniousDays = 0;
			for (int i = 0; i < 12; i++) {
				if (c[j][i] != 0)
					ContiniousDays++;
				if (c[j][i] == 0) {
					ContiniousDays = 0;
				}
				if (ContiniousDays > 7) {
					if (!(c[j][i + 1] == 0 && c[j][i + 2] == 0))
						counter += 100;
				}
			}
		}
		return counter;
	}

	public float WorkWorkFreeWork(Chromosome chr) {
		int[][] c = chr.getList();
		int counter = 0;
		for (int j = 0; j < 30; j++) {
			for (int i = 0; i < 11; i++) {
				if (c[j][i] != 0 && c[j][i + 1] != 0 && c[j][i + 2] == 0 && c[j][i + 3] == 0)
					counter += 1;
			}
		}
		return counter;
	}

	public float WorkFreeWorkWork(Chromosome chr) {
		int[][] c = chr.getList();
		int counter = 0;
		for (int j = 0; j < 30; j++) {
			for (int i = 0; i < 11; i++) {
				if (c[j][i] != 0 && c[j][i + 1] == 0 && c[j][i + 2] != 0 && c[j][i + 3] == 0)
					counter += 1;
			}
		}
		return counter;
	}

	public float OneWeekend(Chromosome chr) {
		int[][] c = chr.getList();
		int counter = 0;
		for (int j = 0; j < 30; j++) {
			if ((c[j][5] != 0 || c[j][6] != 0) ^ (c[j][12] != 0 || c[j][13] != 0)
					|| ((c[j][5] == 0 && c[j][6] == 0 && c[j][12] == 0 && c[j][13] == 0)))
				counter += 1;
		}
		return counter;
	}
}
