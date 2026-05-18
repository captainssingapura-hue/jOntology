package studios.homing.rebar.core;

public interface WithParent<P extends Level> {
    P getParent();
}
