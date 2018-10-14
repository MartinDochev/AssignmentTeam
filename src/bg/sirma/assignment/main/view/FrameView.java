package bg.sirma.assignment.main.view;

import bg.sirma.assignment.main.data.StaffData;
import bg.sirma.assignment.main.model.Task;
import bg.sirma.assignment.main.model.TeamAssignment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FrameView {

    private StaffData data = null;

    private String pathToFile = null;

    private JFrame frame =  null;
    private JTable table = null;
    private JPanel mainPanel = null;

    public FrameView() {
        initFrame();
    }

    private void initFrame(){
        this.frame = new JFrame("Assignment Team Longest Period");
        this.frame.setSize(600, 600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setAlwaysOnTop(true);

        JPanel filePanel = new JPanel(new FlowLayout());
        filePanel.setSize(new Dimension(300, 100));

        JLabel fileLabel = new JLabel("Select file:");
        JButton fileChoseButton = new JButton("Choose File");
        fileChoseButton.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("File selection");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            if (chooser.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
                this.pathToFile = chooser.getSelectedFile().getAbsolutePath();

                createStaffData();
                showTeamsInGrid();
            } else {
                JOptionPane.showMessageDialog(frame,
                        "You must specify path to the file!",
                        "Warning!",
                        JOptionPane.WARNING_MESSAGE);
            }
        });


        mainPanel= new JPanel(new BorderLayout());

        filePanel.add(fileLabel);
        filePanel.add(fileChoseButton);

        mainPanel.add(filePanel, BorderLayout.NORTH);
        this.frame.add(mainPanel);
        frame.setVisible(true);

    }

    private void createStaffData() {
        this.data = new StaffData(this.pathToFile);
        this.data.processData();
        this.data.printBestTeam();
    }

    private void showTeamsInGrid() {
        String[] columns = new String[] {
                "Employee ID #1", "Employee ID #2", "Project ID", "Days worked"
        };

        table = new JTable(getTableData(), columns);

        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.revalidate();
    }

    private Object[][] getTableData() {
        java.util.List<Object[]> listData = new ArrayList<>();


        for (Task task : this.data.getTasks()) {

            System.out.println(task.getAllTeams().size());
            for (TeamAssignment team : task.getAllTeams()) {
                listData.add(new Object[]{
                        team.getFirstMember().getEmployeeId(),
                        team.getSecondMember().getEmployeeId(),
                        team.getFirstMember().getProjectId(),
                        team.getDaysWorked()
                });
            }
        }

        Object[][] result = new Object[listData.size()][];
        result = listData.toArray(result);
        return result;
    }
}
