package com.letitbee.diamondvaluationsystem.service.impl;

import com.letitbee.diamondvaluationsystem.entity.DiamondImage;
import com.letitbee.diamondvaluationsystem.entity.DiamondValuationNote;
import com.letitbee.diamondvaluationsystem.exception.ResourceNotFoundException;
import com.letitbee.diamondvaluationsystem.payload.DiamondImageDTO;
import com.letitbee.diamondvaluationsystem.repository.DiamondImageRepository;
import com.letitbee.diamondvaluationsystem.service.DiamondImageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DiamondImageServiceImpl implements DiamondImageService {

    private DiamondImageRepository diamondImageRepository;
    private ModelMapper mapper;

    public DiamondImageServiceImpl(DiamondImageRepository diamondImageRepository, ModelMapper mapper) {
        this.diamondImageRepository = diamondImageRepository;
        this.mapper = mapper;
    }

    @Override
    public DiamondImageDTO createDiamondImage(DiamondImageDTO diamondImageDTO) {
        DiamondImage diamondImage = mapToEntity(diamondImageDTO);
        diamondImage = diamondImageRepository.save(diamondImage);
        return mapToDTO(diamondImage);
    }

    @Override
    public DiamondImageDTO updateDiamondImage(long id, DiamondImageDTO diamondImageDTO) {
        DiamondImage diamondImage = diamondImageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diamond Image", "id", id + ""));

        diamondImage.setImage(diamondImage.getImage());
        diamondImage = diamondImageRepository.save(diamondImage);
        return mapToDTO(diamondImage);
    }

    private DiamondImageDTO mapToDTO(DiamondImage diamondImage) {
        return mapper.map(diamondImage, DiamondImageDTO.class);
    }

    private DiamondImage mapToEntity(DiamondImageDTO diamondImageDTO) {
        return mapper.map(diamondImageDTO, DiamondImage.class);
    }
}
