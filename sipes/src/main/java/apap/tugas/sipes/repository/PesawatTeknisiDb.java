package apap.tugas.sipes.repository;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.model.TeknisiModel;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesawatTeknisiDb extends JpaRepository<PesawatTeknisiModel, Long> {
    List<PesawatTeknisiModel> findPesawatTeknisiModelsByPesawatId(Long id);
    void deleteAllByPesawatId(Long id);
    List<PesawatTeknisiModel> findPesawatTeknisiModelsByTeknisiId(Long id);
}
