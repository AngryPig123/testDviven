package com.testDriven.lazyEvaluation;

/**
 * packageName    : com.testDriven.lazyEvaluation
 * fileName       : Discount
 * author         : AngryPig123
 * date           : 26. 1. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 21.        AngryPig123       최초 생성
 */
public interface DiscountPolicy {

    long discount(long price);

}
