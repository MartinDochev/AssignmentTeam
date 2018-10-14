package bg.sirma.assignment.main.model;

import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Staff {

    private int employeeId = 0;
    private int projectId = 0;
    private LocalDate dateFrom = null;
    private LocalDate dateTo = null;
    private Map<Integer, Integer> taskTimeMap = new HashMap<>();

    public Staff(String staffInfo) {
        setStaffProperties(staffInfo);

    }


    private void setStaffProperties(String staffInfo) {
        String[] properties = staffInfo.split(", ");
        setEmployeeId(Integer.parseInt(properties[0]));
        setProjectId(Integer.parseInt(properties[1]));
        setDateFrom(properties[2]);
        setDateTo(properties[3]);
        setTaskWorkedDays();
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return this.projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDateFrom() {
        return this.dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = LocalDate.parse(dateFrom);
    }

    public LocalDate getDateTo() {
        return this.dateTo;
    }

    public void setDateTo(String dateTo) {
        if (dateTo.equalsIgnoreCase("null"))
            this.dateTo = LocalDate.now();
        else
            this.dateTo = LocalDate.parse(dateTo);
    }


    public void setTaskWorkedDays() {
        this.taskTimeMap.put(this.getProjectId(), getDaysInProject());
    }

    public void addEmployeeTask(Staff employee) {
        if (!this.taskTimeMap.containsKey(employee.getProjectId())) {
            this.taskTimeMap.put(employee.getProjectId(), employee.getDaysInProject());
        } else {
            this.taskTimeMap.put(employee.getProjectId(), this.taskTimeMap.get(employee.getProjectId()) + getDaysInProject());
        }
    }

    public int getDaysInProject() {
        return (int) this.dateFrom.until(dateTo, ChronoUnit.DAYS);
    }

    private int getDaysInterval(LocalDate dateFrom, LocalDate dateTo) {
        return (int) dateFrom.until(dateTo, ChronoUnit.DAYS);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "employeeId=" + this.employeeId +
                ", projectId=" + this.projectId +
                ", dateFrom=" + this.dateFrom +
                ", dateTo=" + this.dateTo +
                ", taskTimeMap=" + this.taskTimeMap +
                '}';
    }

    public int daysWorkedInTeam(Staff other) {

        LocalDate localFrom = null;
        LocalDate localTo = null;

        if (this.dateFrom.compareTo(other.getDateFrom()) > 0) {
            localFrom = this.dateFrom;
        } else {
            localFrom = other.getDateFrom();
        }

        if (this.dateTo.compareTo(other.getDateTo()) > 0) {
            localTo = other.getDateTo();
        } else {
            localTo = this.dateTo;
        }
        return getDaysInterval(localFrom, localTo);
    }
}
