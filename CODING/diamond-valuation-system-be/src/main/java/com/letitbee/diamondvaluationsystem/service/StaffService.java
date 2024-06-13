package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.payload.StaffDTO;

import java.util.List;

public interface StaffService {
    Response<StaffDTO> getAllStaffs(int pageNo, int pageSize, String sortBy, String sortDir);

    StaffDTO getStaffById(Long id);

    List<StaffDTO> getStaffByName(String name);

    StaffDTO updateStaffInformation(StaffDTO staffDto, Long id);

    StaffDTO createStaffInformation(StaffDTO staffDto);

    void deleteStaffById(Long id);
}
