import java.util.*;
class DN07_63170083 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int stKvadratov = 0;
        int[][] matrika = new int[n][n];
        int[][] maxKvadrati = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrika[i][j] = sc.nextInt();
                if (i == 0 || j == 0) {
                    maxKvadrati[i][j] = matrika[i][j];
                    if (matrika[i][j] == 1) 
                        stKvadratov++;
                }
                if (matrika[i][j] == 1 && i != 0 && j != 0) {
                    int maxKvadrat = 0;
                    if (maxKvadrati[i][j-1] > maxKvadrat) 
                        maxKvadrat = maxKvadrati[i][j-1];
                    if (maxKvadrati[i-1][j] < maxKvadrat) 
                        maxKvadrat = maxKvadrati[i-1][j];
                    if (maxKvadrati[i-1][j-1] < maxKvadrat) 
                        maxKvadrat = maxKvadrati[i-1][j-1];

                    maxKvadrat++;    
                    maxKvadrati[i][j] = maxKvadrat;
                    stKvadratov += maxKvadrat;
                }
                if (matrika[i][j] == 0 && i != 0 && j != 0) 
                    maxKvadrati[i][j] = 0;
            }
        }
        System.out.println(stKvadratov);
    }
}  