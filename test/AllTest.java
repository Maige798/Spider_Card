import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 6个测试类的合集
 *
 * @author 阮泽同
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        GetCanMoveCardsTest.class,
        GetMaxMoveCardsTest.class,
        GetMovePairsTest.class,
        IsFinishCardsTest.class,
        MoveSomeCardTest.class,
        PushCardsTest.class,
})
public class AllTest {
}
