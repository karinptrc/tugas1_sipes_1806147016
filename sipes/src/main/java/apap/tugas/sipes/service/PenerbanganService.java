package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;

import java.util.List;

public interface PenerbanganService {
    List<PenerbanganModel> getPenerbanganList();
    void addPenerbangan(PenerbanganModel penerbangan);
    PenerbanganModel getPenerbanganById(Long id);
    List<PenerbanganModel> getPenerbanganByPesawatId(PesawatModel pesawat);
    PenerbanganModel deletePenerbangan(Long id);
}
