package com.letitbee.diamondvaluationsystem.service.impl;

import com.letitbee.diamondvaluationsystem.entity.DiamondImage;
import com.letitbee.diamondvaluationsystem.entity.DiamondValuationNote;
import com.letitbee.diamondvaluationsystem.exception.APIException;
import com.letitbee.diamondvaluationsystem.exception.ResourceNotFoundException;
import com.letitbee.diamondvaluationsystem.payload.DiamondImageDTO;
import com.letitbee.diamondvaluationsystem.payload.DiamondValuationNoteDTO;
import com.letitbee.diamondvaluationsystem.repository.DiamondValuationNoteRepository;
import com.letitbee.diamondvaluationsystem.service.DiamondValuationNoteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class DiamondValuationNoteServiceImpl implements DiamondValuationNoteService {

    private ModelMapper mapper;
    private DiamondValuationNoteRepository diamondValuationNoteRepository;

    public DiamondValuationNoteServiceImpl(ModelMapper mapper, DiamondValuationNoteRepository diamondValuationNoteRepository) {
        this.mapper = mapper;
        this.diamondValuationNoteRepository = diamondValuationNoteRepository;
    }

    @Override
    public DiamondValuationNoteDTO updateDiamondValuationNote(long id,
                                                              DiamondValuationNoteDTO diamondValuationNoteDTO) {
        DiamondValuationNote diamondValuationNote = diamondValuationNoteRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diamond Valuation Note", "id", id + ""));

        diamondValuationNote.setDiamondOrigin(diamondValuationNoteDTO.getDiamondOrigin());
        diamondValuationNote.setClarity(diamondValuationNoteDTO.getClarity());
        diamondValuationNote.setCaratWeight(diamondValuationNoteDTO.getCaratWeight());
        diamondValuationNote.setCertificateId(diamondValuationNoteDTO.getCertificateId());
        diamondValuationNote.setCertificateDate(new Date());
        diamondValuationNote.setClarityCharacteristicLink(diamondValuationNoteDTO.getClarityCharacteristicLink());
        diamondValuationNote.setColor(diamondValuationNoteDTO.getColor());
        diamondValuationNote.setCut(diamondValuationNoteDTO.getCut());
        diamondValuationNote.setFluorescence(diamondValuationNoteDTO.getFluorescence());
        diamondValuationNote.setPolish(diamondValuationNoteDTO.getPolish());
        diamondValuationNote.setProportions(diamondValuationNoteDTO.getProportions());
        diamondValuationNote.setShape(diamondValuationNoteDTO.getShape());
        diamondValuationNote.setSymmetry(diamondValuationNoteDTO.getSymmetry());
        diamondValuationNote.setClarityCharacteristic(
                diamondValuationNoteDTO.getClarityCharacteristic()
                        .stream().collect(Collectors.joining(",")));
        diamondValuationNote = diamondValuationNoteRepository.save(diamondValuationNote);
        return mapToDTO(diamondValuationNote, diamondValuationNoteDTO.getClarityCharacteristic());
    }

    @Override
    public DiamondValuationNoteDTO getAllDiamondValuationNoteByCertificateId(String certificateId) {
        DiamondValuationNote diamondValuationNote = diamondValuationNoteRepository
                .findByCertificateId(certificateId)
                .orElseThrow(() -> new ResourceNotFoundException("Diamond Valuation Note", "certificateId", certificateId));
        String[] items = diamondValuationNote.getClarityCharacteristic().split(",");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(items));
        return mapToDTO(diamondValuationNote, list);
    }

    @Override
    public DiamondValuationNoteDTO getDiamondValuationNoteById(long id) {
        DiamondValuationNote diamondValuationNote = diamondValuationNoteRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diamond Valuation Note", "id", id + ""));
        String[] items = diamondValuationNote.getClarityCharacteristic().split(",");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(items));
        return mapToDTO(diamondValuationNote, list);
    }

    private DiamondValuationNote mapToEntity(DiamondValuationNoteDTO valuationNoteDTO) {
        return mapper.map(valuationNoteDTO, DiamondValuationNote.class);
    }

    private DiamondValuationNoteDTO mapToDTO(DiamondValuationNote valuationNote
            , ArrayList<String> listClarityCharacteristic) {
        DiamondValuationNoteDTO result = mapper.map(valuationNote, DiamondValuationNoteDTO.class);
        result.setClarityCharacteristic(listClarityCharacteristic);
        return result;
    }


}
