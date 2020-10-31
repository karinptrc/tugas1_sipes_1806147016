package apap.tugas.sipes.repository;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface PesawatDb extends JpaRepository<PesawatModel, Long> {
    List<PesawatModel> findByOrderByIdAsc();
    Optional<PesawatModel> findById(Long id);
    List<PesawatModel> findByTanggalDibuatBefore(Integer year);
}
