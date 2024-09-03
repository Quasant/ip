public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String desc, String startTime, String endTime) {
        super(desc);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s(from: %s to: %s)", super.toString(), startTime, endTime);
    }

    public String toSaveFormat() {
        return String.format("E_%s_%s_%s_%s", isDone ? "1" : "0", getDesc(), startTime, endTime);
    }
}
