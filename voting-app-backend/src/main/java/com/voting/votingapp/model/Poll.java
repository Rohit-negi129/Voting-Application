package com.voting.votingapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "polls")
public class Poll {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private  Long id;

    @Getter
    private String question;
    

    @Getter
    @ElementCollection
    private List<OptionVote> options=new ArrayList<>();

//    @ElementCollection
//    private List<Long> votes= new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<OptionVote> getOptions() {
        return options;
    }

    public void setOptions(List<OptionVote> options) {
        this.options = options;
    }
}
