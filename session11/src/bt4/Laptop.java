package bt4;

public class Laptop extends Device implements Connectable, Chargeable{
    public Laptop(int id, String name) {
        super(id, name);
    }

    @Override
    public void turnOn() {
        System.out.println(name + " đang bật");
    }

    @Override
    public void turnOff() {
        System.out.println(name + " đang tắt");
    }

    @Override
    public void connectWifi() {
        System.out.println(name + " đã kết nối WiFi");
    }

    @Override
    public void charge() {
        System.out.println(name + " đang sạc pin");
    }
}
