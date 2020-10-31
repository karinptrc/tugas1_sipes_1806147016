package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;

import java.util.List;

public interface PesawatService {
    //Method untuk mendapatkan daftar seluruh pesawat
    List<PesawatModel> getPesawatList();

    PesawatModel getPesawatById(Long id);
    PesawatModel addPesawat(PesawatModel pesawat);
    String buatNomorSeri(PesawatModel pesawat);
    PesawatModel deletePesawat(Long id);
    List<PesawatModel> getPesawatTua();
}
