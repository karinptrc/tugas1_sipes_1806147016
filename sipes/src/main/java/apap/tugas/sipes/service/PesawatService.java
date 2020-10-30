package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;

import java.util.List;

public interface PesawatService {
    //Method untuk mendapatkan daftar seluruh pesawat
    List<PesawatModel> getPesawatList();
}
