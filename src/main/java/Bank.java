import java.util.Hashtable;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : Bank
 * author         : AngryPig123
 * date           : 26. 1. 18.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 18.        AngryPig123       최초 생성
 */
public class Bank {

    private Hashtable<Pair, Integer> rates = new Hashtable<>();
    /*
        4) 환산은 Bank가 책임진다 (환율 테이블 필요)
            •	서로 다른 통화를 한 통화로 “줄이기(reduce)” 위해서는 환율(rate) 이 필요하다.
                    •	환율은 Bank(또는 그에 준하는 객체)가 알고/관리한다.
            •	예: Bank.addRate("CHF","USD", 2) → (책 예제에선 2:1 형태로 등장)
                    •	Bank.reduce(expression, "USD") 처럼 목표 통화(target currency) 를 지정해 결과를 얻는다
     */

    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    public void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), rate);
    }

    public int rate(String from, String to) {
        if (from.equals(to)) return 1;
        return rates.get(new Pair(from, to));
    }

    private static class Pair {

        private String from;
        private String to;

        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object object) {
            Pair pair = (Pair) object;
            return this.from.equals(pair.from) && this.to.equals(pair.to);
        }

        @Override
        public int hashCode() {
            return 0;
        }

    }

}
