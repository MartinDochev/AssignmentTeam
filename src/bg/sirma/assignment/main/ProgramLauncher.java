package bg.sirma.assignment.main;

import bg.sirma.assignment.main.data.StaffData;
import bg.sirma.assignment.main.view.FrameView;

public class ProgramLauncher {

    private String filePath = null;

    public ProgramLauncher(String filePath) {
        this.filePath = filePath;
    }

    public void launchProgramMode() {
        StaffData data = new StaffData(this.filePath);
        if (this.filePath == null || this.filePath.equalsIgnoreCase("")) {
            new FrameView();
        } else {
            data.processData();
            data.printBestTeam();
        }
    }
}
