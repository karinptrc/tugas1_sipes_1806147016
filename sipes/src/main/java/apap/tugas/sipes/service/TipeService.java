package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TipeModel;

import java.util.List;

public interface TipeService {
    List<TipeModel> getTipeList();
    TipeModel getTipeById(Long id);
}
