package com.example.kushagra.first;

/**
 * Created by Keerthi on 09-11-2017.
 */

public class ComplaintInformation {
   public String ComplaintType;
    public  String ComplaintDetails;
    public String ComplaintLocation;
    public  String Complaint;
    public String ComplaintDate;
    public String ComplaintTime;





    public ComplaintInformation(String currentTime,String complaintDate,String complaintType, String complaintDetails, String complaintLocation, String complaint) {
        ComplaintTime=currentTime;
        ComplaintDate=complaintDate;
        ComplaintType = complaintType;
        ComplaintDetails = complaintDetails;
        ComplaintLocation = complaintLocation;
        Complaint = complaint ;

    }

}

