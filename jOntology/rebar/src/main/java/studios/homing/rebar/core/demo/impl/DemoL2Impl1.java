package studios.homing.rebar.core.demo.impl;

import studios.homing.rebar.core.demo.DemoL2;

public record DemoL2Impl1() implements DemoL2<DemoL1Impl1> {
    @Override
    public DemoL1Impl1 getParent() {
        return new DemoL1Impl1();
    }
}
