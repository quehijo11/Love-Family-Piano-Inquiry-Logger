package InquiryPackage;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
//import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;


public class GUI extends JFrame implements ActionListener {
    private static Sheets sheetService;
    private static final String APPLICATION_NAME = "Inquiries for Love Family Piano";
    private static final String spreadsheetId = "1SXO7520d4IK4NLgZ04KyGQEqk4Jl7l7tCQCdmclkVvI";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public void run() {
        JFrame frame = new JFrame("Inquiry Logger"); //Name of program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        //buttons
        JButton button1 = new JButton("New Inquiry");
        JButton button2 = new JButton("Spreadsheet");
        JButton button3 = new JButton("Analytics");
        JLabel labelEnrollments = new JLabel("Total Enrollments: " + enrollmentCount());

        //button placement
        button1.setBounds(10,10,130,30);
        button2.setBounds(10,45,130,30);
        button3.setBounds(10,80,130,30);
        labelEnrollments.setBounds(10,115,130,30);

        //add to GUI
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(labelEnrollments);

        //basically makes the buttons on-click
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("New Inquiry")) {
            InquiryPackage.InquiryGUI Steve = new InquiryPackage.InquiryGUI();
            Steve.run();
        }
        else if (command.equals("Spreadsheet")) {
            try {
                Desktop.getDesktop().browse(new URI("https://docs.google.com/spreadsheets/d/1SXO7520d4IK4NLgZ04KyGQEqk4Jl7l7tCQCdmclkVvI/edit#gid=0"));
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        }
        else if (command.equals("Analytics")){
            InquiryPackage.Analytics Joe = new InquiryPackage.Analytics();
            Joe.run();
        }
        else if (command.equals("Exit")){
            dispose();
        }
    }

    private static Credential authorize() throws IOException, GeneralSecurityException {
        InputStream in = SheetQuickstart.class.getResourceAsStream("/credentials.json");
        assert in != null;
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY, clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();

        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver())
                .authorize("user");

        return credential;
    }

    public static Sheets getSheetService() throws IOException, GeneralSecurityException {
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public String enrollmentCount() {
        String enrollCount = "";

        try {
                sheetService = getSheetService();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }

        String range = "Sheet1!R2:R2";

        ValueRange response = null;
        try {
            response = sheetService.spreadsheets().values().get(spreadsheetId, range).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        java.util.List<java.util.List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) {
            System.out.println("No data found");
        } else {
            for (List row: values) {
                enrollCount = (String) row.get(0);
            }
        }
        return enrollCount;
    }
}
