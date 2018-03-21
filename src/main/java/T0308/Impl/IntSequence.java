package T0308.Impl;

public interface IntSequence {

    boolean hasNext();//默认为public，
    // 实现类中，要显式声明为public。

    int next();
}
