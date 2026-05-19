package service.impl;

import dao.showDAO;
import dto.movieDTO;
import dto.showDTO;
import entity.Movie;
import entity.Show;
import mapper.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class showServiceImpl implements service.showService {
    private showDAO dao;

    public showServiceImpl() {
        this.dao = new showDAO();
    }

    @Override
    public List<showDTO> listShowByCurrentDateAndDirector(String director){
        return dao.listShowByCurrentDateAndDirector(director)
                .stream()
                .map(mapper::showToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateShowDateTime(String showId, LocalDateTime newShowDateTime){
        return dao.updateShowDateTime(showId,newShowDateTime);
    }

    @Override
    public boolean addMovie(movieDTO movieDTO){
        Movie movie = mapper.DTOtoMovie(movieDTO);
        return dao.addMovie(movie);
    }
}
