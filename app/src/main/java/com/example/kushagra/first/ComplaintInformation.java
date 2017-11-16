package com.example.kushagra.first;

/**
 * Created by Keerthi on 09-11-2017.
 */

public class ComplaintInformation {
   public String ComplaintID;
    public  String ComplaintDetails;
    public String ComplaintLocation;
    public  String Complaint;

    public ComplaintInformation(){

    }




    public ComplaintInformation(String complaintID, String complaintDetails, String complaintLocation, String complaint) {
        ComplaintID = complaintID;
        ComplaintDetails = complaintDetails;
        ComplaintLocation = complaintLocation;
        Complaint = complaint ;
    }

}

