package bg.sirma.assignment.main.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Task {

    private int taskId = 0;
    private List<Staff> staffsWorked = new LinkedList<>();
    private HashSet<TeamAssignment> allTeams = new HashSet<>();
    private TeamAssignment bestTeam = null;


    public Task(Staff staff) {
        this.taskId = staff.getProjectId();
        this.staffsWorked.add(staff);
    }

    public int getTaskId() {
        return this.taskId;
    }

    public List<Staff> getStaffsWorked() {
        return this.staffsWorked;
    }

    public TeamAssignment getBestTeam() {
        Staff staffOne = null;
        Staff staffTwo = null;
        int daysWorked = 0;
        for (Staff staff : this.staffsWorked) {
            for (Staff s : this.staffsWorked) {
                if (staff.getEmployeeId() == s.getEmployeeId()) {
                    continue;
                }

                int days = staff.daysWorkedInTeam(s);
                if (days > daysWorked) {
                    daysWorked = days;
                    staffOne = staff;
                    staffTwo = s;

                    this.allTeams.add(new TeamAssignment(staffOne, s, daysWorked));
                }
            }
        }

        if (staffOne != null && staffTwo != null && daysWorked > 0) {
            this.bestTeam = new TeamAssignment(staffOne, staffTwo, daysWorked);
        }

        return this.bestTeam;
    }

    public HashSet<TeamAssignment> getAllTeams() {
        return this.allTeams;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + this.taskId +
                ", staffsWorked=" + this.staffsWorked +
                '}';
    }
}
