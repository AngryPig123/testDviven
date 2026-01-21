package money;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : money.Expression
 * author         : AngryPig123
 * date           : 26. 1. 18.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 18.        AngryPig123       최초 생성
 */
public interface Expression {
    Money reduce(Bank bank, String to);

    Expression plus(Expression addend);

    Expression times(int multiplier);

}
