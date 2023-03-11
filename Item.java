import com.google.gson.annotations.SerializedName;

public class Item {

  public String name;
  @SerializedName(value="V")
  public int Value;
  @SerializedName(value="W")
  public int Weight;

  public String getName() {
    return name;
  }

  public int getValue() {
    return Value;
  }

  public int getWeight() {
    return Weight;
  }

}
