import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// Specifies this class as a test suite
@Suite

// Add other testclasses here
@SelectClasses({
        AccountTest.class
})
public class AllTestsSuite {
    // No code is needed here; the annotations define the suite
}
