import java.util.Scanner;

public class bt04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số phần tử của mảng: ");
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println("Mảng không có phần tử");
            return;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập arr[" + i + "]: ");
            arr[i] = sc.nextInt();
        }
        int[] result = new int[n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 0) {
                result[index++] = arr[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 != 0) {
                result[index++] = arr[i];
            }
        }
        for (int i = 0; i < n; i++) {
            arr[i] = result[i];
        }
        System.out.println("Mảng sau khi sắp xếp (chẵn trước, lẻ sau):");
        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
}
