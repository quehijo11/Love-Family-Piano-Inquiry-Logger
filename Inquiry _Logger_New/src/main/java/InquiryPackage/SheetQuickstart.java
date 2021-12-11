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
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.text.SimpleDateFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class SheetQuickstart {

    private static final String time = new SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date());
    private static String fName = "";
    private static String lName = "";
    private static String sName = "";
    private static String email = "";
    private static String phone = "";
    private static String instruments = "";
    private static String cMethod = "";
    private static String invited = "";
    private static String preview = "";
    private static String showedUp = "";
    private static String eInvite = "";
    private static String enrolled = "";
    private static String enrollDate = "";
    private static String endDate = "";
    private static String pMethod = "";
    private static String notes = "";

    private static Sheets sheetService;
    private static final String APPLICATION_NAME = "Inquiries for Love Family Piano";
    private static final String spreadsheetId = "1SXO7520d4IK4NLgZ04KyGQEqk4Jl7l7tCQCdmclkVvI";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public SheetQuickstart(String firstName, String lastName, String studentName, String email_1, String phone_1, String instruments_1,
                           String contactMethod, String previewInvited, String previewDate, String previewShowedUp, String enrollInvited, String enrolled_1,
                           String enrollDate_1, String pMethod_1, String notes_1) {
        fName = firstName;
        lName = lastName;
        sName = studentName;
        email = email_1;
        phone = phone_1;
        instruments = instruments_1;
        cMethod = contactMethod;
        invited = previewInvited;
        preview = previewDate;
        showedUp = previewShowedUp;
        eInvite = enrollInvited;
        enrolled = enrolled_1;
        enrollDate = enrollDate_1;
        endDate = "-";
        pMethod = pMethod_1;
        notes = notes_1;
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

    public static void sheetsRun() throws IOException, GeneralSecurityException {

        try {
            sheetService = getSheetService();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        ValueRange appendBody = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList(time, fName, lName, sName, email, phone, instruments,
                                cMethod, invited, preview, showedUp, eInvite, enrolled, enrollDate, endDate,
                                pMethod, notes)
                ));

        AppendValuesResponse appendResult = sheetService.spreadsheets().values()// Don't delete appendResult
                .append(spreadsheetId, "sheet1", appendBody)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("Insert_ROWS")
                .setIncludeValuesInResponse(true)
                .execute();


    }
}