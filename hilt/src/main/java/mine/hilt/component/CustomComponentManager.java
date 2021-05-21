package mine.hilt.component;

import javax.inject.Inject;

import mine.hilt.data.Person;
import mine.hilt.data.Volleyball;

/**
 * Created by Administrator on 2021/5/15.
 */
public class CustomComponentManager {
    public CustomComponent customComponent;

    @Inject
    CustomComponentManager(CustomComponent.CustomComponentBuilder componentBuilder, Volleyball volleyball) {
        System.out.println("CustomComponentManager.CustomComponentManager");
        System.out.println("componentBuilder = " + componentBuilder);

        customComponent = componentBuilder
                .setVolleyball(volleyball)
                .setPerson(new Person())
                .build();
    }
}
