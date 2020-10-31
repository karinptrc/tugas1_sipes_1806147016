package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.repository.PenerbanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService {
    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public List<PenerbanganModel> getPenerbanganList() {
        return penerbanganDb.findByOrderByIdAsc();
    }

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.save(penerbangan);
    }

    @Override
    public PenerbanganModel getPenerbanganById(Long id) {
        return penerbanganDb.findById(id).get();
    }

    @Override
    public List<PenerbanganModel> getPenerbanganByPesawatId(PesawatModel pesawat) {
        return penerbanganDb.findByPesawat(pesawat);
    }

    @Override
    public PenerbanganModel deletePenerbangan(Long id) {
        PenerbanganModel penerbangan = penerbanganDb.findById(id).get();
        penerbanganDb.deleteById(id);
        return penerbangan;
    }
}
