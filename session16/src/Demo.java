public class Demo implements InterfaceMethod {
    @Override
    public void printf() {

    }

    @Override
    public void printColor() {
        System.out.println("Màu xanh");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.printColor();
        InterfaceMethod.printSize(); // gọi trực tiếp phương thức tĩnh
    }
}
