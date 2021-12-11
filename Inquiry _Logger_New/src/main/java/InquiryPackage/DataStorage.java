package InquiryPackage;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataStorage {
    public static Date date;
    public static int inquiry;
    public static int prevLesson;
    public static int enrollment;
    public static int discontinue;



    public static float percentage;

    private static Sheets sheetService;
    private static final String APPLICATION_NAME = "Inquiries for Love Family Piano";
    private static final String spreadsheetId = "1SXO7520d4IK4NLgZ04KyGQEqk4Jl7l7tCQCdmclkVvI";

    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();


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

    public static String rangeCount() {
        String rangeCount = "0";

        try {
            sheetService = getSheetService();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        String range = "Sheet1!S3:S3";

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
                rangeCount =  (String) row.get(0);
            }
        }
        return rangeCount;
    }

    public static void run(Date startDate, Date endDate) throws IOException {
        try {
            sheetService = getSheetService();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        String temp10 = "";
        String temp12 = "";
        String temp14 = "";
        Integer count = 0;
        Integer showedUpPrev = 0;
        Integer enrollments = 0;
        Integer discontinues = 0;

        Integer j = 2;
        int loopMax = Integer.parseInt(rangeCount()) - 1;

        for (int i = 0; i < loopMax; i++) {
            String range = "Sheet1!A" + j + ":O" + j;

            ValueRange response = sheetService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            j++;
            List<List<Object>> values = response.getValues();

            SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
            for (List row : values) {
                try {
                    date = formatter.parse((String) row.get(0));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (!(date.before(startDate)) && !(date.after(endDate))) {
                if (values == null || values.isEmpty()) {

                } else {
                    count++;

                    for (List row : values) {
                        temp10 = (String) row.get(10);
                        temp12 = (String) row.get(12);
                        temp14 = (String) row.get(14);
                        if (temp10.equals("yes")) {
                            showedUpPrev++;
                        }
                        if (temp12.equals("yes")) {
                            enrollments++;
                        }
                        if (!temp14.equals("-")) {
                            discontinues++;
                        }
                    }

                    inquiry = count;
                    prevLesson = showedUpPrev;
                    enrollment = enrollments;
                    discontinue = discontinues;
                    if (inquiry > 0 && prevLesson > 0 ) {
                    percentage = (float) prevLesson/ (float) inquiry; }
                    else {
                        percentage = 0;
                    }
                }
            }
        }
    }

    public int getInquiry() { return inquiry; }

    public int getPrevLesson() { return prevLesson; }

    public int getEnrollment() { return enrollment; }

    public int getDiscontinue() { return discontinue; }

    public static float getPercentage() {
        return percentage;
    }
}
