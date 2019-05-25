package amaragya.bahaso.Model;


import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private Object id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "Source{" +
                        "name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}