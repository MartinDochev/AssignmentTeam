package bg.sirma.assignment.main.data;

import bg.sirma.assignment.main.model.Staff;
import bg.sirma.assignment.main.model.Task;
import bg.sirma.assignment.main.model.TeamAssignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StaffData {

    private String filePath = null;

    private List<Staff> staffData = null;
    private List<Task> tasks = new ArrayList<>();
    private List<TeamAssignment> bestTeams = new ArrayList<>();
    private TeamAssignment bestTeam = null;

    public StaffData(String filePath) {
        this.filePath = filePath;
    }

    public void processData() {
        try (FileReader file = new FileReader(this.filePath);
             BufferedReader reader = new BufferedReader(file)) {

            createLocalePattern(reader);


            this.staffData = new ArrayList<>();
            reader.lines().forEach(this::addEmployeeTask);

            setTaskBestTeam();

            bestTeamSearch();
            this.bestTeam = this.bestTeams.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createLocalePattern(BufferedReader reader) {
        System.out.println("algorithm to create date pattern");
    }

    private void addEmployeeTask(String line) {
        if (line.trim().length() == 0) {
            return;
        }
        Staff staffTask = new Staff(line);

        Staff presentedStaff = getStaffByd(staffTask.getEmployeeId());

        if (presentedStaff != null) {
            presentedStaff.addEmployeeTask(staffTask);
        } else {
            this.staffData.add(staffTask);
        }

        addTask(staffTask);
    }

    private Staff getStaffByd(int id) {
        return this.staffData.stream().findAny().filter(staff1 -> staff1.getEmployeeId() == id).orElse(null);
    }

    private void addTask(Staff staff) {
        if (!containsTask(staff.getProjectId())) {
            this.tasks.add(new Task(staff));
        } else {
            getTaskById(staff.getProjectId()).getStaffsWorked().add(staff);
        }
    }

    private boolean containsTask(int id) {
        return this.tasks.stream().anyMatch(o -> o.getTaskId() == id);
    }

    private Task getTaskById(int taskId) {
        return this.tasks.stream().filter(t -> t.getTaskId() == taskId).findAny().orElse(null);
    }

    private void setTaskBestTeam() {
        for (Task task : this.tasks) {
            task.getBestTeam();
        }
    }

    private void bestTeamSearch() {
        for (Task task : this.tasks) {
            if (task.getBestTeam() == null) {
                continue;
            }
            if (containsTeam(task.getBestTeam())) {
                getTeamByStaff(task.getBestTeam()).addDaysWorked(task.getBestTeam().getDaysWorked());
            } else {
                this.bestTeams.add(task.getBestTeam());
            }
        }

        Collections.sort(this.bestTeams);
    }

    private boolean containsTeam(TeamAssignment assignmentTeam) {
        return this.bestTeams.stream().anyMatch(t -> t.compareTeam(assignmentTeam));
    }

    private TeamAssignment getTeamByStaff(TeamAssignment teamAssignment) {
        return this.bestTeams.stream().filter(t -> t.compareTeam(teamAssignment)).findFirst().orElse(null);
    }

    public void printBestTeam() {
        System.out.println(String.format("Best team is: %d and %d. They had worked %d days in a team!",
                this.bestTeam.getFirstMember().getEmployeeId(),
                this.bestTeam.getSecondMember().getEmployeeId(),
                this.bestTeam.getDaysWorked()));
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}
