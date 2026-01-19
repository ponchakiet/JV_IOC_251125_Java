public class bt02 {
    public static void main(String[] args) {
        int n = 1000000;
        long startTime = System.currentTimeMillis();
        String s = "Hello";
        for (int i = 0; i < n; i++) {
            s = s + " World";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Thời gian nối chuỗi bằng String: "
                + (endTime - startTime) + " ms");

        // 2. StringBuilder
        startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("Hello");
        for (int i = 0; i < n; i++) {
            sb.append(" World");
        }
        endTime = System.currentTimeMillis();
        System.out.println("Thời gian nối chuỗi bằng StringBuilder: "
                + (endTime - startTime) + " ms");

        // 3. StringBuffer
        startTime = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer("Hello");
        for (int i = 0; i < n; i++) {
            sbf.append(" World");
        }
        endTime = System.currentTimeMillis();
        System.out.println("Thời gian nối chuỗi bằng StringBuffer: "
                + (endTime - startTime) + " ms");
    }
}
