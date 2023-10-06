package vn.edu.usth.wordpress25.eventbus;


import vn.edu.usth.wordpress25.model.ChooseTopic;
import vn.edu.usth.wordpress25.model.Topic;

public class MessageEvent {
    public static final String CHOOSED_SITE = "CHOOSED_SITE";
    public static final String DELETED_THEME = "DELETED_THEME";
    public static final String DISMISS_FILTER_BOTTOM_SHEET = "DISMISS_FILTER_BOTTOM_SHEET";
    public static final String START_FILTER = "START_FILTER";
    public static final String END_FILTER = "END_FILTER";

    private int indexFilter = -1;

    private Topic topic = null;

    private String msg;
    private ChooseTopic chooseTopic = null;
    public MessageEvent(String msg) {
        this.msg = msg;
    }

    public MessageEvent(String msg, ChooseTopic chooseTopic) {
        this.msg = msg;
        this.chooseTopic = chooseTopic;
    }

    public MessageEvent(String msg,Topic topic, int indexFilter) {
        this.msg = msg;
        this.topic = topic;
        this.indexFilter = indexFilter;
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

    public int getIndexFilter() {
        return indexFilter;
    }

    public Topic getTopic() {
        return topic;
    }

}
