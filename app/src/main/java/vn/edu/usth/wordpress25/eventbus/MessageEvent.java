package vn.edu.usth.wordpress25.eventbus;


import vn.edu.usth.wordpress25.model.ChooseTopic;

public class MessageEvent {
    public static final String CHOOSED_THEME = "CHOOSED_THEME";
    public static final String DELETED_THEME = "DELETED_THEME";
    public static final String DISMISS_FILTER_BOTTOM_SHEET = "DISMISS_FILTER_BOTTOM_SHEET";
    private String msg;
    private ChooseTopic chooseTopic = null;
    public MessageEvent(String msg) {
        this.msg = msg;
    }

    public MessageEvent(String msg, ChooseTopic chooseTopic) {
        this.msg = msg;
        this.chooseTopic = chooseTopic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ChooseTopic getChooseTopic() {
        return chooseTopic;
    }

    public void setChooseTopic(ChooseTopic chooseTopic) {
        this.chooseTopic = chooseTopic;
    }

}
