package edu.usc.clicker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ian on 9/9/15.
 */
public class MultipleChoiceQuestion {
    private String question;
    private List<String> answers = new ArrayList<>();

    public List<String> getAnswers() {
        return answers;
    }

    public MultipleChoiceQuestion(String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }
}
