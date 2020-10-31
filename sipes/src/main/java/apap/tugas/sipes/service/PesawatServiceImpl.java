package apap.tugas.sipes.service;

import apap.tugas.sipes.model.*;
import apap.tugas.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{
    @Autowired
    PesawatDb pesawatDb;

    @Override
    public List<PesawatModel> getPesawatList() {
        return pesawatDb.findByOrderByIdAsc();
    }

    @Override
    public PesawatModel getPesawatById(Long id) {
        return pesawatDb.findById(id).get();
    }

    @Override
    public PesawatModel addPesawat(PesawatModel pesawat) {
        PesawatModel pesawatModel = pesawatDb.save(pesawat);
        return pesawatModel;
    }

    @Override
    public String buatNomorSeri(PesawatModel pesawat) {
        String nomorSeri = new String();
        if(pesawat.getJenisPesawat().equals("Komersial")) nomorSeri += "1";
        nomorSeri += "2";
        if(pesawat.getTipe().getId() == 1) nomorSeri += "BO";
        else if(pesawat.getTipe().getId() == 2) nomorSeri += "AT";
        else if(pesawat.getTipe().getId() == 3) nomorSeri += "AB";
        else if(pesawat.getTipe().getId() == 4)nomorSeri += "BB";;
        nomorSeri += reverse(pesawat.getTanggalDibuat().getYear());
        nomorSeri += pesawat.getTanggalDibuat().getYear() + 8;
        Random r = new Random();
        char kapital = (char)(r.nextInt(26) + 'a');
        nomorSeri += Character.toString(kapital).toUpperCase();
        kapital = (char)(r.nextInt(26) + 'a');
        nomorSeri += Character.toString(kapital).toUpperCase();

        return nomorSeri;
    }

    private String reverse(Integer year){
        String tobe = new String();
        for (int i = 4; i > 0; i--){
            tobe += year.toString().substring(i-1, i);
        }
        return tobe;
    }

    @Override
    public PesawatModel deletePesawat(Long id) {
        PesawatModel pesawat = pesawatDb.findById(id).get();
        pesawatDb.deleteById(id);
        return pesawat;
    }

    @Override
    public List<PesawatModel> getPesawatTua() {
        LocalDate time = LocalDate.now().minusYears(10);
        List<PesawatModel> listPesawat = pesawatDb.findByTanggalDibuatBefore(time);
        return listPesawat;
    }

    @Override
    public List<PesawatModel> getPesawatByTipe(TipeModel tipe) {
        return pesawatDb.findPesawatModelsByTipe(tipe);
    }
}
