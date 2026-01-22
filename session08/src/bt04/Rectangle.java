package bt04;

public class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return String.format(
                "Rectangle(width=%.2f, height=%.2f, area=%.2f, perimeter=%.2f)",
                width, height, getArea(), getPerimeter()
        );
    }
}

