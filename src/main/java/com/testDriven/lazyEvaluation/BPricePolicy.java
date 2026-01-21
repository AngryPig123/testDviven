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
public class BPricePolicy implements PricePolicy {

    public long reduce(long price) {
        long total = price % 100;
        if (total != 0) {
            return (price + 99) / 100 * 100;
        } else {
            return price;
        }
    }

}
