package com.csye6225.spring2019.impl;

import com.csye6225.spring2019.entity.Note;
import com.csye6225.spring2019.repository.NoteRepository;
import com.csye6225.spring2019.service.NoteService;
import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public boolean addNewNote(Note note){
        if(note==null||note.getUserId()<=0|| Strings.isNullOrEmpty(note.getTitle())){
            log.warn("Lacking data for add a new note");
            return false;
        }
        int re = noteRepository.insertNewNote(note);
        return re>0;
    }


}