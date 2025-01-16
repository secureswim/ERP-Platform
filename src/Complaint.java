public class Complaint {
    private String date;
    private String description;
    private String status;
    private int complaintID;

    public Complaint(String date, String description){
        this.date=date;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    int getComplaintID() {
        return complaintID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "Complaint{" +
                "date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", complaintID='" + complaintID + '\'' +
                '}';
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }
}
