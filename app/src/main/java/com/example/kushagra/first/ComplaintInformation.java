package com.example.kushagra.first;

/**
 * Created by Keerthi on 09-11-2017.
 */

public class ComplaintInformation {
   public String ComplaintType;
    public  String ComplaintDetails;
    public String ComplaintLocation;
    public  String Complaint;

    public ComplaintInformation(){

    }




    public ComplaintInformation(String complaintType, String complaintDetails, String complaintLocation, String complaint) {
        ComplaintType = complaintType;
        ComplaintDetails = complaintDetails;
        ComplaintLocation = complaintLocation;
        Complaint = complaint ;
    }

}

