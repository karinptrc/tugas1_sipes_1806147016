package apap.tugas.sipes.repository;

import apap.tugas.sipes.model.TeknisiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeknisiDb extends JpaRepository<TeknisiModel, Long> {
    List<TeknisiModel> findByOrderByIdAsc();
}
