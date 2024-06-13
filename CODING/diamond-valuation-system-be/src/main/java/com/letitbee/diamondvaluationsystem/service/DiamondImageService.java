package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.payload.DiamondImageDTO;

public interface DiamondImageService {
    DiamondImageDTO createDiamondImage(DiamondImageDTO diamondImageDTO);
    DiamondImageDTO updateDiamondImage(long id, DiamondImageDTO diamondImageDTO);

}
