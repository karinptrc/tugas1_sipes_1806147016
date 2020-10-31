package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.repository.PesawatDb;
import apap.tugas.sipes.repository.PesawatTeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PesawatTeknisiServiceImpl implements PesawatTeknisiService{
    @Autowired
    PesawatTeknisiDb pesawatTeknisiDb;

    @Autowired
    PesawatDb pesawatDb;

    @Override
    public List<PesawatTeknisiModel> getListTeknisi(Long id) {
        List<PesawatTeknisiModel> teknisi = pesawatTeknisiDb.findPesawatTeknisiModelsByPesawatId(id);
        return teknisi;
    }

    @Override
    public PesawatTeknisiModel addPesawatTeknisi(PesawatTeknisiModel relasi) {
        return pesawatTeknisiDb.save(relasi);
    }

    @Override
    public void deleteRelasi(Long idPesawat) {
        pesawatTeknisiDb.deleteAllByPesawatId(idPesawat);
    }
}
