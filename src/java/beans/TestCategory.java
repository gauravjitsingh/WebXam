package beans;

public class TestCategory {
    private int TestCategoryId ;
    public beans.Test TestInfo = new Test() ;
    public beans.Category CategoryInfo = new Category() ;
    private int NoofQuestions ;

    public int getTestCategoryId() {
        return TestCategoryId;
    }

    public void setTestCategoryId(int TestCategoryId) {
        this.TestCategoryId = TestCategoryId;
    }

    public int getNoofQuestions() {
        return NoofQuestions;
    }

    public void setNoofQuestions(int NoofQuestions) {
        this.NoofQuestions = NoofQuestions;
    }
}
