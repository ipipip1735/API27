package mine.hilt.data;

/**
 * Created by Administrator on 2020/12/29 16:52.
 */
public class Sheep {
    public Grass grass;

    public Sheep(Grass grass) {
        System.out.println("~~Sheep.Sheep~~");
        this.grass = grass;
    }
}
