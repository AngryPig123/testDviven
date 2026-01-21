package lazyEvaluation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * packageName    : lazyEvaluation
 * fileName       : LazyEvaluationTest
 * author         : AngryPig123
 * date           : 26. 1. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 21.        AngryPig123       최초 생성
 */
public class LazyEvaluationTest {

    @Test
    void testItem() {
        Item item = new Item("이름", 10_000);
        Assertions.assertTrue(!item.getName().isEmpty());
        Assertions.assertTrue(Objects.nonNull(item.getPrice()));
    }

    @Test
    void testBucket() {
        Bucket bucket = new Bucket();
        Item item = new Item("이름", 10_000);
        int quantity = 5;
        bucket.addItem(item, quantity);
        Assertions.assertEquals(quantity, bucket.getQuantity());
    }

    @Test
    void testAdditionBucketItemPricePolicyTypeA() {
        int testPrice = 200;
        int testQuantity = 10;
        Item item = new Item("a_item", testPrice);
        Bucket bucket = new Bucket();
        bucket.addItem(item, testQuantity);
        PricePolicy testAPolicy = PricePolicyFactory.createPolicy("A");
        long price = testAPolicy.reduce(bucket.total());
        Assertions.assertEquals(
                (long) testPrice * testQuantity, price
        );
    }

    @Test
    void testAdditionBucketItemPricePolicyTypeB() {
        int testPrice = 199;
        int testQuantity = 10;
        Item item = new Item("a_item", testPrice);
        Bucket bucket = new Bucket();
        bucket.addItem(item, testQuantity);
        PricePolicy testBPolicy = PricePolicyFactory.createPolicy("B");
        long price = testBPolicy.reduce(bucket.total());
        Assertions.assertEquals(
                2000, price
        );
    }

    @Test
    void testDiscount() {
        Assertions.assertDoesNotThrow(() -> {
            DiscountPolicyFactory.createPolicies("A");
            DiscountPolicyFactory.createPolicies("B");
        });
    }

    @Test
    void testADiscountPolicy() {

        int testPrice = 200;
        int testQuantity = 10;

        Item item = new Item("a_item", testPrice);
        Bucket bucket = new Bucket();
        bucket.addItem(item, testQuantity);

        AtomicLong price = new AtomicLong(bucket.total());

        List<DiscountPolicy> discountPolicies = DiscountPolicyFactory.createPolicies("A");
        discountPolicies.forEach(policy -> {
            price.set(policy.discount(price.get()));
        });

        Assertions.assertEquals(1000, price.get());

    }

    @Test
    void testBDiscountPolicy() {

        int testPrice = 200;
        int testQuantity = 10;

        Item item = new Item("a_item", testPrice);
        Bucket bucket = new Bucket();
        bucket.addItem(item, testQuantity);

        AtomicLong price = new AtomicLong(bucket.total());

        List<DiscountPolicy> discountPolicies = DiscountPolicyFactory.createPolicies("B");
        discountPolicies.forEach(policy -> {
            price.set(policy.discount(price.get()));
        });

        Assertions.assertEquals(1800, price.get());

    }

    @Test
    void testAPricePolicyAndADiscountPolicy() {

        int testPrice = 199;
        int testQuantity = 10;

        Item item = new Item("a_item", testPrice);
        Bucket bucket = new Bucket();
        bucket.addItem(item, testQuantity);

        AtomicLong price = new AtomicLong(bucket.total());

        List<DiscountPolicy> discountPolicies = DiscountPolicyFactory.createPolicies("A");
        discountPolicies.forEach(policy -> {
            price.set(policy.discount(price.get()));
        });

        Assertions.assertEquals(990, price.get());

        PricePolicy aPricePolicy = PricePolicyFactory.createPolicy("A");
        long result = aPricePolicy.reduce(price.get());

        Assertions.assertEquals(990, result);

    }

    @Test
    void testBPricePolicyAndADiscountPolicy() {

        int testPrice = 199;
        int testQuantity = 10;

        Item item = new Item("a_item", testPrice);
        Bucket bucket = new Bucket();
        bucket.addItem(item, testQuantity);

        AtomicLong price = new AtomicLong(bucket.total());

        List<DiscountPolicy> discountPolicies = DiscountPolicyFactory.createPolicies("A");
        discountPolicies.forEach(policy -> {
            price.set(policy.discount(price.get()));
        });

        Assertions.assertEquals(990, price.get());

        PricePolicy aPricePolicy = PricePolicyFactory.createPolicy("B");
        long result = aPricePolicy.reduce(price.get());

        Assertions.assertEquals(1000, result);

    }

}
