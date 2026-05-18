package studios.homing.rebar.core.demo.direct;

import studios.homing.rebar.core.L1;

public record JueJu() implements L1<TangShi300> {
    public static final JueJu INSTANCE = new JueJu();
    @Override
    public TangShi300 getParent() {
        return TangShi300.INSTANCE;
    }
}
