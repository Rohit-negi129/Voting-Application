package com.voting.votingapp.service;

import com.voting.votingapp.model.Poll;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PollService {
    Poll createPoll(Poll poll);

    List<Poll> getAllPolls();

    ResponseEntity<Poll> getPollById(Long id);

    void vote(Long pollId, int optionIndex);
}
