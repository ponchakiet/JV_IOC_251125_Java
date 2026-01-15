import java.util.Scanner;

public class bt03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số phần tử của mảng: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập arr[" + i + "]: ");
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
        }
        System.out.println("\nMảng sau khi sắp xếp giảm dần:");
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.print("\nNhập số cần tìm: ");
        int key = sc.nextInt();
        int linearIndex = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == key) {
                linearIndex = i;
                break;
            }
        }
        int left = 0, right = n - 1;
        int binaryIndex = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == key) {
                binaryIndex = mid;
                break;
            } else if (key < arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("\nKết quả tìm kiếm:");
        if (linearIndex != -1) {
            System.out.println("Tìm kiếm tuyến tính: tìm thấy tại vị trí " + linearIndex);
        } else {
            System.out.println("Tìm kiếm tuyến tính: không tìm thấy");
        }

        if (binaryIndex != -1) {
            System.out.println("Tìm kiếm nhị phân: tìm thấy tại vị trí " + binaryIndex);
        } else {
            System.out.println("Tìm kiếm nhị phân: không tìm thấy");
        }
    }
}
