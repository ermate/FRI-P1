import java.util.*;
class DN06_63170083 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[][] tab = new int[k][2];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				int value = sc.nextInt();
				if (value != 0) {
					tab[value-1][0] = i;
					tab[value-1][1] = j;
				}
			}
		}
		int razdalja = 0;
		int x = 0;
		int y = 0;
		for(int i = 0; i < k; i++) {
			razdalja += Math.abs(tab[i][0]-x) + Math.abs(tab[i][1]-y);
			x = tab[i][0];
			y = tab[i][1];
		} 
		System.out.println(razdalja);
	}
}