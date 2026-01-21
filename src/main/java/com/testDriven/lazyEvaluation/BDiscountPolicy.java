package com.testDriven.lazyEvaluation;

/**
 * packageName    : com.testDriven.lazyEvaluation
 * fileName       : BDiscountPolicy
 * author         : AngryPig123
 * date           : 26. 1. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 21.        AngryPig123       최초 생성
 */
public class BDiscountPolicy implements DiscountPolicy {

    @Override
    public long discount(long price) {
        return (long) (price * 0.9);
    }


}
