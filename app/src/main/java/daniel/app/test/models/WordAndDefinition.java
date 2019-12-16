package daniel.app.test.models;

public class WordAndDefinition {

    private String word;
    private String definition;
    private String numberThumbsUp;
    private String numberThumbsDown;

    public WordAndDefinition(String word, String definition,
                             String numberThumbsUp, String numberThumbsDown) {
        this.word = word;
        this.definition = definition;
        this.numberThumbsUp = numberThumbsUp;
        this.numberThumbsDown = numberThumbsDown;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getNumberThumbsUp() {
        return numberThumbsUp;
    }

    public void setNumberThumbsUp(String numberThumbsUp) {
        this.numberThumbsUp = numberThumbsUp;
    }

    public String getNumberThumbsDown() {
        return numberThumbsDown;
    }

    public void setNumberThumbsDown(String numberThumbsDown) {
        this.numberThumbsDown = numberThumbsDown;
    }
}
