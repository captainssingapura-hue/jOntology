package studios.homing.rebar.core.demo.impl;

import studios.homing.rebar.core.demo.DemoL1;

public record DemoL1Impl2() implements DemoL1<DemoL0Impl> {
    @Override
    public DemoL0Impl getParent() {
        return new DemoL0Impl();
    }
}
