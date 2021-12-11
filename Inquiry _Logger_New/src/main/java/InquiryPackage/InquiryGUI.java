package InquiryPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class InquiryGUI extends JFrame implements ActionListener {
    String[] contactMethodList = {"Other", "Email", "Social Media", "Text", "Phone"}; //all the contact methods

    int textX;
    int labelX;
    int labelWidth;
    int textWidth;
    int rowY;

    static JTextField FirstName;
    static JTextField LastName;
    static JTextField StudentName;
    static JTextField Email;
    static JTextField Phone;
    static JCheckBox PianoCheck;
    static JCheckBox GuitarCheck;
    static JCheckBox VoiceCheck;
    static JCheckBox ViolinCheck;
    static JCheckBox UkuleleCheck;
    JComboBox ContactMethods;
    static JCheckBox PreviewInvite;
    static JTextField PreviewLesson;
    static JCheckBox PreviewAttended;
    static JCheckBox EnrollInvite;
    static JCheckBox Enrolled;
    static JTextField EnrollDate;
    static JTextField PreferredContact;
    static JTextArea Notes;

    public InquiryGUI() {
        textX = 320;
        labelX = 12;
        labelWidth = 250;
        textWidth = 150;
        rowY = 50;

        FirstName = new JTextField();
        LastName = new JTextField();
        StudentName = new JTextField();
        Email = new JTextField();
        Phone = new JTextField();
        PianoCheck = new JCheckBox("Piano");
        GuitarCheck = new JCheckBox("Guitar");
        VoiceCheck = new JCheckBox("Voice");
        ViolinCheck = new JCheckBox("Violin");
        UkuleleCheck = new JCheckBox("Ukulele");
        ContactMethods = new JComboBox(contactMethodList);
        PreviewInvite = new JCheckBox();
        PreviewLesson = new JTextField();
        PreviewAttended = new JCheckBox();
        EnrollInvite = new JCheckBox();
        Enrolled = new JCheckBox();
        EnrollDate = new JTextField();
        PreferredContact = new JTextField();
        Notes = new JTextArea(textWidth,100);
    }

    public void run() {

        //inquiry submission window setup
        JFrame frame=new JFrame("InquiryGUI");
        frame.setSize(500, 650);

        //First Name
        JLabel labelFirstName = new JLabel("First Name: ");
        labelFirstName.setBounds(labelX,rowY, labelWidth,20);
        frame.add(labelFirstName);
        FirstName.setBounds(textX,rowY, textWidth,20);
        frame.add(FirstName);

        //Last Name
        JLabel labelLastName = new JLabel("Last Name: ");
        labelLastName.setBounds(labelX,rowY += 25, labelWidth,20);
        frame.add(labelLastName);
        LastName.setBounds(textX,rowY, textWidth,20);
        frame.add(LastName);

        //Student Name
        JLabel labelStudentName = new JLabel("Student Name: ");
        labelStudentName.setBounds(labelX,rowY += 25, labelWidth,20);
        frame.add(labelStudentName);
        StudentName.setBounds(textX,rowY, textWidth,20);
        frame.add(StudentName);

        // Email
        JLabel labelEmail = new JLabel("Email: ");
        labelEmail.setBounds(labelX,rowY += 25, labelWidth,20);
        frame.add(labelEmail);
        Email.setBounds(textX,rowY, textWidth,20);
        frame.add(Email);

        // Phone
        JLabel labelPhone = new JLabel("Phone: ");
        labelPhone.setBounds(labelX,rowY += 25, labelWidth,20);
        frame.add(labelPhone);
        Phone.setBounds(textX,rowY, textWidth,20);
        frame.add(Phone);

        // Instrument interest
        JLabel labelInstrument = new JLabel("Instrument Interest: ");
        labelInstrument.setBounds(labelX, rowY += 25, labelWidth,20);
        frame.add(labelInstrument);
        PianoCheck.setBounds(textX, rowY, 75,20);
        frame.add(PianoCheck);

        //final JCheckBox GuitarCheck = new JCheckBox("Guitar");
        GuitarCheck.setBounds(textX + 75, rowY, 75,20);
        frame.add(GuitarCheck);

        //final JCheckBox VoiceCheck = new JCheckBox("Voice");
        VoiceCheck.setBounds(textX, rowY += 25, 75,20);
        frame.add(VoiceCheck);

        //final JCheckBox ViolinCheck = new JCheckBox("Violin");
        ViolinCheck.setBounds(textX + 75, rowY, 75,20);
        frame.add(ViolinCheck);

        //final JCheckBox UkuleleCheck = new JCheckBox("Ukulele");
        UkuleleCheck.setBounds(textX + 75, rowY + 25, 75,20);
        frame.add(UkuleleCheck);

        // Contacted Method
        JLabel labelContactMethod = new JLabel("Contacted Method: ");
        labelContactMethod.setBounds(labelX,rowY += 55, labelWidth,20);
        frame.add(labelContactMethod);
        ContactMethods.setSelectedIndex(4);
        ContactMethods.setBounds(textX,rowY, textWidth,20);
        frame.add(ContactMethods);

        // Invited to Preview Lesson
        JLabel labelPreviewInvite = new JLabel("Invited to Preview Lesson? ");
        labelPreviewInvite.setBounds(labelX,rowY += 25, labelWidth,20);
        frame.add(labelPreviewInvite);

        //final JCheckBox PreviewInvite = new JCheckBox();
        PreviewInvite.setBounds(textX,rowY, 150,20);
        frame.add(PreviewInvite);

        // Preview Lesson Date
        JLabel labelPreviewLesson = new JLabel("Preview Lesson Date: ");
        labelPreviewLesson.setBounds(labelX,rowY += 25, labelWidth,20);
        frame.add(labelPreviewLesson);

        //final JTextField PreviewLesson = new JTextField();
        PreviewLesson.setBounds(textX,rowY, textWidth,20);
        frame.add(PreviewLesson);

        // Showed up for preview lesson?
        JLabel labelPreviewAttended = new JLabel("Showed up for preview lesson? ");
        labelPreviewAttended.setBounds(labelX,rowY += 25, labelWidth,20);
        frame.add(labelPreviewAttended);

        //final JCheckBox PreviewAttended = new JCheckBox();
        PreviewAttended.setBounds(textX,rowY, 150,20);
        frame.add(PreviewAttended);

        //Invited to enroll
        JLabel labelEnrollInvite = new JLabel("Invited to enroll ");
        labelEnrollInvite.setBounds(labelX,rowY += 25, labelWidth,20);
        frame.add(labelEnrollInvite);

        //final JCheckBox EnrollInvite = new JCheckBox();
        EnrollInvite.setBounds(textX,rowY, 150,20);
        frame.add(EnrollInvite);

        // Enrolled
        JLabel labelEnrolled = new JLabel("Enrolled ");
        labelEnrolled.setBounds(labelX,rowY += 25, labelWidth,20);
        frame.add(labelEnrolled);

        //final JCheckBox Enrolled = new JCheckBox();
        Enrolled.setBounds(textX,rowY, 150,20);
        frame.add(Enrolled);

        // Enrollment Date
        JLabel labelEnrollDate = new JLabel("Enrollment Date: ");
        labelEnrollDate.setBounds(labelX,rowY += 25, labelWidth,20);
        frame.add(labelEnrollDate);
        EnrollDate.setBounds(textX,rowY, textWidth,20);
        frame.add(EnrollDate);

        // Preferred Contact
        JLabel labelPreferredContact = new JLabel("Preferred Contact Method: ");
        labelPreferredContact.setBounds(labelX,rowY += 25, labelWidth,20);
        frame.add(labelPreferredContact);
        PreferredContact.setBounds(textX,rowY, textWidth,20);
        frame.add(PreferredContact);

        // Notes
        JLabel labelNotes = new JLabel("Notes: ");
        labelNotes.setBounds(labelX,rowY += 25, labelWidth,20);
        frame.add(labelNotes);

        //final JTextArea Notes = new JTextArea(textWidth,100);
        JScrollPane scrollPane = new JScrollPane(Notes);
        Notes.setEditable(true);
        Notes.setBounds(textX,rowY, textWidth,100);
        frame.add(Notes);

        // Submit
        JButton button1 =new JButton("Submit");
        button1.setBounds(338,575,130,30);
        frame.add(button1);
        button1.addActionListener(this);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent i) {
        String instruments = "";
        String previewInvitedString = "no";
        String previewAttendedString = "no";
        String enrollInviteString = "no";
        String enrolledString = "no";

        String command = i.getActionCommand();

        String firstName = FirstName.getText();
        String lastName = LastName.getText();
        String studentName = StudentName.getText();
        String email = Email.getText();
        String phone = Phone.getText();
        String previewLesson = PreviewLesson.getText();
        String enrollDate = EnrollDate.getText();
        String preferredContact = PreferredContact.getText();
        String notes = Notes.getText();

        boolean pianoCheck = PianoCheck.isSelected();
        boolean guitarCheck = GuitarCheck.isSelected();
        boolean voiceCheck = VoiceCheck.isSelected();
        boolean violinCheck = ViolinCheck.isSelected();
        boolean ukuleleCheck = UkuleleCheck.isSelected();
        boolean previewInvite = PreviewInvite.isSelected();

        if (previewInvite == true) {
            previewInvitedString = "yes";
        }
        boolean previewAttended = PreviewAttended.isSelected();
        if (previewAttended == true) {
            previewAttendedString = "yes";
        }
        boolean enrollInvite = EnrollInvite.isSelected();
        if (enrollInvite == true) {
            enrollInviteString = "yes";
        }
        boolean enrolled = Enrolled.isSelected();
        if (enrolled == true) {
            enrolledString = "yes";
        }

        if(pianoCheck == true){
            instruments += "piano ";
        }
        if(guitarCheck == true){
            instruments += "guitar ";
        }
        if(violinCheck == true) {
            instruments += "violin ";
        }
        if(voiceCheck == true) {
            instruments += "voice ";
        }
        if(ukuleleCheck == true) {
            instruments += "ukulele ";
        }

        String contactMethods = (String)ContactMethods.getSelectedItem();

        if (command.equals("Submit")) {
            //call data manager to send input from window to google spreadsheet
            SheetQuickstart quickstart = new SheetQuickstart(firstName, lastName, studentName, email, phone, instruments,
                    contactMethods, previewInvitedString, previewLesson, previewAttendedString, enrollInviteString, enrolledString,
                    enrollDate, preferredContact, notes);
            try {
                quickstart.sheetsRun();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
            JOptionPane.showMessageDialog(this, "Submitted!");

        }
    }
}
