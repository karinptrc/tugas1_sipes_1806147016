package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.repository.TeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TeknisiServiceImpl implements TeknisiService{
    @Autowired
    TeknisiDb teknisiDb;

    @Override
    public List<TeknisiModel> getTeknisiList() {
        return teknisiDb.findByOrderByIdAsc();
    }

    @Override
    public TeknisiModel getTeknisiById(Long id) {
        return teknisiDb.findById(id).get();
    }
}
