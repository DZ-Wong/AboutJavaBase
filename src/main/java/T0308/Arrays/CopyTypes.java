package T0308.Arrays;

/**
 * Created by vip on 2018/4/3.
 */
public class CopyTypes {
    public static int[][] A = {
        {1, 2, 3, 4},
        {5, 6, 7, 8, 9},
        {10, 11, 12,}
    };

    /**
     * 整形数组复制。
     * @param A
     */
    public static void arrayPrint(int[][] A) {

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.printf("%-4d  ", A[i][j]);
            }
            System.out.println();
        }
    }

    public static void arrayCopy() {
        int[][] B = new int[4][10];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                B[i][j] = A[i][j];
             }
        }
        arrayPrint(B);
        System.out.println(B.length);

    }



    /**
     * 使用循环语句遍历复制。
     * @param array
     * @return
     */
    public String[][] copyArray1(String[][] array){
        String[][] copyArray=new String[array.length][];
        for(int i=0;i<array.length;i++){
            copyArray[i]=new String[array[i].length];
            for(int j=0;j<array[i].length;j++){
                copyArray[i][j]=array[i][j];
            }
        }
        return copyArray;
    }

    /**
     * System.arraycopy。
     * @param array
     * @return
     */
    public String[][] copyArray2(String[][] array){
        String[][] copyArray=new String[array.length][];
        for(int i=0;i<array.length;i++){
            copyArray[i]=new String[array[i].length];
            System.arraycopy(array[i], 0, array[i], 0, copyArray[i].length);
        }
        return array;
    }

    public static void main(String[] args) {
        arrayPrint(A);
        arrayCopy();
    }
}
