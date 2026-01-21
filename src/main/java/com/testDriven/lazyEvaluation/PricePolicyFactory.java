package com.testDriven.lazyEvaluation;

/**
 * packageName    : com.testDriven.lazyEvaluation
 * fileName       : PolicyFactory
 * author         : AngryPig123
 * date           : 26. 1. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 21.        AngryPig123       최초 생성
 */
public class PricePolicyFactory {

    public static PricePolicy createPolicy(String type) {
        if ("A".equals(type)) {
            return new APricePolicy();
        } else if ("B".equals(type)) {
            return new BPricePolicy();
        } else {
            throw new RuntimeException();
        }
    }

}
