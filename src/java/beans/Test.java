package beans;

public class Test {
    
    private int TestId ;
    public beans.Department DepartmentInfo = new Department() ;
    public beans.Job JobInfo = new Job();
    private String TestDate ;
    private int Vacancies ;
    private short PassingMarks ;
    private short Duration ;
    private String Description ;
    private byte Level ;

    public int getTestId() {
        return TestId;
    }

    public void setTestId(int TestId) {
        this.TestId = TestId;
    }

    public String getTestDate() {
        return TestDate;
    }

    public void setTestDate(String TestDate) {
        this.TestDate = TestDate;
    }

    public int getVacancies() {
        return Vacancies;
    }

    public void setVacancies(int Vacancies) {
        this.Vacancies = Vacancies;
    }

    public short getPassingMarks() {
        return PassingMarks;
    }

    public void setPassingMarks(short PassingMarks) {
        this.PassingMarks = PassingMarks;
    }

    public short getDuration() {
        return Duration;
    }

    public void setDuration(short Duration) {
        this.Duration = Duration;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public byte getLevel() {
        return Level;
    }

    public void setLevel(byte Level) {
        this.Level = Level;
    }
    
}
