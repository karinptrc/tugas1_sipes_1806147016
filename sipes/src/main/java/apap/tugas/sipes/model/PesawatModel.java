package apap.tugas.sipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.NamedNodeMap;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pesawat")
public class PesawatModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "maskapai", nullable = false)
    private String maskapai;

    @NotNull
    @Size(max = 255)
    @Column(name = "nomorSeri", unique = true)
    private String nomorSeri;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempatDibuat")
    private String tempatDibuat;

    @NotNull
    @Column(name = "tanggalDibuat")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalDibuat;

    @NotNull
    @Column(name = "jenisPesawat")
    private String jenisPesawat;

    @OneToMany(mappedBy = "pesawat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenerbanganModel> listPenerbangan;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "tipeId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TipeModel tipe;

    @OneToMany(mappedBy = "pesawat", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<PesawatTeknisiModel> listPesawatTeknisi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public String getNomorSeri() {
        return nomorSeri;
    }

    public void setNomorSeri(String nomorSeri) {
        this.nomorSeri = nomorSeri;
    }

    public String getTempatDibuat() {
        return tempatDibuat;
    }

    public void setTempatDibuat(String tempatDibuat) {
        this.tempatDibuat = tempatDibuat;
    }

    public LocalDate getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(LocalDate tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

    public String getJenisPesawat() {
        return jenisPesawat;
    }

    public void setJenisPesawat(String jenisPesawat) {
        this.jenisPesawat = jenisPesawat;
    }

    public List<PenerbanganModel> getListPenerbangan() {
        return listPenerbangan;
    }

    public void setListPenerbangan(List<PenerbanganModel> listPenerbangan) {
        this.listPenerbangan = listPenerbangan;
    }

    public TipeModel getTipe() {
        return tipe;
    }

    public void setTipe(TipeModel tipe) {
        this.tipe = tipe;
    }

    public List<PesawatTeknisiModel> getListPesawatTeknisi() {
        return listPesawatTeknisi;
    }

    public void setListPesawatTeknisi(List<PesawatTeknisiModel> listPesawatTeknisi) {
        this.listPesawatTeknisi = listPesawatTeknisi;
    }
}
