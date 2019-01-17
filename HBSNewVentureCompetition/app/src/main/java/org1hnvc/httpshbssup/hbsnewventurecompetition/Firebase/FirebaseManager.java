package org1hnvc.httpshbssup.hbsnewventurecompetition.Firebase;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.*;
import org1hnvc.httpshbssup.hbsnewventurecompetition.NameFile;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.*;
import android.provider.Settings.Secure;
import java.util.HashMap;
import java.util.Map;

public class FirebaseManager {

    public interface CompletionHandler {
        void onSuccess();
        void onError(String error);
    }
    public interface EventCodeCallback {
        void onSuccess(String eventCode);
        void onError(String error);
    }
    public interface CurrentEventCallback {
        void onSuccess(String currentEvent);
        void onError(String error);
    }
    public interface NotesCallback {
        void onSuccess(String notes);
        void onError(String error);
    }
    public interface CompanyCallback {
        void onSuccess(Company[] companies);
        void onError(String error);
    }
    public interface CompanyMemberCallback {
        void onSuccess(CompanyMember[] companies);
        void onError(String error);
    }
    public interface EventCallback {
        void onSuccess(Event[] events);
        void onError(String error);
    }
    public interface JudgeCallback {
        void onSuccess(Judge[] judges);
        void onError(String error);
    }
    public interface SponsorCallback {
        void onSuccess(Sponsor[] sponsors);
        void onError(String error);
    }
    public interface CoordinatorCallback {
        void onSuccess(Coordinator[] coordinators);
        void onError(String error);
    }


    public static FirebaseManager manager = new FirebaseManager();

    // MARK: - Firestore References

    private CollectionReference codes = FirebaseFirestore.getInstance().collection(NameFile.Firebase.CodeDB.codes);
    private CollectionReference companies = FirebaseFirestore.getInstance().collection(NameFile.Firebase.CompanyDB.companies);
    private CollectionReference events = FirebaseFirestore.getInstance().collection(NameFile.Firebase.EventDB.events);
    private CollectionReference judges = FirebaseFirestore.getInstance().collection(NameFile.Firebase.JudgeDB.judges);
    private CollectionReference sponsors = FirebaseFirestore.getInstance().collection(NameFile.Firebase.SponsorDB.sponsors);
    private CollectionReference coordinators = FirebaseFirestore.getInstance().collection(NameFile.Firebase.CoordinatorDB.coordinators);

    // Manager Functions

    // Fetches the event code
    public void fetchEventCode(final EventCodeCallback callback){
        codes.document(NameFile.Firebase.CodeDB.eventCode).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String code = document.getString(NameFile.Firebase.CodeDB.code);
                        callback.onSuccess(code);
                    } else {
                        callback.onError("No such document");
                    }
                } else {
                    callback.onError(task.getException().getMessage());
                }
            }
        });
    }

    // Fetches all companies
    public void fetchCompanies(final CompanyCallback callback){
        companies.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Company[] companies = new Company[task.getResult().size()];
                    int index = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString(NameFile.Firebase.CompanyDB.name);
                        String description = document.getString(NameFile.Firebase.CompanyDB.description);
                        String logoImageURL = document.getString(NameFile.Firebase.CompanyDB.logoImageURL);
                        Double order = document.getDouble(NameFile.Firebase.CompanyDB.order);
                        Double stars = document.getDouble(NameFile.Firebase.CompanyDB.stars);
                        Company company = new Company(document.getId(), name, description, logoImageURL, order, stars);
                        companies[index] = company;
                        index++;
                    }
                    callback.onSuccess(companies);
                } else {
                    callback.onError(task.getException().getMessage());
                }
            }
        });
    }

    // Fetches the notes of a company taken by this device
    public void fetchNotes(String companyID, final NotesCallback callback){
        String android_id = Secure.ANDROID_ID;
        companies.document(companyID).collection(NameFile.Firebase.CompanyDB.notes).document(android_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String notes = document.getString(NameFile.Firebase.CompanyDB.note);
                        callback.onSuccess(notes);
                    } else {
                        callback.onError("No such document");
                    }
                } else {
                    callback.onError(task.getException().getMessage());
                }
            }
        });
    }

    // Adds the notes of a company taken by this device
    public void addNotes(String companyID, String notes, final CompletionHandler completionHandler){
        Map<String, Object> notesData = new HashMap<>();
        notesData.put(NameFile.Firebase.CompanyDB.note, notes);
        String android_id = Secure.ANDROID_ID;
        companies.document(companyID).collection(NameFile.Firebase.CompanyDB.notes).document(android_id).set(notesData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                completionHandler.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                completionHandler.onError(e.getMessage());
            }
        });
    }

    // Fetches all members inside a company
    public void fetchCompanyMembers(String companyID, final CompanyMemberCallback callback){
        companies.document(companyID).collection(NameFile.Firebase.CompanyDB.members).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    CompanyMember[] companyMembers = new CompanyMember[task.getResult().size()];
                    int index = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String firstName = document.getString(NameFile.Firebase.CompanyDB.firstName);
                        String lastName = document.getString(NameFile.Firebase.CompanyDB.lastName);
                        String profileImageURL = document.getString(NameFile.Firebase.CompanyDB.profileImageURL);
                        String email = document.getString(NameFile.Firebase.CompanyDB.email);
                        String phoneNumber = document.getString(NameFile.Firebase.CompanyDB.phoneNumber);
                        String linkedInURL = document.getString(NameFile.Firebase.CompanyDB.linkedInURL);
                        String education = document.getString(NameFile.Firebase.CompanyDB.education);
                        String position = document.getString(NameFile.Firebase.CompanyDB.position);
                        CompanyMember companyMember = new CompanyMember(firstName, lastName, profileImageURL, email, phoneNumber, linkedInURL, education, position);
                        companyMembers[index] = companyMember;
                        index++;
                    }
                    callback.onSuccess(companyMembers);
                } else {
                    callback.onError(task.getException().getMessage());
                }
            }
        });
    }

    // Adds a device specific vote to a company
    public void addVote(String companyID, Double rating, final CompletionHandler completionHandler){
        Map<String, Object> voteData = new HashMap<>();
        voteData.put(NameFile.Firebase.CompanyDB.stars, rating);
        String android_id = Secure.ANDROID_ID;
        companies.document(companyID).collection(NameFile.Firebase.CompanyDB.votes).document(android_id).set(voteData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                completionHandler.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                completionHandler.onError(e.getMessage());
            }
        });
    }

    // Fetches all events
    public void fetchEvents(final EventCallback callback){
        events.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Event[] events = new Event[task.getResult().size()-1];
                    int index = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (!document.getId().equals("CurrentEvent")){
                            Timestamp time = document.getTimestamp(NameFile.Firebase.EventDB.time);
                            String description = document.getString(NameFile.Firebase.EventDB.description);
                            Event event = new Event(document.getId(), time, description);
                            events[index] = event;
                            index++;
                        }
                    }
                    callback.onSuccess(events);
                } else {
                    callback.onError(task.getException().getMessage());
                }
            }
        });
    }

    // Fetches the current event
    public void fetchCurrentEvent(final CurrentEventCallback callback){
        events.document(NameFile.Firebase.EventDB.currentEvent).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String currentEventID = document.getString(NameFile.Firebase.EventDB.currentEventID);
                        callback.onSuccess(currentEventID);
                    } else {
                        callback.onError("No such document");
                    }
                } else {
                    callback.onError(task.getException().getMessage());
                }
            }
        });
    }

    // Fetches all judges
    public void fetchJudges(final JudgeCallback callback){
        judges.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Judge[] judges = new Judge[task.getResult().size()];
                    int index = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String firstName = document.getString(NameFile.Firebase.JudgeDB.firstName);
                        String lastName = document.getString(NameFile.Firebase.JudgeDB.lastName);
                        String profileImageURL = document.getString(NameFile.Firebase.JudgeDB.profileImageURL);
                        String linkedInURL = document.getString(NameFile.Firebase.JudgeDB.linkedInURL);
                        String description = document.getString(NameFile.Firebase.JudgeDB.description);
                        Judge judge =  new Judge(firstName, lastName, profileImageURL, linkedInURL, description);
                        judges[index] = judge;
                        index++;
                    }
                    callback.onSuccess(judges);
                } else {
                    callback.onError(task.getException().getMessage());
                }
            }
        });
    }

    // Fetches all sponsors
    public void fetchSponsors(final SponsorCallback callback){
        sponsors.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Sponsor[] sponsors = new Sponsor[task.getResult().size()];
                    int index = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString(NameFile.Firebase.SponsorDB.name);
                        String description = document.getString(NameFile.Firebase.SponsorDB.description);
                        String logoImageURL = document.getString(NameFile.Firebase.SponsorDB.logoImageURL);
                        String prize = document.getString(NameFile.Firebase.SponsorDB.prize);
                        String website = document.getString(NameFile.Firebase.SponsorDB.website);
                        String repProfileImageURL = document.getString(NameFile.Firebase.SponsorDB.repProfileImageURL);
                        String repFirstName = document.getString(NameFile.Firebase.SponsorDB.repFirstName);
                        String repLastName = document.getString(NameFile.Firebase.SponsorDB.repLastName);
                        String repEmail = document.getString(NameFile.Firebase.SponsorDB.repEmail);
                        Sponsor sponsor = new Sponsor(name, description, logoImageURL, prize, website, repProfileImageURL, repFirstName, repLastName, repEmail);
                        sponsors[index] = sponsor;
                        index++;
                    }
                    callback.onSuccess(sponsors);
                } else {
                    callback.onError(task.getException().getMessage());
                }
            }
        });
    }

    // Fetches all coordinators
    public void fetchCoordinators(final CoordinatorCallback callback){
        coordinators.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Coordinator[] coordinators = new Coordinator[task.getResult().size()];
                    int index = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String firstName = document.getString(NameFile.Firebase.CoordinatorDB.firstName);
                        String lastName = document.getString(NameFile.Firebase.CoordinatorDB.lastName);
                        String profileImageURL = document.getString(NameFile.Firebase.CoordinatorDB.profileImageURL);
                        String position = document.getString(NameFile.Firebase.CoordinatorDB.position);
                        String organization = document.getString(NameFile.Firebase.CoordinatorDB.organization);
                        String linkedInURL = document.getString(NameFile.Firebase.CoordinatorDB.linkedInURL);
                        Coordinator coordinator = new Coordinator(firstName, lastName, profileImageURL, position, organization, linkedInURL);
                        coordinators[index] = coordinator;
                        index++;
                    }
                    callback.onSuccess(coordinators);
                } else {
                    callback.onError(task.getException().getMessage());
                }
            }
        });
    }

}
