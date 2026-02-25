public interface InterfaceMethod {
    void printf(); //trừu tượng

    //default: phương thức mặc định
    default void printColor() {
        System.out.println("Màu đỏ");
    }
    //static: phương thức tĩnh không cho ghi đè
    static void printSize() {
        System.out.println("XXL");
    }
}
