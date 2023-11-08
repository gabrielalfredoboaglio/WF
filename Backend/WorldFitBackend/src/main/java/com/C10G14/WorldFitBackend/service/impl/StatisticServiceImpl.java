package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.statistic.StatisticDateDto;
import com.C10G14.WorldFitBackend.dto.statistic.StatisticDto;
import com.C10G14.WorldFitBackend.exception.InputNotValidException;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.mapper.StatisticDtoMapper;
import com.C10G14.WorldFitBackend.repository.StatisticRepository;
import com.C10G14.WorldFitBackend.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final StatisticDtoMapper mapper;


    @Override
    public StatisticDto save(StatisticDto statisticDto) {
        if (statisticDto.getTotalIncome() > 0 || statisticDto.getTotalOutcome() > 0) {
            throw new InputNotValidException("Error: income and outcome must be a positive number");
        }
        return mapper.entityToDto(statisticRepository.save(mapper.dtoToEntity(statisticDto)));
    }

    @Override
    public List<StatisticDto> getByDate(StatisticDateDto dates) {
        String date = validateDate(dates.getDate());
        return mapper.entityListToDto(statisticRepository.getByDate(date));
    }

    @Override
    public List<StatisticDto> getByDateBetween(StatisticDateDto dates) {
        String startDate = validateDate(dates.getStartDate());
        String endDate = validateDate(dates.getEndDate());
        return mapper.entityListToDto(statisticRepository.getByDateBetween(startDate, endDate));
    }

    @Override
    public List<StatisticDto> getAll() {
        return mapper.entityListToDto(statisticRepository.findAll());
    }

    @Override
    public StatisticDto getById(Long id) {
        return mapper.entityToDto(statisticRepository.findById(id).orElseThrow(() -> new NotFoundException("Static not found")));
    }

    @Override
    public void deleteById(Long id) {
        if(!statisticRepository.existsById(id)){
            throw new NotFoundException("Static not found");
        }
        statisticRepository.deleteById(id);
    }

    @Override
    public void deleteByDateBetween(StatisticDateDto dates){
        String startDate = validateDate(dates.getStartDate());
        String endDate = validateDate(dates.getEndDate());
        statisticRepository.deleteByDateBetween(startDate, endDate);
    }
    private String validateDate(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            Date parsedDate = dateFormat.parse(date);
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(parsedDate);
        } catch (ParseException e) {
            throw new InputNotValidException("Error: Invalid date format. Please use the format dd-mm-yyyy");
        }
    }
}
