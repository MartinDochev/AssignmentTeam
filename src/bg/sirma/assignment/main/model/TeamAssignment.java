package bg.sirma.assignment.main.model;

import java.util.Objects;

public class TeamAssignment implements Comparable {
    private int taskId = 0;
    private int daysWorked = 0;
    private Staff firstMember = null;
    private Staff secondMember = null;

    public TeamAssignment(Staff firstMember, Staff secondMember, int daysWorked) {
        this.daysWorked = daysWorked;
        this.firstMember = firstMember;
        this.secondMember = secondMember;
        this.taskId = firstMember.getProjectId();
    }

    public Staff getFirstMember() {
        return firstMember;
    }

    public Staff getSecondMember() {
        return secondMember;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void addDaysWorked(int daysWorked) {
        this.daysWorked += daysWorked;
    }

    public boolean compareTeam(TeamAssignment otherTeam) {
        int thisEmployeeOne = this.getFirstMember().getEmployeeId();
        int thisEmployeeTwo = this.getSecondMember().getEmployeeId();
        int otherEmployeeOne = otherTeam.getFirstMember().getEmployeeId();
        int otherEmployeeTwo = otherTeam.getSecondMember().getEmployeeId();

        if ((thisEmployeeOne == otherEmployeeOne || thisEmployeeOne == otherEmployeeTwo) &&
                (thisEmployeeTwo == otherEmployeeOne || thisEmployeeTwo == otherEmployeeTwo)) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {

        if (this.getDaysWorked() > ((TeamAssignment) o).getDaysWorked()) {
            return -1;
        } else if (this.getDaysWorked() < ((TeamAssignment) o).getDaysWorked()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "TeamAssignment{" +
                "firstMember=" + this.firstMember +
                ", secondMember=" + this.secondMember +
                ", daysWorked=" + this.daysWorked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamAssignment that = (TeamAssignment) o;
        return taskId == that.taskId &&
                daysWorked == that.daysWorked &&
                Objects.equals(firstMember, that.firstMember) &&
                Objects.equals(secondMember, that.secondMember);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taskId, daysWorked, firstMember, secondMember);
    }
}
