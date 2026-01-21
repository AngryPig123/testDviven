package com.testDriven.lazyEvaluation;

/**
 * packageName    : com.testDriven.lazyEvaluation
 * fileName       : Policy
 * author         : AngryPig123
 * date           : 26. 1. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 21.        AngryPig123       최초 생성
 */
public class APricePolicy implements PricePolicy {

    public long reduce(long price) {
        return price;
    }

}
