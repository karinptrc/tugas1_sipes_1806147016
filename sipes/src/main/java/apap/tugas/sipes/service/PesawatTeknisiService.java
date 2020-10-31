package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatTeknisiModel;

import java.util.List;

public interface PesawatTeknisiService {
    List<PesawatTeknisiModel> getListTeknisi(Long id);
    PesawatTeknisiModel addPesawatTeknisi(PesawatTeknisiModel relasi);
    void deleteRelasi(Long idPesawat);
    List<PesawatTeknisiModel> getPesawatByTeknisi(Long id);
}
