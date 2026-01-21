package lazyEvaluation;

/**
 * packageName    : lazyEvaluation
 * fileName       : ADicsountPolicy
 * author         : AngryPig123
 * date           : 26. 1. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 21.        AngryPig123       최초 생성
 */
public class ADiscountPolicy implements DiscountPolicy {

    @Override
    public long discount(long price) {
        if (price < 1000) {
            return price;
        } else {
            return price - 1000;
        }
    }

}
