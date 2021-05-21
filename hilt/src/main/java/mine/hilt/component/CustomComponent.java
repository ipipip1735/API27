package mine.hilt.component;

import dagger.BindsInstance;
import dagger.hilt.DefineComponent;
import dagger.hilt.components.SingletonComponent;
import mine.hilt.data.Person;
import mine.hilt.data.Volleyball;

/**
 * Created by Administrator on 2021/5/15.
 */
@DefineComponent(parent = SingletonComponent.class)
public interface CustomComponent {
    @DefineComponent.Builder
    interface CustomComponentBuilder {
        CustomComponentBuilder setVolleyball(@BindsInstance Volleyball volleyball);
        CustomComponentBuilder setPerson(@BindsInstance Person person);
        CustomComponent build();
    }
}