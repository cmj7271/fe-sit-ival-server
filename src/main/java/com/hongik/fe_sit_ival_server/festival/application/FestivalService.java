package com.hongik.fe_sit_ival_server.festival.application;

import com.hongik.fe_sit_ival_server.festival.dto.FestivalSizeDTO;
import com.hongik.fe_sit_ival_server.festival.dto.FestivalWithoutSizeDTO;
import com.hongik.fe_sit_ival_server.festival.dao.FestivalRepository;
import com.hongik.fe_sit_ival_server.festival.domain.Festival;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FestivalService {
    private final FestivalRepository festivalRepository;

    public List<FestivalWithoutSizeDTO> findAll() {
        return festivalRepository.findAll().stream()
                .map(this::festivalToDTO).toList();
    }
    public FestivalWithoutSizeDTO findById(Long id) throws IllegalArgumentException {
        Festival festival = festivalRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return festivalToDTO(festival);
    }

    public Long createFestival(FestivalWithoutSizeDTO festivalDTO) {
        Festival festival = Festival.builder()
                .name(festivalDTO.name())
                .organizer(festivalDTO.organizer())
                .festivalBegin(festivalDTO.begin())
                .festivalEnd(festivalDTO.end())
                .location(festivalDTO.location())
                .description(festivalDTO.description())
                .build();
        return festivalRepository.save(festival).getId();
    }

    public Long updateFestival(Long id, FestivalWithoutSizeDTO festivalDTO) {
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
                .festivalBegin(festival.getFestivalBegin())
                .festivalEnd(festival.getFestivalEnd())
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
    private FestivalWithoutSizeDTO festivalToDTO(Festival festival) {
        return new FestivalWithoutSizeDTO(
                festival.getId(),
                festival.getName(),
                festival.getOrganizer(),
                festival.getFestivalBegin(),
                festival.getFestivalEnd(),
                festival.getLocation(),
                festival.getDescription()
        );
    }
    private Festival festivalUpdatedByDTO(Festival festival, FestivalWithoutSizeDTO festivalDTO) {
        return Festival.builder()
                .name(festivalDTO.name())
                .organizer(festivalDTO.organizer())
                .festivalBegin(festivalDTO.begin())
                .festivalEnd(festivalDTO.end())
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
