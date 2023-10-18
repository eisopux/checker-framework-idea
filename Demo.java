import org.checkerframework.checker.nullness.qual.*;

public class Demo {
    void foo() {
        @Nullable Object o = null;
        o.toString();
    }
}
