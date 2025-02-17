package com.voting.votingapp.service;

import com.voting.votingapp.model.OptionVote;
import com.voting.votingapp.model.Poll;
import com.voting.votingapp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollServiceImpl implements PollService{

    private PollRepository pollRepository;
    @Autowired
    public PollServiceImpl(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }
    @Override
    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    @Override
    public ResponseEntity<Poll> getPollById(Long id) {
        return pollRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public void vote(Long pollId, int optionIndex) {
        //get Poll from db
        Poll poll=pollRepository.findById(pollId)
                .orElseThrow(() ->new RuntimeException("Poll Not Found"));

        //get All Option
        List<OptionVote> options=poll.getOptions();

        //If index for vote is not valid ,throw error
        if(optionIndex < 0 || optionIndex>= options.size()){
            throw new IllegalArgumentException("Invalid option index");
        }

        //get selected option
        OptionVote selectedOption = options.get(optionIndex);
        // Increment vote for selected option
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);
        // save incremented option into the db
        pollRepository.save(poll);
    }
}
