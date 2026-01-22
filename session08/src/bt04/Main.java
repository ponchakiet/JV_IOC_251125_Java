package bt04;

public class Main {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(3, 4);
        Rectangle r2 = new Rectangle(5, 2);
        Rectangle r3 = new Rectangle(4.5, 3.5);

        Rectangle[] rectangles = { r1, r2, r3 };
        System.out.println("Thông tin các hình chữ nhật:");
        for (Rectangle r : rectangles) {
            System.out.println(r);
        }

        double maxArea = rectangles[0].getArea();
        int countMax = 1;
        for (int i = 1; i < rectangles.length; i++) {
            double area = rectangles[i].getArea();
            if (area > maxArea) {
                maxArea = area;
                countMax = 1;
            } else if (area == maxArea) {
                countMax++;
            }
        }

        System.out.println("\nDiện tích lớn nhất: " + maxArea);

        if (countMax > 1) {
            System.out.println("Có " + countMax + " hình chữ nhật có diện tích lớn nhất.");
        } else {
            System.out.println("Hình chữ nhật có diện tích lớn nhất:");
            for (Rectangle r : rectangles) {
                if (r.getArea() == maxArea) {
                    System.out.println(r);
                    break;
                }
            }
        }
    }
}
