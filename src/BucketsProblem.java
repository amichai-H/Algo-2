import java.util.Arrays;

public class BucketsProblem {
    private int sizeBucketA;
    private int sizeBucketB;
    private boolean[][] neighborMatrix;
    private boolean[][] trackMatrix;
    private String[][] theTrac;
    public BucketsProblem(int sizeBucketA,int sizeBucketB){
        this.sizeBucketA = sizeBucketA;
        this.sizeBucketB = sizeBucketB;
        int m = sizeBucketA+1;
        int n = sizeBucketB+1;
        neighborMatrix = new boolean[n*m][n*m];
        createTable();
        trackMatrix = new boolean[n*m][n*m];
        theTrac = new String[n*m][n*m];
        createTraks();
    }

    private void createTable() {
        int m = sizeBucketA;
        int n = sizeBucketB;
        for (int k = 0; k < neighborMatrix.length; k++){
            int capacityA = k / (n + 1);
            int capacityB = k % (n + 1);
            neighborMatrix[k][k]=true;
            int index = 0;

            index = emptyA(capacityA,capacityB);
            neighborMatrix[k][index] = true;

            index = emptyB(capacityA,capacityB);
            neighborMatrix[k][index] = true;

            index = fillA(capacityA,capacityB);
            neighborMatrix[k][index] = true;

            index = fillB(capacityA,capacityB);
            neighborMatrix[k][index] = true;

            index = AtoB(capacityA,capacityB);
            neighborMatrix[k][index] = true;

            index = BtoA(capacityA,capacityB);
            neighborMatrix[k][index] = true;
        }
    }
    int emptyA(int capacityA,int capacityB){
        return capacityB;
    }
    int emptyB(int capacityA,int capacityB){
        int n = (sizeBucketB+1);
        return capacityA * n;
    }
    int fillA(int capacityA,int capacityB){
        int n = (sizeBucketB+1);
        return n * sizeBucketA + capacityB;
    }
    int fillB(int capacityA,int capacityB){
        int n = (sizeBucketB+1);
        return n * capacityA + sizeBucketB;
    }
    int AtoB(int capacityA, int capacityB){
        int n = (sizeBucketB+1);
        int j = Math.min(capacityB+capacityA,sizeBucketB);
        int i = capacityB+capacityA - j;
        return n * i + j;
    }
    int BtoA(int capacityA, int capacityB){
        int n = (sizeBucketB+1);
        int i = Math.min(capacityB+capacityA,sizeBucketA);
        int j = capacityB+capacityA - i;
        return n * i + j;

    }

    private void createTraks() {
        for (int i = 0; i < neighborMatrix.length; i++) {
            int cA = i / (sizeBucketB + 1);
            int cB = i % (sizeBucketB + 1);
            String s2 = "(" + cA + " ," + cB + ")";
            for (int j = 0; j < neighborMatrix[i].length; j++) {
                int capacityA = j / (sizeBucketB + 1);
                int capacityB = j % (sizeBucketB + 1);
                String s = s2 + " -> (" + capacityA + " ," + capacityB + ")";
                if (cA == capacityA && cB == capacityB) {
                    theTrac[i][j] = s2;
                } else if (neighborMatrix[i][j]) {
                    theTrac[i][j] = s + "";
                }
            }
        }
        for (int k = 0; k < neighborMatrix.length; k++) {
            for (int i = 0; i < neighborMatrix.length; i++) {
                for (int j = 0; j < neighborMatrix[i].length; j++) {
                        trackMatrix[i][j] = (neighborMatrix[i][j] || (neighborMatrix[i][k] && neighborMatrix[k][j])) || (trackMatrix[i][j] || (trackMatrix[i][k] && trackMatrix[k][j]));
                }
            }
        }
        int go = 1;
        boolean[][] fill = new boolean[neighborMatrix.length][neighborMatrix.length];
        while (go>0) {
            for (int k = 0; k < neighborMatrix.length; k++) {
                for (int i = 0; i < neighborMatrix.length; i++) {
                    for (int j = 0; j < neighborMatrix[i].length; j++) {
                        if (!neighborMatrix[i][j] && trackMatrix[i][j] && (theTrac[i][j] == null || i == j)) {
                            if (theTrac[i][k] != null && theTrac[k][j] != null && !fill[i][j]) {
                                fill[i][j] = true;
                                theTrac[i][j] = theTrac[i][k] + " -> " + theTrac[k][j];
                            }
                            else go++;
                        }


                    }
                }
            }
            go--;
        }

    }



    public  void printM(boolean[][] arr){
        String[] arrayS = new String[arr.length];
        for (int i = 0; i<arr.length;i++) {
            int capacityA = i / (sizeBucketB + 1);
            int capacityB = i % (sizeBucketB + 1);
            String s = "("+ capacityA+ " ," + capacityB + ")";
            arrayS[i] = s;
        }
        System.out.println("      " + Arrays.toString(arrayS));
        for (int i = 0; i<arr.length;i++){
            int capacityA = i / (sizeBucketB + 1);
            int capacityB = i % (sizeBucketB + 1);
            String s = "("+ capacityA+ " ," + capacityB + ")";
            System.out.println(s + " " + Arrays.toString(arr[i]));
        }
    }
    public  void printM(String[][] arr){
        String[] arrayS = new String[arr.length];
        for (int i = 0; i<arr.length;i++) {
            int capacityA = i / (sizeBucketB + 1);
            int capacityB = i % (sizeBucketB + 1);
            String s = "("+ capacityA+ " ," + capacityB + ")";
            arrayS[i] = s;
        }
        System.out.println("      " + Arrays.toString(arrayS));
        for (int i = 0; i<arr.length;i++){
            int capacityA = i / (sizeBucketB + 1);
            int capacityB = i % (sizeBucketB + 1);
            String s = "("+ capacityA+ " ," + capacityB + ")";
            System.out.println(s + " " + Arrays.toString(arr[i]));
        }
    }
    public static void main(String[] args){
        BucketsProblem bucketsProblem = new BucketsProblem(3,5);
//        bucketsProblem.printM(bucketsProblem.trackMatrix);
//        bucketsProblem.printM(bucketsProblem.theTrac);
        System.out.println(bucketsProblem.theTrac[0][4]);
    }


}
