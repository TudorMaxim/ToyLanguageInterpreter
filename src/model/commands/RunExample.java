package model.commands;

import controller.Controller;

public class RunExample extends Command {
    private Controller ctrl;
    public RunExample(String key, String description, Controller ctrl) {
        super(key, description);
        this.ctrl = ctrl;
    }
    @Override
    public void execute() {
        try {
            this.ctrl.allSteps();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
