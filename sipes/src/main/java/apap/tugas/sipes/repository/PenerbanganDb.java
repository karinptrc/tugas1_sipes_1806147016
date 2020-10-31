package apap.tugas.sipes.repository;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PenerbanganDb extends JpaRepository<PenerbanganModel, Long>{
    List<PenerbanganModel> findByOrderByIdAsc();
    Optional<PenerbanganModel> findById(Long id);
    List<PenerbanganModel> findByPesawat(PesawatModel pesawat);
}
