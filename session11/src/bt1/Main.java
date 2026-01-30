package bt1;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[2];

        shapes[0] = new Rectangle(4, 6);
        shapes[1] = new Circle(3);

        for (Shape shape : shapes) {
            shape.displayInfo();
            System.out.println("Diện tích: " + shape.getArea());
            System.out.println("Chu vi: " + shape.getPerimeter());

            if (shape instanceof Drawable) {
                Drawable d = (Drawable) shape;
                d.draw();
            }

            System.out.println("----------------------");
        }
    }
}
