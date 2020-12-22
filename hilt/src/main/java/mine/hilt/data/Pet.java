package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2020/12/22.
 */
public class Pet {
    String name;

    @Inject
    public Pet() {
        System.out.println("~~Pet.Pet~~");
        this.name = "";
    }
}
