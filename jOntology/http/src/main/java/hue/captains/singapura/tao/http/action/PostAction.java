package hue.captains.singapura.tao.http.action;

/**
 * For a regular post action
 * @param <REQ>
 * @param <PP>
 * @param <HP>
 * @param <R>
 */
public interface PostAction<REQ, PP extends Param._Post, HP extends Param._Header, R> {
    ParamMarshaller._Post<REQ, PP> postMarshaller();
    R exectue(PP param, HP queryParam);
}
