package com.letitbee.diamondvaluationsystem.service.impl;

import com.letitbee.diamondvaluationsystem.entity.Notification;
import com.letitbee.diamondvaluationsystem.exception.ResourceNotFoundException;
import com.letitbee.diamondvaluationsystem.payload.NotificationDTO;
import com.letitbee.diamondvaluationsystem.repository.NotificationRepository;
import com.letitbee.diamondvaluationsystem.service.NotificationService;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;
    private ModelMapper mapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, ModelMapper mapper) {
        this.notificationRepository = notificationRepository;
        this.mapper = mapper;
    }


    @Override
    public List<NotificationDTO> getAllNotificationByAccount(Long id) {
        List<Notification> notification = notificationRepository.findByAccountId(id);
        if(!notification.isEmpty())
            return notification.stream().map(this::mapToDto).collect(Collectors.toList());
        return null;
    }

    @Override
    public NotificationDTO updateNotification(NotificationDTO notificationDTO, Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: ", "id", id + ""));
        notification.setMessage(notificationDTO.getMessage());
        notification.setIsRead(notificationDTO.getIsRead());
        return mapToDto(notificationRepository.save(notification));
    }

    private NotificationDTO mapToDto(Notification notification) {
        return mapper.map(notification, NotificationDTO.class);
    }

    private Notification mapToEntity(NotificationDTO notificationDTO) {
        return mapper.map(notificationDTO, Notification.class);
    }
}
