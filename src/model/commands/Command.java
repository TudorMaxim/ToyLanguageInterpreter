package model.commands;

public abstract class Command {
    private String description;
    private Integer key;

    public Command(Integer key, String description) {
        this.key = key;
        this.description = description;
    }

    public abstract void execute();

    public Integer getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
