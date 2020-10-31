package apap.tugas.sipes.repository;

import apap.tugas.sipes.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipeDb extends JpaRepository<TipeModel, Long> {
    List<TipeModel> findByOrderByIdAsc();
}
