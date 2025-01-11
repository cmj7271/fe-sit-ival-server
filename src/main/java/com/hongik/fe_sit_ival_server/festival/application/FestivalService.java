package com.hongik.fe_sit_ival_server.festival.application;

import com.hongik.fe_sit_ival_server.festival.dto.AddFestivalRequest;
import com.hongik.fe_sit_ival_server.festival.dto.FestivalSizeDTO;
import com.hongik.fe_sit_ival_server.festival.dto.FestivalWithoutSizeDTO;
import com.hongik.fe_sit_ival_server.festival.dao.FestivalRepository;
import com.hongik.fe_sit_ival_server.festival.domain.Festival;
import com.hongik.fe_sit_ival_server.festival.dto.FindFestivalResponse;
import com.hongik.fe_sit_ival_server.organizer.dao.OrganizerRepository;
import com.hongik.fe_sit_ival_server.organizer.domain.Organizer;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FestivalService {
    private final FestivalRepository festivalRepository;
    private final OrganizerRepository organizerRepository;

    public List<FindFestivalResponse> findAll() {
        return festivalRepository.findAll().stream()
                .map(this::festivalToDTO).toList();
    }
    public FindFestivalResponse findById(Long id) throws IllegalArgumentException {
        Festival festival = festivalRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return festivalToDTO(festival);
    }

    public Long createFestival(AddFestivalRequest festivalDTO) {
        Organizer organizer = organizerRepository.findById(festivalDTO.organizerId())
                .orElseThrow(() -> new IllegalArgumentException("no organizer"));

        Festival festival = Festival.builder()
                .name(festivalDTO.name())
                .organizer(organizer)
                .begin(festivalDTO.begin())
                .end(festivalDTO.end())
                .location(festivalDTO.location())
                .description(festivalDTO.description())
                .build();
        return festivalRepository.save(festival).getId();
    }

    public Long updateFestival(Long id, AddFestivalRequest festivalDTO) {
        Festival festival = festivalRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Festival updated = festivalUpdatedByDTO(festival, festivalDTO);
        return festivalRepository.save(updated).getId();
    }

    public void deleteFestival(Long id) {
        festivalRepository.deleteById(id);
    }

    public FestivalSizeDTO findFestivalSizeDTO(Long id) {
        Festival festival = festivalRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new FestivalSizeDTO(
                festival.getSeatHorizon(),
                festival.getSeatVertical()
        );
    }
    public Long setFestivalSize(Long id, FestivalSizeDTO festivalSizeDTO) {
        Festival festival = festivalRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Festival updatedFestival = Festival.builder()
                .name(festival.getName())
                .organizer(festival.getOrganizer())
                .begin(festival.getBegin())
                .end(festival.getEnd())
                .location(festival.getLocation())
                .description(festival.getDescription())
                .id(festival.getId())
                .seats(festival.getSeats())
                .teams(festival.getTeams())
                .seatHorizon(festivalSizeDTO.seatHorizon())
                .seatVertical(festivalSizeDTO.seatVertical())
                .build();
        return festivalRepository.save(updatedFestival).getId();
    }

    //== utils ==//
    private FindFestivalResponse festivalToDTO(Festival festival) {
        return new FindFestivalResponse(
                festival.getId(),
                festival.getName(),
                festival.getOrganizer().getName(),
                festival.getBegin(),
                festival.getEnd(),
                festival.getLocation(),
                festival.getDescription()
        );
    }
    private Festival festivalUpdatedByDTO(Festival festival, AddFestivalRequest festivalDTO) {
        Organizer organizer = organizerRepository.findById(festivalDTO.organizerId())
                .orElseThrow(() -> new IllegalArgumentException("no organizer"));

        return Festival.builder()
                .name(festivalDTO.name())
                .organizer(organizer)
                .begin(festivalDTO.begin())
                .end(festivalDTO.end())
                .location(festivalDTO.location())
                .description(festivalDTO.description())
                .id(festival.getId())
                .seats(festival.getSeats())
                .teams(festival.getTeams())
                .seatHorizon(festival.getSeatHorizon())
                .seatVertical(festival.getSeatVertical())
                .build();
    }
}
