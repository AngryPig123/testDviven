package lazyEvaluation;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : lazyEvaluation
 * fileName       : DiscountPolicyFactory
 * author         : AngryPig123
 * date           : 26. 1. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 21.        AngryPig123       최초 생성
 */
public class DiscountPolicyFactory {

    public static List<DiscountPolicy> createPolicies(String... types) {
        if (types.length > 1) {
            throw new RuntimeException();
        }
        List<DiscountPolicy> discountPolicyList = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            String type = types[i];
            discountPolicyList.add(find(type));
        }
        return discountPolicyList;
    }

    private static DiscountPolicy find(String type) {
        if ("A".equals(type)) {
            return new ADiscountPolicy();
        } else if ("B".equals(type)) {
            return new BDiscountPolicy();
        } else {
            throw new RuntimeException();
        }
    }

}
