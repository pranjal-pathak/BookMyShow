package com.scaler.bookmyshow.Repositories;

import com.scaler.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository <ShowSeatType,Long> {

    List<ShowSeatType> findAllByShow(Long showId);
}
