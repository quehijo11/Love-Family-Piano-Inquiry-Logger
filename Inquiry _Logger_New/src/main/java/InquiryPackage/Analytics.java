package InquiryPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Analytics extends JFrame implements ActionListener {

    Date startDate;
    Date endDate;
    int inquiries;
    int previewLessons;
    int enrollments;
    int discontinued;
    float percentage;

    static JTextField StartDate;
    static JTextField EndDate;



    public Analytics() {
        StartDate = new JTextField();
        EndDate = new JTextField();
    }

    public void run() {
        //Analytics window setup
        JFrame frame = new JFrame("Analytics");
        frame.setSize(500, 400);

        // Range warning
        JLabel labelWarning = new JLabel("WARNING: Max range for analytics is 55 entries");
        labelWarning.setBounds(12, 20, 350,20);
        frame.add(labelWarning);

        // Start Date
        JLabel labelStartDate = new JLabel("Start Date (mm/dd/yyyy): ");
        labelStartDate.setBounds(12, 50, 250,20);
        frame.add(labelStartDate);
        StartDate.setBounds(320, 50, 150,20);
        frame.add(StartDate);

        // End Date
        JLabel labelEndDate = new JLabel("End Date (mm/dd/yyyy): ");
        labelEndDate.setBounds(12, 80, 250,20);
        frame.add(labelEndDate);
        EndDate.setBounds(320, 80, 150,20);
        frame.add(EndDate);

        // Run/Submit
        JButton RunButton =new JButton("Run");
        RunButton.setBounds(338,110,130,30);
        frame.add(RunButton);
        RunButton.addActionListener(this);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
        try {
            startDate = formatter.parse(StartDate.getText());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        try {
            endDate = formatter.parse(EndDate.getText());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        if (command.equals("Run")) {

            DataStorage dataStorage = new DataStorage();
            try {
                dataStorage.run(startDate, endDate);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            inquiries = dataStorage.getInquiry();
            previewLessons = dataStorage.getPrevLesson();
            enrollments = dataStorage.getEnrollment();
            discontinued = dataStorage.getDiscontinue();
            percentage = dataStorage.getPercentage();

            JFrame analyticsFrame = new JFrame("Analytics Results");
            analyticsFrame.setSize(500, 650);
            JOptionPane.showMessageDialog(analyticsFrame, "Inquiries: " + inquiries + "\n" +
                            "Preview Lessons: " + previewLessons + "\n" +
                            "Enrollments: " + enrollments + "\n" +
                            "Discontinuations: " + discontinued + "\n" +
                            "Percentage of inquiries/preview lessons attended: " + String.format("%.2f", percentage) + "\n");
        }
    }
}


