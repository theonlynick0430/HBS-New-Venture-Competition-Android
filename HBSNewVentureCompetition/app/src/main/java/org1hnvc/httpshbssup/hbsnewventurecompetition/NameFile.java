package org1hnvc.httpshbssup.hbsnewventurecompetition;

public class NameFile {

    public static class Firebase{

        public static class CodeDB{
            //codes <collection>
            public static String codes = "Codes";
            public static String eventCode = "EventCode";
                //event code <document> - EventCode
            public static String code = "Code";
        }

        public static class CompanyDB{
            //companies <collection>
            public static String companies = "Companies";
                //company <document> - random ID
            public static String name = "Name";
            public static String description = "Description";
            public static String logoImageURL = "LogoImageURL";
            public static String order = "Order";
                    //members <collection>
            public static String members = "Members";
                        //member <document> - randomID
            public static String firstName = "FirstName";
            public static String lastName = "LastName";
            public static String profileImageURL = "ProfileImageURL";
            public static String email = "Email";
            public static String phoneNumber = "PhoneNumber";
            public static String linkedInURL = "LinkedInURL";
            public static String education = "Education";
            public static String position = "Position";
            public static String website = "Website";
                    //notes <collection>
            public static String notes = "Notes";
                        //note <document> - deviceID
            public static String note = "Note";
                     //votes <collection>
            public static String votes = "Votes";
                         //vote <document> - deviceID
            public static String stars = "Stars";
        }

        public static class EventDB{
            //events <collection>
            public static String events = "Events";
            public static String currentEvent = "CurrentEvent";
                //current event <document> - CurrentEvent
            public static String currentEventID = "CurrentEventID";
                //event <document> - random ID
            public static String time = "Time";
            public static String description = "Description";
        }

        public static class JudgeDB{
            //judges <collection>
            public static String judges = "Judges";
                //judge <document> - random ID
            public static String firstName = "FirstName";
            public static String lastName = "LastName";
            public static String profileImageURL = "ProfileImageURL";
            public static String linkedInURL = "LinkedInURL";
            public static String description = "Description";
        }

        public static class SponsorDB{
            //sponsors <collection>
            public static String sponsors = "Sponsors";
                //sponsor <document> - random ID
            public static String name = "Name";
            public static String description = "Description";
            public static String logoImageURL = "LogoImageURL";
            public static String prize = "Prize";
            public static String website = "Website";
            public static String repProfileImageURL = "RepProfileImageURL";
            public static String repFirstName = "RepFirstName";
            public static String repLastName = "RepLastName";
            public static String repEmail = "RepEmail";
        }

        public static class CoordinatorDB{
            //coordinators <collection>
            public static String coordinators = "Coordinators";
                //coordinator <document> - random ID
            public static String firstName = "FirstName";
            public static String lastName = "LastName";
            public static String profileImageURL = "ProfileImageURL";
            public static String position = "Position";
            public static String organization = "Organization";
            public static String linkedInURL = "LinkedInURL";
        }

    }

}
