
import java.util.List;

/**
 * Created by mac on 18/1/22.
 */

public class ArrayEntity {
    private String name;
    private List<String> items;

    public ArrayEntity() {
        super();
    }

    public ArrayEntity(String name) {
        super();
        this.name = name;
    }

    public ArrayEntity(String name, List<String> items) {
        super();
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
