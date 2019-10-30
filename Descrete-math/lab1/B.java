import java.util.Arrays;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] matrix = new int[k][n];
        int[] size = new int[k];
        boolean has_one = false;
        boolean prot = true;
        for (int i = 0; i < k; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = in.nextInt();
                if (matrix[i][j] != -1) size[i]++;
            }
            if (size[i]==1) has_one = true;
        }

        int[] val = new int[n];
        Arrays.fill(val,-1);
        while (has_one){
            has_one = false;
            int num = -1;
            for (int i = 0; i < k; i++){
                if (size[i]==1){
                    has_one = true;
                    for (int j = 0; j < n; j++){
                        if (matrix[i][j]!=-1){
                            num = j;
                            break;
                        }
                    }
                    if (matrix[i][num] == 1) {
                        val[num] = 1;
                    } else {
                        val[num] = 0;
                    }
                    break;
                }
            }

            if (has_one){
                for (int i = 0; i < k; i++){
                    if (matrix[i][num] != -1){
                        if (size[i]==1){
                            if ((matrix[i][num]==1 && val[num]==0) || (matrix[i][num]==0 && val[num]==1)) {
                                System.out.println("YES");
                                System.exit(0);
                            }
                        }
                        if (matrix[i][num] == 1){
                            if (val[num]==1){
                                Arrays.fill(matrix[i],-1);
                                size[i] = 0;
                            } else {
                                size[i]--;
                                matrix[i][num] = -1;
                            }
                        } else {
                            if (val[num]==0){
                                Arrays.fill(matrix[i],-1);
                                size[i] = 0;
                            } else {
                                size[i]--;
                                matrix[i][num] = -1;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("NO");
    }
}
