package bt4;

public abstract class Device {
    protected int id;
    protected String name;

    public Device(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void turnOn();
    public abstract void turnOff();
}
