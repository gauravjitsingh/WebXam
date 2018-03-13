package beans;

public class ConductedTest {
    private int ConductedTestId ;
    public beans.Candidate CandidateInfo = new Candidate();
    private String DateConducted ;
    private byte Submitted ;
    private short TimeRemaining ;

    public int getConductedTestId() {
        return ConductedTestId;
    }

    public void setConductedTestId(int ConductedTestId) {
        this.ConductedTestId = ConductedTestId;
    }

    public String getDateConducted() {
        return DateConducted;
    }

    public void setDateConducted(String DateConducted) {
        this.DateConducted = DateConducted;
    }

    public byte getSubmitted() {
        return Submitted;
    }

    public void setSubmitted(byte Submitted) {
        this.Submitted = Submitted;
    }

    public short getTimeRemaining() {
        return TimeRemaining;
    }

    public void setTimeRemaining(short TimeRemaining) {
        this.TimeRemaining = TimeRemaining;
    }
    
    
}
