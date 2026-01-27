package bt04;

public class Car {
    int currentSpeed = 0;

    public void accelerate() {
        this.currentSpeed = this.currentSpeed + 10;
        System.out.println("Car accelerates by default: +10 km/h");
    }

    public void accelerate(int speed) {
        this.currentSpeed = this.currentSpeed + speed;
        System.out.println("Car accelerates by" + speed + " km/h");
    }

    public void accelerate(int speed, int seconds) {
        int increase = speed * seconds;
        this.currentSpeed = this.currentSpeed + increase;
        System.out.println("Car accelerates by" + increase + " km/h (speed x time)");
    }

    public void printStatus() {
        System.out.println(this.currentSpeed + " km/h");
    }
}
