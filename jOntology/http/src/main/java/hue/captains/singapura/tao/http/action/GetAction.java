package hue.captains.singapura.tao.http.action;

public interface GetAction<REQ, QP extends Param._QueryString, HP extends Param._Header, R> extends Action<REQ, HP> {

    ParamMarshaller._QueryString<REQ, QP> queryStrMarshaller();

    /**
     * For GET, just support header and query string
     * @param qp
     * @param hp
     * @return
     */
    R execute(QP qp, HP hp);
}
