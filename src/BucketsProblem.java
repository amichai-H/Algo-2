import java.util.Arrays;

public class BucketsProblem {
    int sizeBucketA;
    int sizeBucketB;
    boolean[][] neighborMatrix;
    public BucketsProblem(int sizeBucketA,int sizeBucketB){
        this.sizeBucketA = sizeBucketA;
        this.sizeBucketB = sizeBucketB;
        int m = sizeBucketA+1;
        int n = sizeBucketB+1;
        neighborMatrix = new boolean[n*m][n*m];
        createTable();
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
    public void printM(){
        String[] arrayS = new String[neighborMatrix.length];
        for (int i = 0; i<neighborMatrix.length;i++) {
            int capacityA = i / (sizeBucketB + 1);
            int capacityB = i % (sizeBucketB + 1);
            String s = "("+ capacityA+ " ," + capacityB + ")";
            arrayS[i] = s;
        }
        System.out.println("      " + Arrays.toString(arrayS));
        for (int i = 0; i<neighborMatrix.length;i++){
            int capacityA = i / (sizeBucketB + 1);
            int capacityB = i % (sizeBucketB + 1);
            String s = "("+ capacityA+ " ," + capacityB + ")";
            System.out.println(s + " " + Arrays.toString(neighborMatrix[i]));
        }
    }

    public static void main(String[] args){
        BucketsProblem bucketsProblem = new BucketsProblem(3,5);
        bucketsProblem.printM();
    }


}
