package studios.homing.rebar.core.demo.direct;

import studios.homing.rebar.core.L2;

public record JueJu7() implements L2<JueJu> {
    @Override
    public JueJu getParent() {
        return JueJu.INSTANCE;
    }
}
