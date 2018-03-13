package beans;

public class Question {
    private int QuestionId ;
    private String QuestionText ;
    private String Option1 ;
    private String Option2 ;
    private String Option3 ;
    private String Option4 ;
    private byte Answer ;
    private byte Level ;
    private int AttemptedQuestionId;
    private byte OptionSelected ;
    public beans.Category CategoryInfo = new Category() ;

    public int getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(int QuestionId) {
        this.QuestionId = QuestionId;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String QuestionText) {
        this.QuestionText = QuestionText;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String Option1) {
        this.Option1 = Option1;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String Option2) {
        this.Option2 = Option2;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String Option3) {
        this.Option3 = Option3;
    }

    public String getOption4() {
        return Option4;
    }

    public void setOption4(String Option4) {
        this.Option4 = Option4;
    }

    public byte getAnswer() {
        return Answer;
    }

    public void setAnswer(byte Answer) {
        this.Answer = Answer;
    }

    public byte getLevel() {
        return Level;
    }

    public void setLevel(byte Level) {
        this.Level = Level;
    }

    public int getAttemptedQuestionId() {
        return AttemptedQuestionId;
    }

    public void setAttemptedQuestionId(int AttemptedQuestionId) {
        this.AttemptedQuestionId = AttemptedQuestionId;
    }
    
    public byte getOptionSelected() {
        return OptionSelected;
    }

    public void setOptionSelected(byte OptionSelected) {
        this.OptionSelected = OptionSelected;
    }    
    
    
}
