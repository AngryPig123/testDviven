package lazyEvaluation;

/**
 * packageName    : lazyEvaluation
 * fileName       : Item
 * author         : AngryPig123
 * date           : 26. 1. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 21.        AngryPig123       최초 생성
 */
public class Item {

    private final String name;
    private final Integer price;

    public Item(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

}
