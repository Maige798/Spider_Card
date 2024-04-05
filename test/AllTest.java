import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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
