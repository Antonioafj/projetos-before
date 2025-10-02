package br.com.antonio.eventostec.domain.event;

import org.springframework.web.multipart.MultipartFile;

public record EventRequestDTO(
        String title,
        String discription,
        Long date,
        String city,
        String state,
        Boolean remote,
        String eventUrl,
        MultipartFile image
) {
}
