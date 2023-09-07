package hexlet.code;


public final class Diff {
    private final String key;
    private final String action;
    private final Object val1;
    private final Object val2;

    public String getAction() {
        return this.action;
    }

    public String getKey() {
        return this.key;
    }

    public Object getVal1() {
        return this.val1;
    }

    public Object getVal2() {
        return this.val2;
    }

    public Diff(String keyIn, String actionIn, Object val1In, Object val2In) {
        this.key = keyIn;
        this.action = actionIn;
        this.val1 = val1In;
        this.val2 = val2In;
    }
}

